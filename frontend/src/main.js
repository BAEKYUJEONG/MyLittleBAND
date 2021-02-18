import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store/store';
import vuetify from './plugins/vuetify';
import VueMoment from 'vue-moment';
import font4size from './assets/font/4size/font.css';
Vue.config.productionTip = false;

// 카카오 링크 사용
window.Kakao.init('02edea6a07b8bac0a336b0e60add8969');

// moment.js 사용
Vue.use(VueMoment);
Vue.config.productionTip = false

new Vue({
  render: (h) => h(App),
  vuetify,
  router,
  store,
  font4size,
}).$mount('#app');
