
package com.wstutorial.ws;

import java.util.List;


import com.wstutorial.ws.commonservice.AcknowledgementCode;
import com.wstutorial.ws.commonservice.AcknowledgementCodeResponse;
import com.wstutorial.ws.itemservice.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class ItemServiceEndpoint {
	private static final String NAMESPACE_URI = "http://www.wstutorial.com/ws/ItemService";
	private ItemRepository itemRepository = new ItemRepository();

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createItemRequest" )
	@ResponsePayload
	public AcknowledgementCodeResponse createItem(@RequestPayload CreateItemRequest request)throws Exception  {
		System.out.println("create Item called");

		AcknowledgementCodeResponse response = new AcknowledgementCodeResponse();

		itemRepository.createNewItem(request.getItemType());

		response.setAcknowledgementCode(AcknowledgementCode.CREATED);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateItemRequest" )
	@ResponsePayload
	public AcknowledgementCodeResponse updateItem(@RequestPayload UpdateItemRequest request)throws Exception  {

		AcknowledgementCodeResponse response = new AcknowledgementCodeResponse();

		AcknowledgementCode updated = itemRepository.updateItemById(request.getItemType().getId(), request.getItemType());
		response.setAcknowledgementCode(updated);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteItemRequest" )
	@ResponsePayload
	public AcknowledgementCodeResponse deleteItem(@RequestPayload DeleteItemRequest request)throws Exception  {
		System.out.println("Item deleted");
		AcknowledgementCodeResponse response = new AcknowledgementCodeResponse();

		AcknowledgementCode ack = itemRepository.deleteItemById(request.getId());

		response.setAcknowledgementCode(ack);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getItemsRequest" )
	@ResponsePayload
	public GetItemsResponse getAllItems(@RequestPayload GetItemsRequest request)throws Exception  {
		ObjectFactory factory = new ObjectFactory();
		GetItemsResponse response = factory.createGetItemsResponse();

		List<Item> Items = itemRepository.getAllItems();

		response.getItems().addAll(Items);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getItemRequest" )
	@ResponsePayload
	public GetItemResponse getItem(@RequestPayload GetItemRequest request)throws Exception  {
		ObjectFactory factory = new ObjectFactory();
		GetItemResponse response = factory.createGetItemResponse();

        Item Item = itemRepository.getItemById(request.getId());
		response.setItem(Item);
		return response;
	}




}