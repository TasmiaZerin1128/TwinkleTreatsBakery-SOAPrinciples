package com.wstutorial.ws;

import com.wstutorial.ws.commonservice.AcknowledgementCode;
import com.wstutorial.ws.retailerservice.*;

import java.util.ArrayList;
import java.util.List;

public class RetailerRepository {

    List<Retailer> retailers = new ArrayList<>();

    public RetailerRepository() {
        Retailer sel1 = new Retailer();
        sel1.setId(1001);
        sel1.setName("Tasmia Zerin");
        sel1.setEmail("bsse1128@iit.du.ac.bd");
        sel1.setPhoneNumber("01752773475");
        sel1.setBank("1041050090156");
        sel1.setTinNumber("1230024859");


        Retailer sel2 = new Retailer();
        sel2.setId(1002);
        sel2.setName("New Retailer in Town");
        sel2.setEmail("retailer@iit.du.ac.bd");
        sel2.setPhoneNumber("01618985426");
        sel2.setBank("1041053798267");
        sel2.setTinNumber("1234024850");

        retailers.add(sel1);
        retailers.add(sel2);
    }
    public List<Retailer> getAllRetailers() {
        return retailers;
    }

    public Retailer getRetailerById(Long id) {
        for(Retailer r: retailers) {
            if(r.getId() == id) {
                return r;
            }
        }
        System.out.println("Retailer not found with id: " + id);
        return null;
    }

    public AcknowledgementCode deleteRetailerById(Long id) {

        for(Retailer r: retailers) {
            if(r.getId() == id) {
                retailers.remove(r);
                return AcknowledgementCode.DELETED;
            }
        }
        System.out.println("Retailer not found with id: " + id + ", Delete aborted");
        return AcknowledgementCode.FAILED;
    }

    public AcknowledgementCode updateRetailerById(Long id, Retailer retailer) {
        if (id == null || retailer == null) {
            System.out.println("Invalid input: ID or retailer is null");
            return AcknowledgementCode.FAILED;
        }

        for (int i = 0; i < retailers.size(); i++) {
            Long retailerId = retailers.get(i).getId();
            if (retailerId.equals(id)) {
                retailers.set(i, retailer);
                return AcknowledgementCode.UPDATED;
            }
        }
        System.out.println("Retailer not found with id: " + id + ", Update aborted");
        return AcknowledgementCode.FAILED;
    }


    public void createNewRetailer(Retailer retailer) {
        retailer.setId(retailers.size()+1001);
        retailers.add(retailer);
    }

}
