import axios from "../../axios/axios-common";

const FollowStore = {
    namespaced: true,
    state: {
        FollowList: [], //유저의 소속 밴드 리스트
    },
    getters: {
        //팔로우리스트 반환
        getFollowList(state) { return state.FollowList; },
       
    },
    mutations: {
        //팔로우리스트 설정
        setFollowList(state, payload) { state.FollowList = payload; },
    },
    actions: {
        //팔로우리스트 가져오기
        reqFollowList(context, memberno) {
            return axios
                .get("/followlist/" + memberno)
                .then((response) => {
                    if (response.data.status) {
                        context.commit("setFollowList", response.data.object);
                        return { result: true, msg: "팔로우 목록 받아오기 성공" };
                    }
                    else return { result: false, msg: "팔로우 목록을 불러오는데 실패했습니다" };
                })
                .catch((error) => console.log(error));
        },

        //팔로우하기
        reqFollowBand(context, info) {
            return axios
                .post("/follow", {
                    bandId: info.bandId,
                    memberId : info.memberId
                })
                .then((response) => {
                    if (response.data.status) {
                        return { result: true, msg: "팔로우 성공" };
                    }
                    else return { result: false, msg: "팔로우에 실패했습니다" };
                })
                .catch((error) => console.log(error));
        },

        // 팔로우 중인 지 확인하기
        reqFollowCheck(context, info) {
            return axios
                .get("/followcheck?bandId=" + info.bandId + "&memberId="+info.memberId)
                .then((response) => {
                    if (response.data.status) return { result: true, msg: response.data.object.followId };
                    else return { result: false, msg: "팔로우 중이 아닙니다." };
                })
                .catch((error) => {
                    console.log(error);
                    return { result: false };
                })
        },
        
        // 팔로우 취소하기
        reqFollowCancle(context, followId) {
            return axios
                .delete("/follow/followlist/" + followId)
                .then((response) => {
                    if (response.data.status) return { result: true, msg: "언팔로우 성공" };
                    else return { result: false, msg: "언팔로우에 실패했습니다" };
                })
                .catch((error) => {
                    console.log(error);
                })
        },

        //팔로우수 확인하기
        reqFollownum(context, bandId) {
            return axios
                .get("/follownum/" + bandId)
                .then((response) => {
                    if (response.data.status) return {
                        result : true, msg:"팔로우수 조회 성공", follownum : response.data.object.follownum
                    }
                    else return { result: false, msg: "팔로우 수 조회 실패" };
                })
                .catch((error) => {
                    console.log(error);
                })
        }
    }
}

export default FollowStore;