import request from '@/utils/request'

export function upload(data) {
  return request({
    url: '/files/upload',
    method: 'post',
    data
  })
}
