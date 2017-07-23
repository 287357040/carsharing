import Vue from 'vue'
import Router from 'vue-router'
import home from '@/components/home/home'
import login from '@/components/login/login'
import awaitStatus from '@/components/status/awaitStatus'
import mapLocation from '@/components/public/mapLocation'
import owner from '@/components/mine/owner'
import plateNum from '@/components/setting/plateNum'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: { name: 'login' }
    },
    {
      path: '/home',
      name: 'home',
      component: home
    },
    {
      path: '/login',
      name: 'login',
      component: login,
      alias: '/' || '/login'
    },
    {
      path: '/await',
      component: awaitStatus,
    },

    {
      path: '/owner',
      name: 'owner',
      component: owner
    },
    {
      path: '/plateNum',
      component: plateNum
    },
    {
      path: '/mapLocation',
      component: mapLocation
    }
  ]
})
