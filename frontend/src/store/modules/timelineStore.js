import axios from "axios";

const timelineStore = {
    namespaced: true,
    state: {
        
        /*
            todayTime: 오늘 시간
            shows: 오늘의 공연 정보를 담고 있는 배열 
            {
                title,
                time,
            }
        */
        timeline: [],
    },
    getters: {
        getTimeline(state)     { return state.timeline; },
    },
    mutations: {
        setTimeline(state, payload)     { state.timeline = payload; },
    },
    actions: {
        reqTimeline(context, date) {         // Timeline 요청
            return axios
            .get('/timeline/'+date)
            .them((response) => {
                context.commit("setTimeline", response.data);
            })
            .catch((error) => {
                // 오류 설정은 나중에 백엔드와 협의해서 만들기
                console.log(error);
            })
        }
    }
}

export default timelineStore;