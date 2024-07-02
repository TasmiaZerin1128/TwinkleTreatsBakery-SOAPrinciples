package com.wstutorial.ws;

import com.wstutorial.ws.commonservice.AcknowledgementCode;
import com.wstutorial.ws.employeeservice.*;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        Employee emp1 = new Employee();
        emp1.setId(1001);
        emp1.setName("Tasmia Zerin");
        emp1.setEmail("bsse1128@iit.du.ac.bd");
        emp1.setPhoneNumber("01752773475");
        emp1.setRole("Delivery");

        Employee emp2 = new Employee();
        emp2.setId(1002);
        emp2.setName("New Employee in Town");
        emp2.setEmail("employee@iit.du.ac.bd");
        emp2.setPhoneNumber("01618985426");
        emp1.setRole("Serve");

        employees.add(emp1);
        employees.add(emp2);
    }
    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee getEmployeeById(Long id) {
        for(Employee r: employees) {
            if(r.getId() == id) {
                return r;
            }
        }
        System.out.println("Employee not found with id: " + id);
        return null;
    }

    public AcknowledgementCode deleteEmployeeById(Long id) {

        for(Employee r: employees) {
            if(r.getId() == id) {
                employees.remove(r);
                return AcknowledgementCode.DELETED;
            }
        }
        System.out.println("Employee not found with id: " + id + ", Delete aborted");
        return AcknowledgementCode.FAILED;
    }

    public AcknowledgementCode updateEmployeeById(Long id, Employee employee) {
        if (id == null || employee == null) {
            System.out.println("Invalid input: ID or employee is null");
            return AcknowledgementCode.FAILED;
        }

        for (int i = 0; i < employees.size(); i++) {
            Long employeeId = employees.get(i).getId();
            if (employeeId.equals(id)) {
                employees.set(i, employee);
                return AcknowledgementCode.UPDATED;
            }
        }
        System.out.println("Employee not found with id: " + id + ", Update aborted");
        return AcknowledgementCode.FAILED;
    }

    public AcknowledgementCode assignDeliveryMan(Long id, Employee employee) {
        if (id == null || employee == null) {
            System.out.println("Invalid input: ID or employee is null");
            return AcknowledgementCode.FAILED;
        }

        for(Employee r: employees) {
            if(r.getId() == id) {
                r.setRole("Delivery");
                return AcknowledgementCode.UPDATED;
            }
        }
        System.out.println("Employee not found with id: " + id + ", Assigning aborted");
        return AcknowledgementCode.FAILED;
    }

    public AcknowledgementCode getPayment(Long id, Long amount) {
        if (id == null) {
            System.out.println("Invalid input: ID is null");
            return AcknowledgementCode.FAILED;
        }

        for(Employee r: employees) {
            if(r.getId() == id) {
                System.out.println("Salary is paid");
                return AcknowledgementCode.UPDATED;
            }
        }
        System.out.println("Employee not found with id: " + id + ", Payment aborted");
        return AcknowledgementCode.FAILED;
    }

    public void createNewEmployee(Employee employee) {
        employee.setId(employees.size()+1001);
        employees.add(employee);
    }

}
