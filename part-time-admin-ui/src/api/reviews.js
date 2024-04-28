import request from '@/utils/request'

// 保存或更新评价信息
export function saveReviews(data){
    return request({
      url:'/reviews/save',
      method: 'post',
      data
    })
}
