package org.dromara.video.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.video.domain.Info;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 访问信息视图对象 info
 *
 * @author Jason
 * @date 2025-03-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Info.class)
public class InfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 请求头
     */
    @ExcelProperty(value = "请求头")
    private String headers;

    /**
     * 位置
     */
    @ExcelProperty(value = "位置")
    private String getlocation;

    /**
     * ip地址
     */
    @ExcelProperty(value = "ip地址")
    private String ip;

    /**
     * 用户代理
     */
    @ExcelProperty(value = "用户代理")
    private String useragent;

    /**
     * 请求时间
     */
    @ExcelProperty(value = "请求时间")
    private Date reqTime;


}
