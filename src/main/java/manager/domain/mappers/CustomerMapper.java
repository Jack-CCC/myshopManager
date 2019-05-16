package manager.domain.mappers;

import manager.domain.Customer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Select("select id,is_VIP as isVip,username from customer")
    List<Customer> findAllCustomersForVIP();

    @Select("select id,is_VIP as isVip,username from customer where id=#{id}")
    Customer findCustomerById(String id);

    @Update("update customer set is_VIP=#{VIP} where id=#{id}")
    void changeCustomerVIP(String id, String VIP);

    @Delete("delete from customer where id=#{id}")
    void deleteCustomerById(String id);

    @Delete("delete from cart where commodity_id=#{id}")
    void deleteCartsByCustomerId(String id);
}
