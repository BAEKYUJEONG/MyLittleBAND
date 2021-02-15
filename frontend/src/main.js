import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/store'
import vuetify from './plugins/vuetify';
import VueMoment from 'vue-moment';

// moment.js 사용
Vue.use(VueMoment);
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  vuetify,
  router,
  store,
}).$mount('#app')