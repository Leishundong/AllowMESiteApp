import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router);

const _import = require('./_import_' + process.env.NODE_ENV);

let constantRouterMap=[
  {path:'/',redirect:'/Login'},
  {path:'/Login',component:_import('Login/Login')},
  {path:'/Home',name:'Home',component:_import('Home/Index/Index')},
  {path:'/Inbound',name:'Inbound',component:_import('InBound/InBound/InBound'),meta:{keepAlive:true}},
  {path:'/Details',name:'Details',component:_import('Common/Details/Details'),meta:{keepAlive:true}},
  {path:'/AddClothes',name:'AddClothes',component:_import('InBound/AddClothes/AddClothes'),meta:{keepAlive:true}},
  {path:'/AddChildren',name:'AddChildren',component:_import('InBound/AddClothes/AddChildren')},
  {path:'/AddDetails',name:'AddDetails',component:_import('InBound/AddDetails/AddDetails')},
  {path:'/ClothesList',name:'ClothesList',component:_import('InBound/ClothesList/ClothesList'),meta:{keepAlive:true}},
  {path:'/Receipt',name:'Receipt',component:_import('InBound/Receipt/Receipt')},
  {path:'/HangUpClothes',name:'HangUpClothes',component:_import('Common/HangUpClothes')},
  {path:'/HangUps',name:'HangUps',component:_import('HangUps/HangUps')},

];

export default new Router({
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
