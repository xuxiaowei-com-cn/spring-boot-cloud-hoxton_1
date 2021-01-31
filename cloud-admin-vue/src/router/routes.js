import Home from '../views/home/index.vue'

import Login from './login'
import { ElementPlus, Session } from './demo'

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/about/index.vue')
    },
    Login,
    ElementPlus,
    Session,
]

export default routes
