package com.zxa.backgroud.modular.system.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxa.backgroud.core.common.exception.BizExceptionEnum;
import com.zxa.backgroud.core.common.page.LayuiPageFactory;
import com.zxa.backgroud.modular.system.entity.Category;
import com.zxa.backgroud.modular.system.entity.Goods;
import com.zxa.backgroud.modular.system.mapper.CategoryMapper;
import com.zxa.backgroud.modular.system.mapper.GoodsMapper;
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
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> {

    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 新增部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void addCategory(Goods goods) {

        if (ToolUtil.isOneEmpty(goods, goods.getName(), goods.getPrice(), goods.getInventory(),goods.getCategoryId())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.save(goods);
    }

    /**
     * 修改部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editCategory(Goods goods) {
        if (ToolUtil.isOneEmpty(goods, goods.getName(), goods.getPrice(), goods.getInventory(),goods.getCategoryId())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        this.updateById(goods);
    }


    /**
     * 获取所有部门列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public Page<Map<String, Object>> list(String condition, String deptId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.goodsMapper.list(page, condition, deptId);
    }

    public List<Category> getAll() {
        return goodsMapper.getAll();
    }

}
