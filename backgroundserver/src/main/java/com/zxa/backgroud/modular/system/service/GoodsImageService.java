package com.zxa.backgroud.modular.system.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxa.backgroud.core.common.exception.BizExceptionEnum;
import com.zxa.backgroud.core.common.page.LayuiPageFactory;
import com.zxa.backgroud.modular.system.entity.Category;
import com.zxa.backgroud.modular.system.entity.Goods;
import com.zxa.backgroud.modular.system.entity.GoodsImage;
import com.zxa.backgroud.modular.system.mapper.GoodsImageMapper;
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
public class GoodsImageService extends ServiceImpl<GoodsImageMapper, GoodsImage> {

    @Transactional(rollbackFor = Exception.class)
    public void addCategory(GoodsImage image) {
        if (ToolUtil.isOneEmpty(image, image.getImage(), image.getType(), image.getGoodsId())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.save(image);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editCategory(GoodsImage image) {
        if (ToolUtil.isOneEmpty(image, image.getImage(), image.getType(), image.getGoodsId())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.updateById(image);
    }

}
