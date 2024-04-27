import request from '@/utils/request'

// 保存或更新
export function saveCarousel(data) {
  return request({
    url: '/carousel/save',
    method: 'post',
    data
  })
}

// 分页查询
export function getCarousel(data) {
    return request({
      url: '/carousel/list',
      method: 'post',
      data
    })
}

// 删除
export function deleteCarousel(carouselId){
    return request({
        url:'/carousel/delete?carouselId=' + carouselId,
        method:'delete',
    })
}

// 通过ID查询
export function getById(carouselId){
    return request({
        url:'/carousel/getById?carouselId=' + carouselId,
        method:'get',
    })
}