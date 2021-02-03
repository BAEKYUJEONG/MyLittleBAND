import Vue from "vue";
import Vuex from "vuex";

// store module import
import MemberStore from "./modules/MemberStore"
import timelineStore from "./modules/timelineStore";
import RankingStore from "./modules/RankingStore";

//yarn add vuex-persistedstate 필요 
import createPersistedState from 'vuex-persistedstate';//새로고침해도 store 정보가 사라지지 않게 해줌

// import jwt_decode from "jwt-decode";

// Vuex 사용
Vue.use(Vuex);

export default new Vuex.Store({
  modules: {      // imported modules
    timelineStore,
    RankingStore,
    MemberStore
  },
  plugins: [
    createPersistedState({//store 내부 정보가 날아가지 않도록 해줌
      paths: ['MemberStore']//원하는 모듈별로 추가가능
    })
  ]
});