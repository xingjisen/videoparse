//
// /**
//  * 解析
//  * @param url
//  * @returns {*}
//  */
import {useDollarGet} from '~/../composables/useDollarFetchRequest.js';

export const parsing = (params) => {
  return useDollarGet('/video/getVideoDownloadData?url=' + encodeURIComponent(params.url) + '&type=' + params.type);
};
