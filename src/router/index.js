import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router);

const _import = require('./_import_' + process.env.NODE_ENV);

let constantRouterMap=[
  {path:'/',redirect:'/home'},
  {path:'/login',component:_import('login/login')},
  {path:'/home',name:'home',component:_import('home/index/index'),children:[
    {path:'/Inbound',name:'Inbound',component:_import('inBound/inBound/inBound')}
  ]},
  {path:'/cs',component:_import('cs/index/index')}
];

export default new Router({
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
