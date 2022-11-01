import { createApp } from 'vue';
import { createPinia } from 'pinia';

import App from './App.vue';
import router from './router';

// import './assets/main.css';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
// import 'bootstrap/dist/css/bootstrap-utilities.css';

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';
import BootstrapVue from 'bootstrap-vue-3';
// import 'vue-fullpage.js/dist/style.css';
// import VueFullPage from 'vue-fullpage.js';
// import './assets/custom.css';

import axios from 'axios';
axios.defaults.xsrfCookieName = 'XSRF-TOKEN';
axios.defaults.xsrfHeaderName = 'X-XSRF-TOKEN';
const app = createApp(App);

app.config.globalProperties.axios = axios;

app.use(createPinia());
app.use(router);
app.use(ElementPlus);
// app.use(VueFullPage);
app.use(BootstrapVue);

app.mount('#app');
