package com.zxa.backgroud.modular.system.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxa.backgroud.core.common.exception.BizExceptionEnum;
import com.zxa.backgroud.core.common.page.LayuiPageFactory;
import com.zxa.backgroud.modular.system.entity.Category;
import com.zxa.backgroud.modular.system.entity.SecondCategory;
import com.zxa.backgroud.modular.system.mapper.CategoryMapper;
import com.zxa.backgroud.modular.system.mapper.SecondCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author zxa
 * @since 2018-12-07
 */
@Service
public class SecondCategoryService extends ServiceImpl<SecondCategoryMapper, SecondCategory> {

    @Resource
    private SecondCategoryMapper secondCategoryMapper;

    /**
     * 新增部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void addCategory(SecondCategory category) {

        if (ToolUtil.isOneEmpty(category, category.getName(), category.getSort(), category.getStatus())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.save(category);
    }

    /**
     * 修改部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editCategory(SecondCategory category) {
        if (ToolUtil.isOneEmpty(category, category.getName(), category.getSort(), category.getStatus())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        this.updateById(category);
    }



    /**
     * 获取所有部门列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public Page<Map<String, Object>> list(String condition, String deptId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.secondCategoryMapper.list(page, condition, deptId);
    }

    public List<SecondCategory> getAll() {
        return secondCategoryMapper.getAll();
    }
}
