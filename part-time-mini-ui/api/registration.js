import {request} from './http'

// 分页查询
export function getRegistration(data){
  return request({
    url:'/registration/list',
    method: 'post',
    data
  })
}
// 获取用户是否已经报名了该兼职
export function registrationEd(jobId,userId){
  return request({
    url:'/registration/registrationEd?jobId=' + jobId + "&userId=" + userId,
    method:'get',
  })
}
// 保存兼职报名信息
export function saveRegistration(data){
  return request({
    url:'/registration/save',
    method: 'post',
    data
  })
}

