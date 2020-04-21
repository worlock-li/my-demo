<template>
  <div>
    <el-form :model="department" label-width="80px">
      
      <el-form-item label="部门">
        <!-- <el-select v-model="department.name" placeholder="选择部门" style="width: 200px">
          <el-option
            v-for="item in departmentList"
            :key="item.id"
            :label="item.name"
            :value="item.name"
          ></el-option>
        </el-select> -->
          <el-input v-model="department.name"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="updateDepartment">修改</el-button>
    </div>
  </div>
</template>

<script>
import departmentApi from '../../api/department.js'
export default {
  data() {
    return {
      departmentList: []
    }
  },
  props: {
    department: {
      type: Object,
      default: {}
    }
  },
  methods: {
    updateDepartment() {
      console.log(this.department)
      departmentApi.update(this.department).then(res => {
        this.$message.success(res.msg)
        this.$emit('closeDialog')
        this.$emit('getDepartmentList')
      })
    },

    getDepartmentList() {
        // 调用api里面的方法，获取参数, 
        departmentApi.findAll().then(result => {
          // 调用department.js的departmentList方法。then接收回调回来的结果，放到res里
          this.departmentList = result
        })
      },
  },
  created() {
    this.getDepartmentList()
  }
}
</script>
<style scoped>
</style>