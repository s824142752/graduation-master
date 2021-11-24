import request from '@/utils/request'

export function createInfo(data) {
  return request({
    url: '/information/add',
    method: 'post',
    data
  })
}
export function listVoInfo(status) {
  return request({
    url: '/information/listByStatus',
    method: 'get',
    params: { status }
  })
}
/*
* 处理义工信息
* */
export function updateInfo(data) {
  return request({
    url: '/information/update',
    method: 'post',
    data
  })
}

export function voInfoDetail(id) {
  return request({
    url: '/information/getInformation',
    method: 'get',
    params: { id }
  })
}
