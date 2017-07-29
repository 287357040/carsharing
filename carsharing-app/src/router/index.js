import Vue from 'vue'
import Router from 'vue-router'
import home from '@/components/home/home'
import login from '@/components/login/login'
import awaitStatus from '@/components/status/awaitStatus'
import mapLocation from '@/components/public/mapLocation'
import owner from '@/components/mine/owner'
import plateNum from '@/components/setting/plateNum'
import brandModel from '@/components/setting/brandModel'
import password from '@/components/mine/password'
import guide from '@/components/mine/guide'
import friend from '@/components/mine/friend'
import feedback from '@/components/mine/feedback'
import route from '@/components/mine/route'
import valuation from '@/components/mine/valuation'
import order from '@/components/mine/order'
<<<<<<< HEAD
import message from '@/components/message/info'
=======
import message  from '@/components/message/info'
import details  from '@/components/message/details'
>>>>>>> f2d39911a18a5a3ec20ac0d505c879d328926b59

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
      path: '/awaitStatus',
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
      path: '/brandModel',
      component: brandModel
    },
    {
      path: '/mapLocation',
      component: mapLocation
    },
    {
      path: '/password',
      name: 'password',
      component: password
    },
    {
      path: '/guide',
      name: 'guide',
      component: guide
    },
    {
      path: '/friend',
      name: 'friend',
      component: friend
    },
    {
      path: '/feedback',
      name: 'feedback',
      component: feedback
    },
    {
      path: '/route',
      name: 'route',
      component: route
    },
    {
      path: '/valuation',
      name: 'valuation',
      component: valuation
    },
    {
      path: '/order',
      name: 'order',
      component: order
    },
    {
      path: '/message',
      name: 'message',
      component: message
<<<<<<< HEAD
    }
=======
    },
    {
      path: '/details',
      name: 'details',
      component: details
    }  

>>>>>>> f2d39911a18a5a3ec20ac0d505c879d328926b59
  ]
})
