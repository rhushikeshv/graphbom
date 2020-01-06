package com.graphbom.app.repository;

import com.graphbom.app.model.EBOM;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EBOMRepository extends Neo4jRepository<EBOM,Long> {
}
