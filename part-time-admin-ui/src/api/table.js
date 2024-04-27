import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/part-time/table/list',
    method: 'get',
    params
  })
}
