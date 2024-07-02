package com.wstutorial.ws;

import com.wstutorial.ws.commonservice.AcknowledgementCode;
import com.wstutorial.ws.customerservice.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    List<Customer> customers = new ArrayList<>();

    public CustomerRepository() {
        Customer customer1 = new Customer();
        customer1.setId(1001);
        customer1.setName("Tasmia Zerin");
        customer1.setEmail("bsse1128@iit.du.ac.bd");
        customer1.setPhoneNumber("01752773475");

        Customer customer2 = new Customer();
        customer2.setId(1002);
        customer2.setName("New Customer in Town");
        customer2.setEmail("customer@iit.du.ac.bd");
        customer2.setPhoneNumber("01618985426");

        customers.add(customer1);
        customers.add(customer2);
    }
    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerById(Long id) {
        for(Customer r: customers) {
            if(r.getId() == id) {
                return r;
            }
        }
        System.out.println("Customer not found with id: " + id);
        return null;
    }

    public AcknowledgementCode deleteCustomerById(Long id) {

        for(Customer r: customers) {
            if(r.getId() == id) {
                customers.remove(r);
                return AcknowledgementCode.DELETED;
            }
        }
        System.out.println("Customer not found with id: " + id + ", Delete aborted");
        return AcknowledgementCode.FAILED;
    }

    public AcknowledgementCode updateCustomerById(Long id, Customer customer) {
        if (id == null || customer == null) {
            System.out.println("Invalid input: ID or customer is null");
            return AcknowledgementCode.FAILED;
        }

        for (int i = 0; i < customers.size(); i++) {
            Long customerId = customers.get(i).getId();
            if (customerId.equals(id)) {
                customers.set(i, customer);
                return AcknowledgementCode.UPDATED;
            }
        }
        System.out.println("Customer not found with id: " + id + ", Update aborted");
        return AcknowledgementCode.FAILED;
    }

    public Order placeOrder(Item item, Long id) {
        for(Customer r: customers) {
            if(r.getId() == id) {
                Order newOrder = new Order();
                newOrder.setItems(item);
                newOrder.setId(id + 5001);
                newOrder.setAmount(1);
                newOrder.setStatus("Confirmed");
                return newOrder;
            }
        }
        System.out.println("Customer not found with id: " + id);
        return null;
    }

    public Order trackOrder(Item item, Long id) {
        for(Customer r: customers) {
            if(r.getId() == id) {
                Order tracked = new Order();
                tracked.setId(id);
                return tracked;
            }
        }
        System.out.println("Customer not found with id: " + id);
        return null;
    }

    public void createNewCustomer(Customer customer) {
        customer.setId(customers.size()+1001);
        customers.add(customer);
    }

}
