import {request} from './http'

// 分页查询
export function getJobTypes(data){
  return request({
    url:'/jobType/list',
    method: 'post',
    data
  })
}
// 获取兼职种类信息
export function getById(typeId){
  return request({
    url:'/jobType/getById?typeId=' + typeId,
    method:'get',
  })
}


