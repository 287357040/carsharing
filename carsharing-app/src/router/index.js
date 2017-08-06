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
import message  from '@/components/message/info'
import details  from '@/components/message/details'
import accountInfo  from '@/components/setting/accountInfo'
import mobileNum  from '@/components/setting/mobileNum'
import nickName  from '@/components/setting/nickName'
import license  from '@/components/setting/license'
import finishEvaluate  from '@/components/status/finishEvaluate'
import unfinishedEvaluate  from '@/components/status/unfinishedEvaluate'
import driverComment from '@/components/setting/driverComment'
import passengerComment from '@/components/setting/passengerComment'
import LocalStorage from '../utils/store' 
Vue.use(Router);

var router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: { name: 'login' }
    },
    {
      path: '/home',
      name: 'home',
      component: home,
      meta: { requiresAuth: true }
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
      meta: { requiresAuth: true }
    },

    {
      path: '/owner',
      name: 'owner',
      component: owner,
      meta: { requiresAuth: true }
    },
    {
      path: '/plateNum',
      component: plateNum,
      meta: { requiresAuth: true }
    },
    {
      path: '/brandModel',
      component: brandModel,
      meta: { requiresAuth: true }
    },
    {
      path: '/accountInfo',
      component: accountInfo,
      meta: { requiresAuth: true }
    },
    {
      path: '/mobileNum',
      component: mobileNum,
      meta: { requiresAuth: true }
    },
    {
      path: '/nickName',
      component: nickName,
      meta: { requiresAuth: true }
    },
    {
      path: '/license',
      name: 'license',
      component: license,
      meta: { requiresAuth: true }
    },
    {
      path: '/mapLocation',
      component: mapLocation,
      meta: { requiresAuth: true }
    },
    {
      path: '/password',
      name: 'password',
      component: password,
      meta: { requiresAuth: true }
    },
    {
      path: '/guide',
      name: 'guide',
      component: guide,
      meta: { requiresAuth: true }
    },
    {
      path: '/friend',
      name: 'friend',
      component: friend,
      meta: { requiresAuth: true }
    },
    {
      path: '/feedback',
      name: 'feedback',
      component: feedback,
      meta: { requiresAuth: true }
    },
    {
      path: '/route',
      name: 'route',
      component: route,
      meta: { requiresAuth: true }
    },
    {
      path: '/valuation',
      name: 'valuation',
      component: valuation,
      meta: { requiresAuth: true }
    },
    {
      path: '/finishEvaluate',
      name: 'finishEvaluate',
      component: finishEvaluate,
      meta: { requiresAuth: true }
    },
    {
      path: '/unfinishedEvaluate',
      name: 'unfinishedEvaluate',
      component: unfinishedEvaluate,
      meta: { requiresAuth: true }
    },
    {
      path: '/order',
      name: 'order',
      component: order,
      meta: { requiresAuth: true }
    },
    {
      path: '/message',
      name: 'message',
      component: message,
      meta: { requiresAuth: true }
    },
    {
      path: '/details/:msgTitle/:AddTime/:msgContent',
      name: 'details',
      component: details,
      meta: { requiresAuth: true }
    },
    {
      path: '/license',
      name: 'license',
      component: license,
      meta: { requiresAuth: true }
    },
    {
      path: '/driverComment',
      name: 'driverComment',
      component: driverComment,
      meta: { requiresAuth: true }
    },
    {
      path: '/passengerComment',
      name: 'passengerComment',
      component: passengerComment,
      meta: { requiresAuth: true }
    }
        
  ]
})
router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    if (!LocalStorage.fetch("user")) {
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      })
    } else {
      next()
    }
  } else {
    next() // 确保一定要调用 next()
  }
})

export default router 
