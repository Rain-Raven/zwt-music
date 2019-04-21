package com.zxa.backgroud.modular.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zxa.backgroud.core.common.constant.factory.ConstantFactory;
import com.zxa.backgroud.core.common.page.LayuiPageFactory;
import com.zxa.backgroud.core.log.LogObjectHolder;
import com.zxa.backgroud.modular.system.entity.Category;
import com.zxa.backgroud.modular.system.entity.Goods;
import com.zxa.backgroud.modular.system.entity.GoodsImage;
import com.zxa.backgroud.modular.system.entity.SecondCategory;
import com.zxa.backgroud.modular.system.model.DeptDto;
import com.zxa.backgroud.modular.system.service.CategoryService;
import com.zxa.backgroud.modular.system.service.GoodsImageService;
import com.zxa.backgroud.modular.system.service.GoodsService;
import com.zxa.backgroud.modular.system.warpper.DeptWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 类别控制器
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    @Autowired(required = false)
    FastFileStorageClient fastFileStorageClient;

    private final String imagePath="120.79.225.94:8080";

    private String PREFIX = "/modular/system/goods/";

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsImageService goodsImageService;

    /**
     * 跳转到部门管理首页
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "category.html";
    }

    /**
     * 跳转到添加部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("/category_add")
    public String deptAdd() {
        return PREFIX + "category_add.html";
    }

    /**
     * 跳转到修改部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("/category_update")
    public String deptUpdate(@RequestParam("id") Long id) {

        if (ToolUtil.isEmpty(id)) {
            throw new RequestEmptyException();
        }

        //缓存部门修改前详细信息
        Goods goods = goodsService.getById(id);
        LogObjectHolder.me().set(goods);

        return PREFIX + "category_edit.html";
    }

    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Long id) {
        Goods goods = goodsService.getById(id);
        return goods;
    }

    @ResponseBody
    @PostMapping("/uploadPic")
    public Map uploadPic(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return new HashMap();
        }
        StorePath storePath=fastFileStorageClient.uploadFile(file.getInputStream(),file.getSize(),"png",null);
        String url=imagePath+"/"+storePath.getFullPath();
        Map map=new HashMap();
        map.put("url",url);
        return map;

    }


    /**
     * 新增部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseData add(Goods goods,String images) {
        goods.setSalesQuantity(0);
        this.goodsService.addCategory(goods);
        String[] imageArray=images.split(",");
        boolean start=true;
        for (String s:imageArray) {
            if (!StringUtils.isEmpty(s)){
                GoodsImage goodsImage=new GoodsImage();
                goodsImage.setGoodsId(goods.getId());
                goodsImage.setImage(s);
                if (start){
                    goodsImage.setType(1);
                    start=false;
                }
                else {
                    goodsImage.setType(2);
                }
                goodsImageService.addCategory(goodsImage);
            }
        }
        return SUCCESS_TIP;
    }

    /**
     * 获取所有部门列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "condition", required = false) String condition,
                       @RequestParam(value = "deptId", required = false) String deptId) {
        Page<Map<String, Object>> list = this.goodsService.list(condition, deptId);
        Page<Map<String, Object>> wrap = new DeptWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    @RequestMapping(value = "/getAll")
    @ResponseBody
    public List<Category> getAll() {
        return goodsService.getAll();
    }


    /**
     * 修改部门
     *
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public ResponseData update(Goods goods) {
        goodsService.editCategory(goods);
        return SUCCESS_TIP;
    }

    /**
     * 删除类别
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public ResponseData delete(@RequestParam Long id) {

        //缓存被删除的部门名称
        LogObjectHolder.me().set(ConstantFactory.me().getDeptName(id));

        goodsService.removeById(id);

        return SUCCESS_TIP;
    }
}

