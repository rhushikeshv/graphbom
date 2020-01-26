package com.graphbom.app.repository;

import com.graphbom.app.model.EBOM;
import com.graphbom.app.model.Part;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface PartRepository extends Neo4jRepository<Part,Long> {


    //@Query("MATCH path=(n:Part{name:'Bike'})-[r:EBOM*]->(m:Part) WHERE NOT (" + "(m)-[:EBOM*]->()) " + "RETURN m")
    @Query("MATCH (n:Part) RETURN n")
    Collection<Part> findAll();

    @Query("MATCH p=(n:Part{ name:{name},type:{type},rev:{rev}})-[:EBOM*]->" +
            "(m)" +
            " WITH collect(p) AS ps" +
            " CALL apoc.convert.toTree(ps) YIELD value as data RETURN data")
    List<Map<String,Part>> loadBOM(@Param("type") String type,
                                   @Param("name") String name,
                                   @Param("rev")  Long rev
                                   );

/*    @Query("MATCH path=(n:Part{name:'{name}',type:'{type}',rev:'{rev}'})" +
            "-[r:REVISION*]->" +
            "(m:Part) " +
            "WHERE " +
            "NOT (" +
            "(m)-->()) " + "RETURN m")*/
    @Query("MATCH path=(n:Part{name:{name},type:{type},rev:{rev}})-[:REVISION*]->(m:Part) WHERE NOT (" + "(m)-[:REVISION*]->()) " + "RETURN m")
    Collection<Part> findPartByRev(@Param("name")String name,
                        @Param("type")String type,
                        @Param("rev")Long rev);


    @Query("MERGE node=(n:Part{name:{name},type:{type},rev:{rev},quantity:{quantity}}) return node")
    Part createPart(@Param("type") String type,
                    @Param("name") String name,
                    @Param("rev")  Long rev,
                    @Param("quantity")Long quantity);

    @Query("MATCH ebom = ( (:Part {name:{startNodeName} })-[r:EBOM]-(:Part {name:{endNodeName}})) return ebom")
    List<Map<String, EBOM>> existsEBOMRelationship(@Param("startNodeName") String startNodeName,
                                                   @Param("endNodeName") String endNodeName);
    @Query("MATCH (n) WHERE id(n)={id} return n.uuid")
    String findPartUUID(@Param("id") Long id);





}
