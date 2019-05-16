package manager.controller;

import manager.domain.AdminUser;
import manager.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private IAdminUserService iAdminUserService;

    /**
     * 管理员登录界面
     */
    @RequestMapping("/login")
    public String toLogin(){
        return "html/manager_login";
    }

    /**
     * 处理管理员登录,跳转到管理员首页
     * @param account
     * @param password
     */
    @RequestMapping("/handleLogin")
    public String handleLogin(String account,String password){
        AdminUser adminUser = iAdminUserService.findByAccount(account);
        if(adminUser==null){
            return "redirect:login";
        }else if(!(adminUser.getPassword().equals(password))){
            return "redirect:login";
        }else {
            return toManageIndex();
        }
    }

    /**
     * 转到管理员首页
     */
    @RequestMapping("/toIndex")
    public String toManageIndex(){
        return "html/manager_index";
    }

}
