package manager.controller;

import manager.domain.CommodityType;
import manager.service.ICommodityService;
import manager.service.ICommodityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 商品种类后台管理，包括新增，删除，总览
 * //后期可改进，增加修改接口，但是没有太大必要
 */
@Controller
@RequestMapping("/admin/manage/commodity/type")
public class CommodityTypeManageController {

    @Autowired
    private ICommodityTypeService iCommodityTypeService;

    @Autowired
    private ICommodityService iCommodityService;

    /**
     * 跳转到商品分类管理首页
     * @param model
     */
    @RequestMapping("")
    public String toCommodityTypeManage(Model model){
        List<CommodityType> commodityTypes = iCommodityTypeService.findAllTypes();
        model.addAttribute("commodityTypes",commodityTypes);
        return "html/commodity_type/manager_commodity_type_index";
    }

    /**
     *跳转到商品类添加页面
     */
    @RequestMapping("/add")
    public String toCommodityTypeAdd(){
        return "html/commodity_type/manager_commodity_type_add";
    }

    /**
     *处理商品类增加逻辑，没有信号返回，如果添加失败，则重定向到添加页面；如果成功，重定向到分类总览
     */
    @RequestMapping("/handleAdd")
    public String handleCommodityTypeAdd(String typeName){
        boolean flag = iCommodityTypeService.addType(typeName);
        if(!flag){//保存未成功,则返回添加界面
            return "redirect:/admin/manage/commodity/type/add";
        }else {
            return "redirect:/admin/manage/commodity/type";
        }
    }

    /**
     * 删除商品分类,同时删除该类下所有商品
     * @param id
     */
    @RequestMapping("/del")
    public String deleteCommodityType(String id){
        iCommodityTypeService.delete(id);
        iCommodityService.deleteByTypeId(id);
        return "redirect:/admin/manage/commodity/type";
    }

    /**
     * 跳转到更改商品类名页面
     * @param id
     * @param model
     */
    @RequestMapping("/change")
    public String toChangeCommodityType(String id, Model model){
        CommodityType commodityType = iCommodityTypeService.findTypeById(id);
        model.addAttribute("commodityType",commodityType);
        return "html/commodity_type/manager_commodity_type_change";
    }

    /**
     * 处理更改商品类名逻辑
     * @param id
     * @param typeName
     * @param model
     */
    @RequestMapping("/handleChange")
    public String handleChangeCommodityType(String id,String typeName,Model model){
        boolean flag = iCommodityTypeService.changeTypeName(id,typeName);
        if(flag){
            return "redirect:/admin/manage/commodity/type";
        }else {
            return toChangeCommodityType(id,model);
        }
    }

}
