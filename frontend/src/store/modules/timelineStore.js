import axios from "../../axios/axios-common";
import moment from "moment";

const timelineStore = {
  namespaced: true,
  state: {
    timeline: [],
  },
  getters: {
    getTimeline(state) {
      return state.timeline;
    },
  },
  mutations: {
    setTimeline(state, payload) {
      state.timeline = payload;
    },
  },
  actions: {
    reqTimeline(context) {
      // Timeline 요청
      return axios
        .get("/show/list/" + moment(new Date()).format("YYYY-MM-DD"))
        .then((response) => {
          console.log(response.data.object);
          context.commit("setTimeline", response.data.object);
        })
        .catch((error) => {
          // 오류 설정은 나중에 백엔드와 협의해서 만들기
          console.log(error);
        });
    },
  },
};

export default timelineStore;
