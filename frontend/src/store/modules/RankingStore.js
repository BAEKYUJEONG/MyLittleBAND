import axios from "../../axios/axios-common";

const RankingStore = {
  namespaced: true,
  state: {
    ranking: [],
  },
  getters: {
    getRanking(state) {
      return state.ranking;
    }, // 전체 랭킹 얻기
  },
  mutations: {
    // 랭킹 store 셋팅
    setRanking(state, payload) {
      state.ranking = payload;
    },
  },
  actions: {
    // 랭킹 정보 요청
    reqRanking(context) {
      return axios
        .get("/ranking")
        .then((response) => {
          if (response.data.status)
            context.commit("setRanking", response.data.object);
        })
        .catch((error) => {
          // TODO: 에러 상황 정리해서 만들기
          console.log(error);
        });
    },
  },
};

export default RankingStore;
