<template>
  <div>
    <el-form :model="department" label-width="80px">
      <el-form-item label="部门名称">
        <el-input v-model="department.name" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="add()">保存</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import departmentApi from '../../api/department.js'
export default {
  data() {
    return {
      department: {}
    }
  },
  methods: {
    add() {
      departmentApi.add(this.department).then(res => {
        this.$message.success(res.msg)
        this.$router.push("/departmentList")
      })
    }
  }
}
</script>
<style scoped>
.page-component {
  margin-top: 15px;
}
</style>