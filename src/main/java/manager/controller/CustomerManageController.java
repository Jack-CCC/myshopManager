package manager.controller;

import manager.domain.Customer;
import manager.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 用户管理的处理逻辑
 */
@Controller
@RequestMapping("/admin/manage/customer")
public class CustomerManageController {

    @Autowired
    private ICustomerService iCustomerService;

    /**
     * 跳转到用户删除页面
     * @param model
     */
    @RequestMapping(value = "del")
    public String toCustomersManage(Model model){
        List<Customer> customers = iCustomerService.findAllCustomers();
        model.addAttribute("customers",customers);
        return "html/customer/manager_customer_del";
    }

    /**
     * 跳转到用户权限管理页面
     * @param model
     */
    @RequestMapping(value = "powerChange")
    public String toCustomersPowerManage(Model model){
        List<Customer> customers = iCustomerService.findAllCustomers();
        model.addAttribute("customers",customers);
        return "html/customer/manager_customer_powerChange";
    }



    /**
     * 更改用户会员等级，如果是普通用户，改为会员；如果是会员，改为普通用户
     * @param id
     * @param model
     */
    @RequestMapping("/changeIsVIP")
    public String changeCustomerVIP(String id, Model model){
        iCustomerService.changeCustomerVIP(id);
        return "redirect:/admin/manage/customer/powerChange";
    }

    /**
     * 删除某个用户
     * @param id
     * @param model
     */
    @RequestMapping("/delete")
    public String deleteCustomer(String id,Model model){
        iCustomerService.deleteCustomerById(id);
        return "redirect:/admin/manage/customer/del";
    }
}
