import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Vuex from 'vuex'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    departmentList: [],
    userList: []
  },
  mutations: {
    // 规范：mutations全部大写下划线规则，这样可以区分方法
    SET_DEPARTMENT_LIST(state, val) {
      state.departmentList = val
    },

    SET_USER_LIST(state, val) {
      state.userList = val
    }
  },
  getters: {
    getDepartmentList() {
      return store.state.departmentList
    },
    getUserList() {
      return store.state.userList
    } 
  }
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')