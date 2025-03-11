package org.dromara.video.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.util.Date;

/**
 * 访问信息对象 info
 *
 * @author Jason
 * @date 2025-03-03
 */
@Data
@TableName("info")
public class Info {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 请求头
     */
    private String headers;

    /**
     * 位置
     */
    private String getlocation;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 用户代理
     */
    private String useragent;

    /**
     * 请求时间
     */
    private Date reqTime;


}
