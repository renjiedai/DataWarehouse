import { createRouter, createWebHashHistory } from 'vue-router'
import SearchView from '../views/SearchView.vue'
import CooperateView from '../views/CooperateView.vue'
import MovieinfoView from '../views/MovieinfoView.vue'
import TracebackView from '../views/TracebackView.vue'


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
    path: '/movieinfo',
    name: 'movieinfo',
    component: MovieinfoView
  },
  {
    path: '/traceback',
    name: 'cooperatesearch',
    component: TracebackView
  },
  {
    path: '/cooperate',
    name: 'cooperatesearch',
    component: CooperateView
  },

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
