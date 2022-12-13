import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:3445/'

createApp(App)
    .use(router)
    .use(store)
    .use(ElementPlus)
    .mount('#app')
