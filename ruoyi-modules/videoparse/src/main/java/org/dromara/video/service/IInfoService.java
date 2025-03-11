package org.dromara.video.service;

import org.dromara.video.domain.vo.InfoVo;
import org.dromara.video.domain.bo.InfoBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 访问信息Service接口
 *
 * @author Jason
 * @date 2025-03-03
 */
public interface IInfoService {

    /**
     * 查询访问信息
     *
     * @param id 主键
     * @return 访问信息
     */
    InfoVo queryById(Long id);

    /**
     * 分页查询访问信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 访问信息分页列表
     */
    TableDataInfo<InfoVo> queryPageList(InfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的访问信息列表
     *
     * @param bo 查询条件
     * @return 访问信息列表
     */
    List<InfoVo> queryList(InfoBo bo);

    /**
     * 新增访问信息
     *
     * @param bo 访问信息
     * @return 是否新增成功
     */
    Boolean insertByBo(InfoBo bo);

    /**
     * 修改访问信息
     *
     * @param bo 访问信息
     * @return 是否修改成功
     */
    Boolean updateByBo(InfoBo bo);

    /**
     * 校验并批量删除访问信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
