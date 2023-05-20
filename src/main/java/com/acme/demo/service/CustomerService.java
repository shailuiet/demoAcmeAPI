package com.acme.demo.service;

import java.util.List;

import com.acme.demo.entity.Customer;
import com.acme.demo.entity.Orders;

public interface CustomerService {
    Customer getCustomerById(Long customerId);
    List<Customer> getAllCustomers();
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    boolean deleteCustomer(Long customerId);
    Orders createOrder(Long customerId, Orders order);
    List<Orders> getOrdersByCustomerId(Long customerId);
	Customer updateCustomer(Long customerId, Customer customerDetails);
}