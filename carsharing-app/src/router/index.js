import Vue from 'vue'
import Router from 'vue-router'
import home from '@/components/home/home'
import login from '@/components/login/login'
import mine from '@/components/mine/mine'
import awaitStatus from '@/components/status/awaitStatus'
import mapLocation from '@/components/public/mapLocation'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: { name: 'home' }
    },
    {
      path: '/home',
      name: 'home',
      component: home,
      alias: '/' || '/home',
    },
    {
      path: '/await',
      component: awaitStatus,
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/mine',
      name: 'mine',
      component: mine
    },
    {
      path: '/mapLocation',
      component: mapLocation
    }
  ]
})
