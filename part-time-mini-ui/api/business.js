import {request} from './http'

// 保存商家认证
export function saveBusiness(data){
  return request({
    url:'/business/save',
    method: 'post',
    data
  })
}
// 获取商家信息
export function getById(businessId,status){
  return request({
    url:'/business/getById?businessId=' + businessId + "&status=" + status,
    method:'get',
  })
}

// 分页查询商家信息
export function getBusiness(data){
  return request({
    url:'/business/list',
    method: 'post',
    data
  })
}

