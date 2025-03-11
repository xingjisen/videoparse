export interface InfoVO {
  /**
   * 主键
   */
  id: string | number;

  /**
   * 请求头
   */
  headers: string;

  /**
   * 位置
   */
  getlocation: string;

  /**
   * ip地址
   */
  ip: string;

  /**
   * 用户代理
   */
  useragent: string;

  /**
   * 请求时间
   */
  reqTime: string;

}

export interface InfoForm extends BaseEntity {
  /**
   * 主键
   */
  id?: string | number;

  /**
   * 请求头
   */
  headers?: string;

  /**
   * 位置
   */
  getlocation?: string;

  /**
   * ip地址
   */
  ip?: string;

  /**
   * 用户代理
   */
  useragent?: string;

  /**
   * 请求时间
   */
  reqTime?: string;

}

export interface InfoQuery extends PageQuery {

  /**
   * 请求头
   */
  headers?: string;

  /**
   * 位置
   */
  getlocation?: string;

  /**
   * ip地址
   */
  ip?: string;

  /**
   * 用户代理
   */
  useragent?: string;

  /**
   * 请求时间
   */
  reqTime?: string;

    /**
     * 日期范围参数
     */
    params?: any;
}



