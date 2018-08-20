import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router);

const _import = require('./_import_' + 'development');

let constantRouterMap=[
  {path:'/',redirect:'/Login'},
  {path:'/Login',name:'Login',component:_import('Login/Login')},
  {path:'/Home',name:'Home',component:_import('home/Index/Index')},
  {path:'/Inbound',name:'Inbound',component:_import('InBound/InBound/InBound'),meta:{keepAlive:true}},
  {path:'/Details',name:'Details',component:_import('Common/Details/Details'),meta:{keepAlive:true}},
  {path:'/AddDetails',name:'AddDetails',component:_import('InBound/AddDetails/AddDetails')},
  {path:'/ClothesList',name:'ClothesList',component:_import('InBound/ClothesList/ClothesList'),meta:{keepAlive:true}},
  {path:'/Receipt',name:'Receipt',component:_import('InBound/Receipt/Receipt')},
  {path:'/HangUps',name:'HangUps',component:_import('HangUps/HangUps'),meta:{keepAlive:true}},
  {path:'/MallDetails',name:'MallDetails',component:_import('InBound/MallDetail/MallDetails')},
  {path:'/AllOrder',name:'AllOrder',component:_import('AllOrder/AllOrder'),meta:{keepAlive:true}},
  {path:'/HangUpsList',name:'HangUpsList',component:_import('HangUps/HangUpsList')},
  {path:'/AllOrderDetails',name:'AllOrderDetails',component:_import('Common/Details/AllOrderDetails')}
  /*{path:'/HangUpDe',name:'HangUpDe',component:_import('Common/Details/HangUpDe'),meta:{keepAlive:true}},
    {path:'/AddClothes',name:'AddClothes',component:_import('InBound/AddClothes/AddClothes'),meta:{keepAlive:true}},
    {path:'/AddChildren',name:'AddChildren',component:_import('InBound/AddClothes/AddChildren')},
    {path:'/HangUpClothes',name:'HangUpClothes',component:_import('Common/HangUpClothes')},
    {path:'/HangUp',name:'HangUp',component:_import('HangUps/HangUp'),meta:{keepAlive:true}},
    {path:'/HangUpList',name:'HangUpList',component:_import('HangUps/HangUpList'),meta:{keepAlive:true}},*/
];

export default new Router({
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
