package com.graphbom.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type="EBOM")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EBOM {
    @Id @GeneratedValue
    private Long relationshipID;

    private String quantity;
    private String name;

    @Version
    private Long version = 0L; //optimistic locking

    public EBOM(Part startNode,Part endNode){
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public Long getRelationshipID() {
        return relationshipID;
    }

    public void setRelationshipID(Long relationshipID) {
        this.relationshipID = relationshipID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Part getStartNode() {
        return startNode;
    }

    public void setStartNode(Part startNode) {
        this.startNode = startNode;
    }

    public Part getEndNode() {
        return endNode;
    }

    public void setEndNode(Part endNode) {
        this.endNode = endNode;
    }

    //@JsonIgnore
    @StartNode private Part startNode;

    @EndNode   private Part endNode;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
