package manager.service.impl;

import manager.domain.Customer;
import manager.domain.mappers.CustomerMapper;
import manager.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICustomerServiceImpl implements ICustomerService {


    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> findAllCustomers() {
        return customerMapper.findAllCustomersForVIP();
    }

    @Override
    //更新用户会员等级
    public void changeCustomerVIP(String id) {
        Customer customer = customerMapper.findCustomerById(id);
        String VIP = "";
        if(!customer.getIsVip()){
            VIP = "true";
        }else {
            VIP = "false";
        }
        customerMapper.changeCustomerVIP(id,VIP);
    }

    @Override
    //删除用户
    public void deleteCustomerById(String id) {
        //同时删除这个用户的购物车数据
        customerMapper.deleteCartsByCustomerId(id);
        customerMapper.deleteCustomerById(id);
    }
}
