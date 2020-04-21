<template>
  <div>

    <div>
      <el-form label-width="80px">
        <el-input v-model='searchName' placeholder="按名称搜索" style="width: 200px; padding-right: 10px;"></el-input>
        </el-form-item>
        <el-button type="primary" @click="searchByName()">搜索</el-button>
      </el-form>
      <el-table :data="departmentList" style="width: 100%">
        <el-table-column prop="id" label="编号" width="180"> </el-table-column>
        <el-table-column prop="name" label="部门" width="180"> </el-table-column>
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button type="primary" @click="toUpdate(scope.row.id)" icon="el-icon-edit" circle size="small">
            </el-button>
            <el-button type="danger" @click="deleteById(scope.row.id)" icon="el-icon-delete" circle size="small">
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog title="修改用户" :visible.sync="dialogFormVisible">
      <department-edit :department="department" @closeDialog="closeDialog" @getDepartmentList="getDepartmentList">
      </department-edit>
    </el-dialog>
  </div>
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
  import departmentApi from '../../api/department.js'
  import departmentEdit from './edit-department.vue'
  export default {

    data() {
      return {
        departmentList: [],
        department: {},
        dialogFormVisible: false,
        searchName: ''
      }
    },
    components: {
      departmentEdit
    },
    methods: {
      getDepartmentList() {
        // 调用api里面的方法，获取参数, 
        departmentApi.findAll().then(result => {
          // 调用department.js的departmentList方法。then接收回调回来的结果，放到res里
          this.departmentList = result
        })
      },

      deleteById(id) {
        this.$confirm('此操作删除该项, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          departmentApi.deleteById(id).then(res => {
            this.$message.success(res.msg)
            this.getDepartmentList()
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      toUpdate(id) {
        departmentApi.getById(id).then(res => {
          this.dialogFormVisible = true
          this.department = res
        })
      },
      closeDialog() {
        this.dialogFormVisible = false
      },
      searchByName() {
        departmentApi.searchByName(this.searchName).then(res => {
          this.departmentList = res
        })
      }


    },
    created() {
      this.getDepartmentList()
    }
  }
</script>