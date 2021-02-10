import axios from "../../axios/axios-common";

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
        timeline: [
            {
                no: 1,
                time: "12:00",
                band: "SURL",
                intro: "브리티시 록, 블루스 기반의 다양한 음악을 연주"
              },
              {
                no: 2,
                time: "14:00",
                band: "세이수미",
                intro: "부산 남포동의 한 찻집에서 '심심한데 밴드나 해보자'는 말 한마디에 꾸려진 밴드"
              },
              {
                no: 3,
                time: "17:00",
                band: "DASUTT",
                intro: "멤버가 다섯이었다가 네명이 되었다는 밴드, 빈디지하고 펑키한 사운드"
              },
              {
                no: 4,
                time: "20:00",
                band: "wetter",
                intro: "트렌디하면서도 전통적인 록 성향을 가진 밴드"
              },
              {
                no: 5,
                time: "22:00",
                band: "맥거핀",
                intro: "록 기반이지만 자신들의 색깔을 확고히 한 밴드"
              },
      
        ],
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