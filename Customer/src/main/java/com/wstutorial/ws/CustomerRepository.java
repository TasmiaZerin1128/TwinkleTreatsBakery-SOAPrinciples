package com.wstutorial.ws;

import com.wstutorial.ws.commonservice.AcknowledgementCode;
import com.wstutorial.ws.customerservice.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    List<Customer> customers = new ArrayList<>();

    public CustomerRepository() {
        Customer sel1 = new Customer();
        sel1.setId(1001);
        sel1.setName("Tasmia Zerin");
        sel1.setEmail("bsse1128@iit.du.ac.bd");
        sel1.setPhoneNumber("01752773475");
        sel1.setBank("1041050090156");
        sel1.setTinNumber("1230024859");


        Customer sel2 = new Customer();
        sel2.setId(1002);
        sel2.setName("New Customer in Town");
        sel2.setEmail("customer@iit.du.ac.bd");
        sel2.setPhoneNumber("01618985426");
        sel2.setBank("1041053798267");
        sel2.setTinNumber("1234024850");

        customers.add(sel1);
        customers.add(sel2);
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


    public void createNewCustomer(Customer customer) {
        customer.setId(customers.size()+1001);
        customers.add(customer);
    }

}
