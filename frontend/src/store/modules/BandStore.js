import axios from "../../axios/axios-common";

const ExampleStore = {
    namespaced: true,
    state: {
        BandList: [], //유저의 소속 밴드 리스트
        MamberList: [],  //밴드의 멤버리스트
        VideoList : [],//밴드의 비디오리스트
        
    },
    getters: {
        //밴드리스트 반환
        getBandList(state) { return state.BandList; },
        //멤버리스트 반환
        getMemberList(state) { return state.MemberList; },
        //비디오리스트 반환
        getVideoList(state) { return state.VideoList; },
    },
    mutations: {
        //밴드리스트 설정
        setBandList(state, payload) { state.BandList = payload; },
        //밴드멤버들 설정
        setMemberList(state, payload) { state.MemberList = payload; },
        //비디오리스트 설정
        setVideoList(state, payload) { state.VideoList = payload;},
    },
    actions: {
        //밴드리스트 가져오기
        reqBandList(context, memberno) {
         return  axios
        .get("/band-list/" + memberno)
        .then((response) => {
            if(response.data.status){
                context.commit("setBandList", response.data.object);
            } else {
                console.log("실패")
            }
        })
        .catch((error) => console.log(error));
        },

        //밴드소속 멤버정보 가져오기
        reqMemberList(context,bandno) {
            return axios
            .get("/band/member/" + bandno)
                .then((response) => {
                    if (response.data.status) {
                        context.commit("setMemberList", response.data.object);
                    } else {
                        console.log("실패")
                     }
            })
            .catch((error) => console.log(error));
        },

        reqVideoList(context,bandno) {
            return axios
            .post("/band/video", { bandid: bandno })
            .then((response) => {
                if (response.data.status) {
                    context.commit("setVideoList", response.data.object);
                } else {
                    console.log("실패")
                 }
        })
        .catch((error) => console.log(error));
        }
    }
}

export default ExampleStore;