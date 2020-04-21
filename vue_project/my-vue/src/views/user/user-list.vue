<template>
  <el-table :data="userList" style="width: 100%" >
    <el-table-column prop="userId" label="编号"> </el-table-column>
    <el-table-column prop="username" label="用户名"> </el-table-column>
    <el-table-column prop="userSex" label="性别"> 
      <template slot-scope="scope">
        <!-- scope.row 表示查询出来的所有数据 -->
        <span>{{scope.row.userSex === 1 ? '男' : '女'}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="userAge" label="年龄"> </el-table-column>
    <el-table-column prop="userBirthday" label="生日"> </el-table-column>
    <el-table-column prop="userDepartment" label="部门"> </el-table-column>
    <el-table-column prop="updateTime" label="修改时间"> 
      <template slot-scope="scope">
        <!-- scope.row 表示查询出来的所有数据 -->
        <span>{{scope.row.updateTime | handlerNullTime}}</span>
      </template>
    </el-table-column>
  </el-table>
</template>

<style>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>

<script>
  import userApi from '../../api/user.js'
  export default {
    methods: {
      tableRowClassName({row, rowIndex}) {
        if (rowIndex === 1) {
          return 'warning-row';
        } else if (rowIndex === 3) {
          return 'success-row';
        }
        return '';
      }
    },
    data() {
      return {
        userList: []
      }
    },
    methods: {
      getUserList() {
        // 调用api里面的方法，获取参数, 
        userApi.findAll().then(result => {
        // 调用department.js的departmentList方法。then接收回调回来的结果，放到res里
        this.userList = result
      })
      }
    },
    filters: {
      handlerNullTime(time) {
        if (!time) {
          return '未被修改'
        }
        return time
      }
    },
    created() {
      this.getUserList()
    }
  }
</script>

