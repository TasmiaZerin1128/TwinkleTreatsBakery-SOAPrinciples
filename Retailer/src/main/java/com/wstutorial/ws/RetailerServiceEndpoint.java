
package com.wstutorial.ws;

import java.util.List;


import com.wstutorial.ws.commonservice.AcknowledgementCode;
import com.wstutorial.ws.commonservice.AcknowledgementCodeResponse;
//import com.wstutorial.ws.retailerservice.*;
import com.wstutorial.ws.retailerservice.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class RetailerServiceEndpoint {
	private static final String NAMESPACE_URI = "http://www.wstutorial.com/ws/RetailerService";
	private RetailerRepository retailerRepository = new RetailerRepository();

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createRetailerRequest" )
	@ResponsePayload
	public AcknowledgementCodeResponse createRetailer(@RequestPayload CreateRetailerRequest request)throws Exception  {
		System.out.println("create retailer called");

		AcknowledgementCodeResponse response = new AcknowledgementCodeResponse();

		retailerRepository.createNewRetailer(request.getRetailerType());

		response.setAcknowledgementCode(AcknowledgementCode.CREATED);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateRetailerRequest" )
	@ResponsePayload
	public AcknowledgementCodeResponse updateRetailer(@RequestPayload UpdateRetailerRequest request)throws Exception  {

		AcknowledgementCodeResponse response = new AcknowledgementCodeResponse();

		AcknowledgementCode updated = retailerRepository.updateRetailerById(request.getRetailerType().getId(), request.getRetailerType());
		response.setAcknowledgementCode(updated);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteRetailerRequest" )
	@ResponsePayload
	public AcknowledgementCodeResponse deleteRetailer(@RequestPayload DeleteRetailerRequest request)throws Exception  {
		System.out.println("-->deleteRetailer<--");
		AcknowledgementCodeResponse response = new AcknowledgementCodeResponse();

		AcknowledgementCode ack = retailerRepository.deleteRetailerById(request.getId());

		response.setAcknowledgementCode(ack);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRetailersRequest" )
	@ResponsePayload
	public GetRetailersResponse getAllRetailers(@RequestPayload GetRetailersRequest request)throws Exception  {
		ObjectFactory factory = new ObjectFactory();
		GetRetailersResponse response = factory.createGetRetailersResponse();

		List<Retailer> retailers = retailerRepository.getAllRetailers();

		response.getRetailers().addAll(retailers);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRetailerRequest" )
	@ResponsePayload
	public GetRetailerResponse getRetailer(@RequestPayload GetRetailerRequest request)throws Exception  {
		ObjectFactory factory = new ObjectFactory();
		GetRetailerResponse response = factory.createGetRetailerResponse();

        Retailer retailer = retailerRepository.getRetailerById(request.getId());
		response.setRetailer(retailer);
		return response;
	}




}