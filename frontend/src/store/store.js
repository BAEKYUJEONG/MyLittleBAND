import Vue from "vue";
import Vuex from "vuex";

import MemberStore from "./modules/MemberStore.js"
//yarn add vuex-persistedstate 필요 
import createPersistedState from 'vuex-persistedstate';//새로고침해도 store 정보가 사라지지 않게 해줌

/*
    modules폴더의 module을 import하면 됨

    import Example from "./modules/ExampleStore.js"
*/
import TodayTimelineStore from "./modules/TodayTimelineStore.js";

// import jwt_decode from "jwt-decode";

// Vuex 사용
Vue.use(Vuex);


export default new Vuex.Store({
  modules: {
    /*
        import했던 모듈을 넣어주면 됨

        ExampleStore1,
        ExampleStore2,
    */
    TodayTimelineStore,
    MemberStore
  },
  plugins: [
    createPersistedState({//store 내부 정보가 날아가지 않도록 해줌
      paths: ['MemberStore']//원하는 모듈별로 추가가능
    })
  ]
});