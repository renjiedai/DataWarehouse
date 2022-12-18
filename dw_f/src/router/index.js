import { createRouter, createWebHashHistory } from 'vue-router'
import SearchView from '../views/SearchView.vue'
import CooperateView from '../views/CooperateView.vue'
import TracebackView from '../views/TracebackView.vue'
import CombineView from '../views/CombineView.vue'

const routes = [
  {
    path: '/',
    redirect: '/searchmovie',
  },
  {
    path: '/searchmovie',
    name: 'searchmovie',
    component: SearchView
  },
  {
    path: '/traceback',
    name: 'traceback',
    component: TracebackView
  },
  {
    path: '/cooperate',
    name: 'cooperatesearch',
    component: CooperateView
  },
  {
    path: '/combineinfo',
    name: 'combineinfo',
    component: CombineView
  },

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
