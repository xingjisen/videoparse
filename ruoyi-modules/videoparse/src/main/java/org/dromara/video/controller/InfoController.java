package org.dromara.video.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.video.domain.vo.InfoVo;
import org.dromara.video.domain.bo.InfoBo;
import org.dromara.video.service.IInfoService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 访问信息
 *
 * @author Jason
 * @date 2025-03-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/video/info")
public class InfoController extends BaseController {

    private final IInfoService infoService;

    /**
     * 查询访问信息列表
     */
    @SaCheckPermission("video:info:list")
    @GetMapping("/list")
    public TableDataInfo<InfoVo> list(InfoBo bo, PageQuery pageQuery) {
        return infoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出访问信息列表
     */
    @SaCheckPermission("video:info:export")
    @Log(title = "访问信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(InfoBo bo, HttpServletResponse response) {
        List<InfoVo> list = infoService.queryList(bo);
        ExcelUtil.exportExcel(list, "访问信息", InfoVo.class, response);
    }

    /**
     * 获取访问信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("video:info:query")
    @GetMapping("/{id}")
    public R<InfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(infoService.queryById(id));
    }

    /**
     * 新增访问信息
     */
    @SaCheckPermission("video:info:add")
    @Log(title = "访问信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody InfoBo bo) {
        return toAjax(infoService.insertByBo(bo));
    }

    /**
     * 修改访问信息
     */
    @SaCheckPermission("video:info:edit")
    @Log(title = "访问信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody InfoBo bo) {
        return toAjax(infoService.updateByBo(bo));
    }

    /**
     * 删除访问信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("video:info:remove")
    @Log(title = "访问信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(infoService.deleteWithValidByIds(List.of(ids), true));
    }
}
