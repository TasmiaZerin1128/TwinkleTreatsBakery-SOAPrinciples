
package com.wstutorial.ws;

import java.util.List;


import com.wstutorial.ws.commonservice.AcknowledgementCode;
import com.wstutorial.ws.commonservice.AcknowledgementCodeResponse;
import com.wstutorial.ws.supplierservice.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class SupplierServiceEndpoint {
	private static final String NAMESPACE_URI = "http://www.wstutorial.com/ws/SupplierService";
	private SupplierRepository supplierRepository = new SupplierRepository();

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createSupplierRequest" )
	@ResponsePayload
	public AcknowledgementCodeResponse createSupplier(@RequestPayload CreateSupplierRequest request)throws Exception  {
		System.out.println("create supplier called");

		AcknowledgementCodeResponse response = new AcknowledgementCodeResponse();

		supplierRepository.createNewSupplier(request.getSupplierType());

		response.setAcknowledgementCode(AcknowledgementCode.CREATED);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateSupplierRequest" )
	@ResponsePayload
	public AcknowledgementCodeResponse updateSupplier(@RequestPayload UpdateSupplierRequest request)throws Exception  {

		AcknowledgementCodeResponse response = new AcknowledgementCodeResponse();

		AcknowledgementCode updated = supplierRepository.updateSupplierById(request.getSupplierType().getId(), request.getSupplierType());
		response.setAcknowledgementCode(updated);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteSupplierRequest" )
	@ResponsePayload
	public AcknowledgementCodeResponse deleteSupplier(@RequestPayload DeleteSupplierRequest request)throws Exception  {
		System.out.println("supplier deleted");
		AcknowledgementCodeResponse response = new AcknowledgementCodeResponse();

		AcknowledgementCode ack = supplierRepository.deleteSupplierById(request.getId());

		response.setAcknowledgementCode(ack);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSuppliersRequest" )
	@ResponsePayload
	public GetSuppliersResponse getAllSuppliers(@RequestPayload GetSuppliersRequest request)throws Exception  {
		ObjectFactory factory = new ObjectFactory();
		GetSuppliersResponse response = factory.createGetSuppliersResponse();

		List<Supplier> suppliers = supplierRepository.getAllSuppliers();

		response.getSuppliers().addAll(suppliers);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSupplierRequest" )
	@ResponsePayload
	public GetSupplierResponse getSupplier(@RequestPayload GetSupplierRequest request)throws Exception  {
		ObjectFactory factory = new ObjectFactory();
		GetSupplierResponse response = factory.createGetSupplierResponse();

        Supplier supplier = supplierRepository.getSupplierById(request.getId());
		response.setSupplier(supplier);
		return response;
	}




}