package manager.domain.mappers;

import manager.domain.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminUserMapper {

    @Select("select id,account,password from adminuser where account=#{account}")
    AdminUser findByAccount(String account);

}