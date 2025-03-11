package org.dromara.video.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.video.domain.Info;

import java.util.Date;

/**
 * 访问信息业务对象 info
 *
 * @author Jason
 * @date 2025-03-03
 */
@Data
@AutoMapper(target = Info.class, reverseConvertGenerate = false)
public class InfoBo {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = {EditGroup.class})
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
