package com.graphbom.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.graphbom.app.views.Views;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Part {



// quantity_unit
// unit_price
// units_in_stock
// units_on_order
// reorder_level
// discontinued

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @Relationship(type="EBOM",direction = Relationship.OUTGOING)
    @JsonView(Views.Internal.class)
    private EBOM ebom;

    private Long level;
    private String type;
    private String name;
    private String description;
    private Long rev;
    private Long quantity=0L;
    private String genericQuantity;
    private Long unitPrice;
    private Long unitsInStock;
    private Long unitsOnOrder;
    private Long reorderLevel;
    private String discontinued;

    private String uuid ="";


    @Version
    private Long version=1L; //optimistic locking

    @JsonProperty("data")
    @Transient
    private PartDetails details = new PartDetails();


    public Part(String type,String name,Long rev){
        this.details.setType(type);
        this.details.setName(name);
        this.details.setRev(rev);

        this.type = type;
        this.name = name;
        this.rev = rev;
    }
    public Part(){
        
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public PartDetails getDetails() {
        return details;
    }

    public void setDetails(PartDetails details) {
        this.details = details;
    }

    public void setName(String name) {
        this.name = name;
        this.details.setName(name);
    }
    public void setDescription(String description) {
        this.description = description;
        this.details.setDescription(description);
    }
    public void setType(String type) {
        this.type = type;
        this.details.setType(type);
    }
    public String getType(){return this.details.getType();}

    public void setRev(Long rev){
        this.rev = rev;
        this.details.setRev(rev);
    }
    public Long getRev(){return this.rev;}

    public List<Part> getChildren() {
        return children;
    }

    private List<Part> children = new ArrayList<>();



    public void setChildren(List<Part> children) {
        this.children = children;
    }


    public void addChild(Part part){
        this.children.add(part);
    }


    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
        this.details.setQuantity(quantity);
    }

    public String getGenericQuantity() {
        return genericQuantity;
    }

    public void setGenericQuantity(String genericQuantity) {
        this.genericQuantity = genericQuantity;
        this.details.setGenericQuantity(genericQuantity);
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public EBOM getEbom() {
        return ebom;
    }

    public void setEbom(EBOM ebom) {
        this.ebom = ebom;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
        this.details.setUnitPrice(this.unitPrice);
    }

    public Long getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Long unitsInStock) {
        this.unitsInStock = unitsInStock;
        this.details.setUnitPrice(this.unitsInStock);
    }

    public Long getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(Long unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
        this.details.setUnitsOnOrder(this.unitsOnOrder);
    }

    public Long getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Long reorderLevel) {
        this.reorderLevel = reorderLevel;
        this.details.setReorderLevel(reorderLevel);
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
        this.details.setDiscontinued(discontinued);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}