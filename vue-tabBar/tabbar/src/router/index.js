import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const Home = () => import("../views/home/Home");
const Shop = () => import("../views/shop/Shop");
const Class = () => import("../views/class/Class");
const Mine = () => import("../views/mine/Mine");

const routes = [
  {
    path: '',
    redirect: '/home'
  },
  {
    path: '/home',
    component: Home
  },
  {
    path: '/shop',
    component: Shop
  },
  {
    path: '/class',
    component: Class
  },
  {
    path: '/mine',
    component: Mine
  }
];

export default new Router({
  routes,
  mode: 'history'
})
