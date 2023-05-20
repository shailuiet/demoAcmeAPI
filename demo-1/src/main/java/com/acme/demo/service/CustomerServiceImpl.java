package com.acme.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.demo.entity.Customer;
import com.acme.demo.entity.Orders;
import com.acme.demo.repository.CustomerRepository;
import com.acme.demo.repository.OrderRepository;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        try {
			return customerRepository.findById(customerId)
			        .orElseThrow(() -> new Exception("Customer not found with ID: " + customerId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customerDetails) {
        Customer existingCustomer = getCustomerById(customerId);
        existingCustomer.setName(customerDetails.getName());
        existingCustomer.setContactInfo(customerDetails.getContactInfo());
        return customerRepository.save(existingCustomer);
    }

    @Override
    public boolean deleteCustomer(Long customerId) {
        Customer customer = getCustomerById(customerId);
        //List<Order> orders = orderRepository.findByCustomer(customer);
        //orderRepository.deleteAll(orders);
        customerRepository.delete(customer);
        return true;
    }

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders createOrder(Long customerId, Orders order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getOrdersByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}
}
