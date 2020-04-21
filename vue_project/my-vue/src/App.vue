<template>
  <div id="app">

    <!-- <div>
      <el-row class="tac">
      <el-col :span="12">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>导航一</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">导航二</span>
          </el-menu-item>
          <el-menu-item index="3" disabled>
            <i class="el-icon-document"></i>
            <span slot="title">导航三</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-setting"></i>
            <span slot="title">导航四</span>
          </el-menu-item>
        </el-menu>
      </el-col>

      </el-row>
    </div> -->
    <div>
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal"
        background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" :router='true'>
        <el-submenu index="1">
          <template slot="title">部门管理</template>
          <el-menu-item index="1-1" :route="'/departmentList'">部门列表</el-menu-item>
          <el-menu-item index="1-1" :route="'/addDepartment'">添加部门</el-menu-item>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">用户管理</template>
          <el-menu-item index="2-1" :route="'/userList'">用户列表</el-menu-item>
          <el-menu-item index="2-2">添加用户</el-menu-item>
        </el-submenu>
      </el-menu>

      <router-view />
    </div>


  </div>

</template>

<script>
  import departmentApi from './api/department.js'
  export default {
    data() {
      return {
        activeIndex: '1'
      };
    },
    methods: {
      
      getDepartmentList() {
        departmentApi.findAll().then(res => {
          this.$store.commit('SET_DEPARTMENT_LIST', res.data)
        })
      }
    },
    
    created() {
      this.getDepartmentList()
    }
  }
</script>