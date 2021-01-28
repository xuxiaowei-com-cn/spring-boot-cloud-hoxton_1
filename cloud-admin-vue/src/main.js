import { createApp } from 'vue'
import App from './App.vue'

import 'element-plus/lib/theme-chalk/index.css';
import './styles/main.scss'

import ElementPlus from 'element-plus'

import router from './router'
import request from './utils/request'

const app = createApp(App)

app.use(router)
app.use(ElementPlus)

app.config.globalProperties.$request = request

app.mount('#app')
