import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/departmentList'
  },
  {
    path: '/departmentList',
    component: () => import('@/views/department/department-list.vue')
  },
  {
    path: '/userList',
    component: () => import('../views/user/user-list.vue')
  },
  {
    path: '/addDepartment',
    component: () => import('../views/department/add-department.vue')
  },
  {
    path: '/editDepartment',
    component: () => import('../views/department/edit-department.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
