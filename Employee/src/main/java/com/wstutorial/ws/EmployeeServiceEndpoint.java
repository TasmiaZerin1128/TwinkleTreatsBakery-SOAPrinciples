
package com.wstutorial.ws;

import java.util.List;


import com.wstutorial.ws.commonservice.AcknowledgementCode;
import com.wstutorial.ws.commonservice.AcknowledgementCodeResponse;
import com.wstutorial.ws.employeeservice.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class EmployeeServiceEndpoint {
	private static final String NAMESPACE_URI = "http://www.wstutorial.com/ws/EmployeeService";
	private EmployeeRepository employeeRepository = new EmployeeRepository();

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createEmployeeRequest" )
	@ResponsePayload
	public AcknowledgementCodeResponse createEmployee(@RequestPayload CreateEmployeeRequest request)throws Exception  {
		System.out.println("create employee called");

		AcknowledgementCodeResponse response = new AcknowledgementCodeResponse();

		employeeRepository.createNewEmployee(request.getEmployeeType());

		response.setAcknowledgementCode(AcknowledgementCode.CREATED);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest" )
	@ResponsePayload
	public AcknowledgementCodeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request)throws Exception  {

		AcknowledgementCodeResponse response = new AcknowledgementCodeResponse();

		AcknowledgementCode updated = employeeRepository.updateEmployeeById(request.getEmployeeType().getId(), request.getEmployeeType());
		response.setAcknowledgementCode(updated);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest" )
	@ResponsePayload
	public AcknowledgementCodeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request)throws Exception  {
		System.out.println("employee deleted");
		AcknowledgementCodeResponse response = new AcknowledgementCodeResponse();

		AcknowledgementCode ack = employeeRepository.deleteEmployeeById(request.getId());

		response.setAcknowledgementCode(ack);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeesRequest" )
	@ResponsePayload
	public GetEmployeesResponse getAllEmployees(@RequestPayload GetEmployeesRequest request)throws Exception  {
		ObjectFactory factory = new ObjectFactory();
		GetEmployeesResponse response = factory.createGetEmployeesResponse();

		List<Employee> employees = employeeRepository.getAllEmployees();

		response.getEmployees().addAll(employees);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeRequest" )
	@ResponsePayload
	public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeRequest request)throws Exception  {
		ObjectFactory factory = new ObjectFactory();
		GetEmployeeResponse response = factory.createGetEmployeeResponse();

        Employee employee = employeeRepository.getEmployeeById(request.getId());
		response.setEmployee(employee);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "assignDeliveryMan" )
	@ResponsePayload
	public AcknowledgementCodeResponse assignDeliveryMan(@RequestPayload AssignDeliveryManRequest request)throws Exception  {
		System.out.println("employee assigned as Delivery Man");
		AcknowledgementCodeResponse response = new AcknowledgementCodeResponse();

		AcknowledgementCode ack = employeeRepository.assignDeliveryMan(request.getId(), employeeRepository.getEmployeeById(request.getId()));

		response.setAcknowledgementCode(ack);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPaymentRequest" )
	@ResponsePayload
	public AcknowledgementCodeResponse getPayment(@RequestPayload GetPaymentRequest request)throws Exception  {
		System.out.println("employee got payment");
		AcknowledgementCodeResponse response = new AcknowledgementCodeResponse();

		AcknowledgementCode ack = employeeRepository.getPayment(request.getId(), 25000L);

		response.setAcknowledgementCode(ack);

		return response;
	}
}