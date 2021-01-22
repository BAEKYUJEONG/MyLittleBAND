import Vue from "vue";
import Vuex from "vuex";

/*
    modules폴더의 module을 import하면 됨

    import Example from "./modules/ExampleStore.js"
*/

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
  }
});