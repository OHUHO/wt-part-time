import request from '@/utils/request'

// 保存或更新
export function saveBusiness(data) {
  return request({
    url: '/business/save',
    method: 'post',
    data
  })
}

// 分页查询
export function getBusiness(data) {
    return request({
      url: '/business/list',
      method: 'post',
      data
    })
}

// 删除
export function deleteBusiness(businessId){
    return request({
        url:'/business/delete?businessId=' + businessId,
        method:'delete',
    })
}

// 通过ID查询
export function getById(businessId,status){
    return request({
        url:'/business/getById?businessId=' + businessId + "&status=" + status,
        method:'get',
    })
}
