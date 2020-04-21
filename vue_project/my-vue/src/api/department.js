import request from '../utils/request'
// 这个文件就是前端vue，访问后台接口的入口， 里面的每个方法对应后端接口的方法

const group_name = 'department'
export default {
  findAll() {
    return request({
      url: `/${group_name}/findAll`,
      method: 'get'
    })
  },
  add(department) {
    return request({
      url: `/${group_name}/add`,
      method: 'post',
      data: department
    })
  },
  deleteById(id) {
    return request({
      url: `/${group_name}/deletedById//${id}`,
      method: 'delete',
    })
  },
  update(department) {
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: department
    })
  },
  getById(id) {
    return request({
      url: `/${group_name}/getById//${id}`,
      method: 'get',
    })
  },
  searchByName(searchName) {
    if (searchName === '') {
      // 转义 %
      searchName = '%25'
    }
    return request({
      url: `/${group_name}/searchByName//${searchName}`,
      method: `get`
    })
  }
}
