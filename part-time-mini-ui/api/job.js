import {request} from './http'

// 分页查询
export function getJobs(data){
  return request({
    url:'/job/list',
    method: 'post',
    data
  })
}
// 获取兼职信息
export function getById(jobId,status=''){
  return request({
    url:'/job/getById?jobId=' + jobId + "&status=" + status,
    method:'get',
  })
}
// 保存兼职信息
export function saveJob(data){
  return request({
    url:'/job/save',
    method: 'post',
    data
  })
}

