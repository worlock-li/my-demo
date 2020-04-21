import request from '../utils/request'
// 这个文件就是前端vue，访问后台接口的入口， 里面的每个方法对应后端接口的方法

const groupName = 'user'
export default {

  findAll() {
    return request({
      url: `/${groupName}/findAll`, // 这里使用的是 反引号 ``, 可以任意换行，可以使用模板表达式 ${}
      method: 'get'
    })
  },

  

}
