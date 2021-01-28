import { createApp } from 'vue'
import App from './App.vue'

import 'element-plus/lib/theme-chalk/index.css';
import './styles/main.scss'

import ElementPlus from 'element-plus'

import router from './router'
import axios from './utils/axios'

const app = createApp(App)

app.use(router)
app.use(ElementPlus)

app.config.globalProperties.$axios = axios

app.mount('#app')
