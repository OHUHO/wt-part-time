import request from '@/utils/request'


// 分页查询经验
export function getExperience(data){
  return request({
    url:'/experience/list',
    method: 'post',
    data
  })
}

// 删除经验
export function deleteExperience(experienceId){
  return request({
    url:'/experience/delete?experienceId=' + experienceId,
    method: 'delete',
  })
}




