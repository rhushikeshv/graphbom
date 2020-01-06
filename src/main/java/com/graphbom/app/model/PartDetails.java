package com.graphbom.app.model;

public class PartDetails {



    private String name;
    private String description;
    private String type;
    private Long rev;
    private Long quantity=0L;
    private String genericQuantity;

    private String unitPrice;
    private String unitsInStock;
    private String unitsOnOrder;
    private String reorderLevel;
    private String discontinued;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getRev() {
        return rev;
    }

    public void setRev(Long rev) {
        this.rev = rev;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getGenericQuantity() {
        return genericQuantity;
    }

    public void setGenericQuantity(String genericQuantity) {
        this.genericQuantity = genericQuantity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(String unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public String getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(String unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public String getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(String reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }
}
