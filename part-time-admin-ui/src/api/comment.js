import request from '@/utils/request'


// 不分页查询经验
export function getList(data){
  return request({
    url:'/comment/list',
    method: 'post',
    data
  })
}

// 删除经验
export function deleteComment(commentId){
  return request({
    url:'/comment/delete?commentId=' + commentId,
    method: 'delete',
  })
}


