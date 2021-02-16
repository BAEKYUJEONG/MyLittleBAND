import axios from "../../axios/axios-common";

const BandStore = {
    namespaced: true,
    state: {
        BandList: [], //유저의 소속 밴드 리스트
        MamberList: [],  //밴드의 멤버리스트
        VideoList: [],//밴드의 비디오리스트
        BandInfo: {}, //밴드 정보
    },
    getters: {
        //밴드리스트 반환
        getBandList(state) { return state.BandList; },
        //멤버리스트 반환
        getMemberList(state) { return state.MemberList; },
        //비디오리스트 반환
        getVideoList(state) { return state.VideoList; },
        //밴드정보 반환
        getBandInfo(state) { return state.BandInfo; },
    },
    mutations: {
        //밴드리스트 설정
        setBandList(state, payload) { state.BandList = payload; },
        //밴드멤버들 설정
        setMemberList(state, payload) { state.MemberList = payload; },
        //비디오리스트 설정
        setVideoList(state, payload) { state.VideoList = payload; },
        //밴드정보 설정
        setBandInfo(state, payload) { state.BandInfo = payload; },
    },
    actions: {
        //밴드리스트 가져오기
        reqBandList(context, memberno) {
            return axios
                .get("/band-list/" + memberno)
                .then((response) => {
                    if (response.data.status) {
                        context.commit("setBandList", response.data.object);
                        return { result: true, msg: "밴드 목록 받아오기 성공" };
                    }
                    else return { result: false, msg: "밴드 목록을 불러오는데 실패했습니다" };
                })
                .catch((error) => console.log(error));
        },

        //밴드정보 가져오기
        reqBandInfo(context, bandno) {
            return axios
                .get("/band/" + bandno)
                .then((response) => {
                    if (response.data.status) {
                        context.commit("setBandInfo", response.data.object);
                        return { result: true, msg: "밴드정보 받아오기 성공" };
                    }
                    else return { result: false, msg: "밴드정보를 불러오는데 실패했습니다" };
                })
                .catch((error) => console.log(error));
        },

        //밴드 생성하기
        reqCreateBand(context, info) {
            return axios
                .post("/band/" + info.memberId, {
                    name: info.name,
                    intro: info.intro,
                    genre: info.genre,
                    color: info.color,
                    codeSession : info.codeSession
                })
                .then((response) => {
                    if (response.data.status) {
                        return { result: true, msg: "밴드개설 성공" };
                    }
                    else return { result: false, msg: "이미 존재하는 밴드명입니다" };
                })
                .catch((error) => console.log(error));
        },

        // 밴드정보 수정하기
        reqModifyBand(context, info) {
            let frm = new FormData();
            frm.append("name", info.name);
            frm.append("profile", info.profile);

            return axios
                .put("/band/" + info.bandId, frm)
                .then((response) => {
                    if (response.data.status) return { result: true, msg: "밴드 정보 수정이 완료되었습니다" };
                    else return { result: false, msg: "밴드 정보 수정이 실패했습니다" };
                })
                .catch((error) => {
                    console.log(error);
                })
        },
        
        // 밴드 삭제하기
        reqDeleteBoard(context, bandno) {
            return axios
                .delete("/band/" + bandno)
                .then((response) => {
                    if (response.data.status) return { result: true, msg: "밴드 해체가 완료되었습니다" };
                    else return { result: false, msg: "밴드 해체가 실패했습니다" };
                })
                .catch((error) => {
                    console.log(error);
                })
        },

        
        //밴드소속 멤버정보 가져오기
        reqMemberList(context, bandno) {
            return axios
                .get("/band/member/" + bandno)
                .then((response) => {
                    if (response.data.status) {
                        context.commit("setMemberList", response.data.object);
                        return { result: true, msg: "멤버 목록 받아오기 성공" };
                    }
                    else return { result: false, msg: "멤버 목록을 불러오는데 실패했습니다" };
                })
                .catch((error) => console.log(error));
        },

        // 멤버 세션 수정하기
        reqModifySession(context, info) {
            let frm = new FormData();
            frm.append("session", info.codeSession);

            return axios
                .put("/band/member/" + info.crewId, frm)
                .then((response) => {
                    if (response.data.status) return { result: true, msg: "세션 정보 수정이 완료되었습니다" };
                    else return { result: false, msg: "세션 정보 수정이 실패했습니다" };
                })
                .catch((error) => {
                    console.log(error);
                })
        },

        // 밴드장 위임 하기
        reqModifyChief(context, info) {
            let frm = new FormData();
            frm.append("mandatecrewId", info.mandatecrewId);

            return axios
                .put("/band/" + info.crewId, frm)
                .then((response) => {
                    if (response.data.status) return { result: true, msg: "밴드장 위임이 완료되었습니다" };
                    else return { result: false, msg: "밴드장 위임에 실패했습니다" };
                })
                .catch((error) => {
                    console.log(error);
                })
        },

        //비디오리스트 가져오기
        reqVideoList(context, bandno) {
            return axios
                .post("/band/video", { bandid: bandno })
                .then((response) => {
                    if (response.data.status) {
                        context.commit("setVideoList", response.data.object);
                        return { result: true, msg: "비디오 목록 받아오기 성공" };
                    }
                    else return { result: false, msg: "비디오 목록을 불러오는데 실패했습니다" };

                })
                .catch((error) => console.log(error));
        },
    }
}

export default BandStore;