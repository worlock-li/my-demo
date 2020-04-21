import axios from 'axios'
import { Message } from 'element-ui'
const service = axios.create({
  baseURL: '/',
  timeout: 5000 // 默认请求超时时间
})

// request 拦截器
// 在请求发送前对请求进行一些处理
service.interceptors.request.use(
  config => {
    return config
  },
  error => {
    Message({
      type: 'error',
      message: '后端程序崩溃'
    })
    return Promise.reject(error)
  }
)

// response 拦截器
// 在接口响应后，页面获取响应前，对响应结果进行处理
service.interceptors.response.use(
  response => {
    const res = response.data
    // 当前端请求后端接口时， 返回值里面带有 code 字段， 当code的值为 200 时， 表示接口是通的
    // 这里的判断是 通过后台的返回值设置来进行的，后台将查询出来的数据经过一个类来封装，这个类中包含一个 code属性
    // 通过 后台的 try-catch 来设置 code 的值
    if (res != null || res != '') {
      Message({
        type: 'success',
        message: '查询成功'
      })
      return res
      
    } else {
      // 接口异常
      Message({
        type: 'warning',
        message: res.msg + '-----'
      })
    }
  },
  error => {
    // http请求报错
    Message({
      type: 'error',
      message: 'http请求错误'
    })
  }
)

export default service
