import axios from "axios";

const ExampleStore = {
    namespaced: true,
    state: {
        /*
            todayTime: 오늘 시간
            shows: 오늘의 공연 정보를 담고 있는 배열 
            {
                title,
                startTime,
                bandname,
            }
        */
        todayTime: "",
        shows: [],
    },
    getters: {
        getTodayTime(state) { return state.todayTime; },
        getshows(state)     { return state.shows; },
    },
    mutations: {
        setTodayTime(state, payload) { state.todayTime = payload; },
        setShows(state, payload)     { state.shows = payload; },
    },
    actions: {
        reqTodayTime(context) {     // TodayTime 요청
            return axios
            .get('/todayTime')
            .then((response) => {
                context.commit("setTodayTime", response.data);
            })
            .catch((error) => {
                // 오류 설정은 나중에 백엔드와 협의해서 만들기
                console.log(error);
            })
        },
        reqShows(context) {         // shows 요청
            return axios
            .get('/todayShow')
            .them((response) => {

            })
            .catch((error) => {
                // 오류 설정은 나중에 백엔드와 협의해서 만들기
                console.log(error);
            })
        }
    }
}

export default ExampleStore;