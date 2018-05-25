import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router);

const _import = require('./_import_' + process.env.NODE_ENV);

let constantRouterMap=[
  {path:'/',redirect:'/Home'},
  {path:'/Login',component:_import('Login/Login')},
  {path:'/Home',name:'Home',component:_import('Home/Index/Index'),children:[
    {path:'/Inbound',name:'Inbound',component:_import('InBound/InBound/InBound')}
  ]},
  {path:'/Details',name:'Details',component:_import('Common/Details/Details'),meta:{keepAlive:true}},
  {path:'/AddClothes',name:'AddClothes',component:_import('InBound/AddClothes/AddClothes'),meta:{keepAlive:true}},
  {path:'/AddChildren',name:'AddChildren',component:_import('InBound/AddClothes/AddChildren')}
];

export default new Router({
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
