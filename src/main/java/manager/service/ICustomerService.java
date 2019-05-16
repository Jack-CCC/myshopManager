package manager.service;

import manager.domain.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAllCustomers();

    void changeCustomerVIP(String id);

    void deleteCustomerById(String id);
}
