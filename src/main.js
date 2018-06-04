import Vue from 'vue';
import App from './App';
import VConsole from 'vconsole'
import fastclick from 'fastclick'
import router from './router';
import axios from 'axios';
import token from './config';
import store from './store';
import 'common/js/flexible';


import {Alert,Loading,Msg} from 'vue-mobile-element';
Vue.use(Loading);
Vue.prototype.$alert=Alert;
Vue.prototype.$confirm=
Vue.prototype.$loading=Loading.service;
Vue.prototype.$msg=Msg;
Vue.prototype.$ajax = axios;
Vue.prototype.$token = token;

Vue.config.productionTip = false;
fastclick.attach(document.body);

let vConsole=new VConsole({maxLogNumber: 5000});  //console.log调试工具，发布时需注释

new Vue({
  el: '#app',
  router,
  render: h => h(App)
});
