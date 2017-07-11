import Vue from 'vue'
import Router from 'vue-router'
import home from '@/components/home/home'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/home',
      name: 'home',
      component: home,
      alias: '/' || '/home',
      children: [

      ]
    }
  ]
})
