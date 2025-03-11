import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { InfoVO, InfoForm, InfoQuery } from '@/api/video/info/types';

/**
 * 查询访问信息列表
 * @param query
 * @returns {*}
 */

export const listInfo = (query?: InfoQuery): AxiosPromise<InfoVO[]> => {
  return request({
    url: '/video/info/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询访问信息详细
 * @param id
 */
export const getInfo = (id: string | number): AxiosPromise<InfoVO> => {
  return request({
    url: '/video/info/' + id,
    method: 'get'
  });
};

/**
 * 新增访问信息
 * @param data
 */
export const addInfo = (data: InfoForm) => {
  return request({
    url: '/video/info',
    method: 'post',
    data: data
  });
};

/**
 * 修改访问信息
 * @param data
 */
export const updateInfo = (data: InfoForm) => {
  return request({
    url: '/video/info',
    method: 'put',
    data: data
  });
};

/**
 * 删除访问信息
 * @param id
 */
export const delInfo = (id: string | number | Array<string | number>) => {
  return request({
    url: '/video/info/' + id,
    method: 'delete'
  });
};
