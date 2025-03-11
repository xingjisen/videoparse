package org.dromara.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.video.domain.Info;
import org.dromara.video.domain.bo.InfoBo;
import org.dromara.video.domain.vo.InfoVo;
import org.dromara.video.mapper.InfoMapper;
import org.dromara.video.service.IInfoService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 访问信息Service业务层处理
 *
 * @author Jason
 * @date 2025-03-03
 */
@RequiredArgsConstructor
@Service
public class InfoServiceImpl implements IInfoService {

    private final InfoMapper baseMapper;

    /**
     * 查询访问信息
     *
     * @param id 主键
     * @return 访问信息
     */
    @Override
    public InfoVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询访问信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 访问信息分页列表
     */
    @Override
    public TableDataInfo<InfoVo> queryPageList(InfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Info> lqw = buildQueryWrapper(bo);
        Page<InfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的访问信息列表
     *
     * @param bo 查询条件
     * @return 访问信息列表
     */
    @Override
    public List<InfoVo> queryList(InfoBo bo) {
        LambdaQueryWrapper<Info> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Info> buildQueryWrapper(InfoBo bo) {
        LambdaQueryWrapper<Info> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(Info::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getHeaders()), Info::getHeaders, bo.getHeaders());
        lqw.eq(StringUtils.isNotBlank(bo.getGetlocation()), Info::getGetlocation, bo.getGetlocation());
        lqw.eq(StringUtils.isNotBlank(bo.getIp()), Info::getIp, bo.getIp());
        lqw.eq(StringUtils.isNotBlank(bo.getUseragent()), Info::getUseragent, bo.getUseragent());
        lqw.eq(bo.getReqTime() != null, Info::getReqTime, bo.getReqTime());
        return lqw;
    }

    /**
     * 新增访问信息
     *
     * @param bo 访问信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(InfoBo bo) {
        Info add = MapstructUtils.convert(bo, Info.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改访问信息
     *
     * @param bo 访问信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(InfoBo bo) {
        Info update = MapstructUtils.convert(bo, Info.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Info entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除访问信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
