package manager.service.impl;

import manager.domain.AdminUser;
import manager.domain.mappers.AdminUserMapper;
import manager.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IAdminUserServiceImpl implements IAdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;


    @Override
    public AdminUser findByAccount(String account) {
        return adminUserMapper.findByAccount(account);
    }


}
