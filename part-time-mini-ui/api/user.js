import {request} from './http'

// 登录
export function login(data){
  return request({
    url:'/user/login',
    method: 'post',
    data
  })
}
// 获取用户信息
export function getById(userId){
  return request({
    url:'/user/getById?userId=' + userId,
    method:'get',
  })
}


