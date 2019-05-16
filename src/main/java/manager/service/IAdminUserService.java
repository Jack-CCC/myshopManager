package manager.service;

import manager.domain.AdminUser;

public interface IAdminUserService {
    AdminUser findByAccount(String account);


}
