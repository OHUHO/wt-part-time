import {request} from './http'

// 分页查询::轮播图
export function getCarousel(data) {
  return request({
    url: '/carousel/list',
    method: 'post',
    data
  })
}
// 分页查询::兼职种类
export function getJobType(data) {
  return request({
    url: '/jobType/list',
    method: 'post',
    data
  })
}
// 商家排行榜
export function getBusiness(data){
  return request({
    url: '/business/list',
    method: 'post',
    data
  })
}



