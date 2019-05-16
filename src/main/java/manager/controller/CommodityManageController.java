package manager.controller;

import manager.common.DeleteFile;
import manager.domain.Commodity;
import manager.domain.CommodityType;
import manager.service.ICommodityService;
import manager.service.ICommodityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/manage/commodity")
public class CommodityManageController {

    @Autowired
    private ICommodityService iCommodityService;

    @Autowired
    private ICommodityTypeService iCommodityTypeService;

    @Value("${imgPath}")
    private String imgPath;

    @Value("${imgPath2}")
    private String imgPath2;

    /**
     * 商品概览
     * @param model
     */
    @RequestMapping("")
    public String toCommodityByType(Model model){
        List<Commodity> commodities = iCommodityService.findCommodities();
        model.addAttribute("commodities",commodities);
        return "html/commodity/manager_commodity_index";
    }

    /**
     * 转到添加商品页面
     */
    @RequestMapping("/add")
    public String toCommodityAdd(Model model){
        List<CommodityType> typeList = iCommodityTypeService.findAllTypes();
        model.addAttribute("typeList",typeList);
        return "html/commodity/manager_commodity_add";
    }

    /**
     * 处理添加商品逻辑
     * @param request
     * @param model
     * @param image
     */
    @RequestMapping("/handleAdd")
    public String handleAddCommodity(HttpServletRequest request,Model model,
                                     @RequestParam("image") MultipartFile image){
        boolean flag = false;
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        int stock = Integer.parseInt(request.getParameter("stock"));

        //这里出现了点小问题，select提交的值是option的文本，这里需要加逻辑，将它变成商品类的id
        String tId = iCommodityTypeService.findTypeByName(request.getParameter("tId")).getId();


        File img = new File(imgPath+name+".jpg");
        File img2 = new File(imgPath2+name+".jpg");
        try {
            //新建临时文件，用来接受前端传回的文件，再复制到服务器上
            File transientFile = new File(imgPath+"trans.jpg");
            image.transferTo(transientFile);
            FileCopyUtils.copy(transientFile,img);
            FileCopyUtils.copy(transientFile,img2);

            String imageUrl = "../../image/commodity_image/"+name+".jpg";
            Commodity commodity = new Commodity(UUID.randomUUID().toString(),name,price,imageUrl,description,stock,0,tId);
            flag = iCommodityService.save(commodity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(flag){
            iCommodityTypeService.numAdd(tId);
            return toCommodityByType(model);
        }else {
            DeleteFile.deleteFile(img);
            DeleteFile.deleteFile(img2);
            return "redirect:/admin/manage/commodity/add";
        }
    }

    /**
     *删除商品逻辑
     * @param id
     */
    @RequestMapping("/del")
    public String DeleteCommodity(String id,Model model){
        try{Commodity commodity = iCommodityService.findCommodityById(id);
            String typeId = commodity.gettId();
            boolean flag = iCommodityService.delete(id);
            if(flag){
                iCommodityTypeService.numReduce(typeId);
            }
            return toCommodityByType(model);
        }catch (Exception e){
            return "redirect:/admin/manage/commodity/";
        }

    }




}
