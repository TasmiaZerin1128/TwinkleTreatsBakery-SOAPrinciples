package com.wstutorial.ws;

import com.wstutorial.ws.commonservice.AcknowledgementCode;
import com.wstutorial.ws.supplierservice.*;

import java.util.ArrayList;
import java.util.List;

public class SupplierRepository {

    List<Supplier> suppliers = new ArrayList<>();

    public SupplierRepository() {
        Supplier sel1 = new Supplier();
        sel1.setId(1001);
        sel1.setName("Tasmia Zerin");
        sel1.setEmail("bsse1128@iit.du.ac.bd");
        sel1.setPhoneNumber("01752773475");

        Supplier sel2 = new Supplier();
        sel2.setId(1002);
        sel2.setName("New Supplier in Town");
        sel2.setEmail("supplier@iit.du.ac.bd");
        sel2.setPhoneNumber("01618985426");

        suppliers.add(sel1);
        suppliers.add(sel2);
    }
    public List<Supplier> getAllSuppliers() {
        return suppliers;
    }

    public Supplier getSupplierById(Long id) {
        for(Supplier r: suppliers) {
            if(r.getId() == id) {
                return r;
            }
        }
        System.out.println("Supplier not found with id: " + id);
        return null;
    }

    public AcknowledgementCode deleteSupplierById(Long id) {
        for(Supplier r: suppliers) {
            if(r.getId() == id) {
                suppliers.remove(r);
                return AcknowledgementCode.DELETED;
            }
        }
        System.out.println("Supplier not found with id: " + id + ", Delete aborted");
        return AcknowledgementCode.FAILED;
    }

    public AcknowledgementCode updateSupplierById(Long id, Supplier supplier) {
        if (id == null || supplier == null) {
            System.out.println("Invalid input: ID or supplier is null");
            return AcknowledgementCode.FAILED;
        }

        for (int i = 0; i < suppliers.size(); i++) {
            Long supplierId = suppliers.get(i).getId();
            if (supplierId.equals(id)) {
                suppliers.set(i, supplier);
                return AcknowledgementCode.UPDATED;
            }
        }
        System.out.println("Supplier not found with id: " + id + ", Update aborted");
        return AcknowledgementCode.FAILED;
    }


    public void createNewSupplier(Supplier supplier) {
        supplier.setId(suppliers.size()+1001);
        suppliers.add(supplier);
    }

}
