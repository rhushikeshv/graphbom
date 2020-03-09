package com.graphbom.app.service;

import com.graphbom.app.model.EBOM;
import com.graphbom.app.model.Part;
import com.graphbom.app.output.Result;
import com.graphbom.app.reader.BOMReader;
import com.graphbom.app.repository.EBOMRepository;
import com.graphbom.app.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.InputStream;
import java.util.*;

@Service
public class PartBOMService {

    @Autowired
    private  PartRepository partRepository;

    @Autowired
    private  EBOMRepository ebomRepository ;


    @Autowired
    private PlatformTransactionManager transactionManager;

    private Queue<Map<String, Part>> queue = new LinkedList<>();

    public PartBOMService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Transactional(readOnly = true)
    public Iterable<Part> findParts() {
        return partRepository.findAll();
    }



    @Transactional(readOnly = true)
    public Map<String, List<Part>> loadBOM(String type,String name,Long rev) {
        List<Map<String, Part>> outcome = partRepository.loadBOM(type,name,rev);
        Part topPart = new Part();
        navigateBOM(outcome, topPart);
        List<Part> bomList = new ArrayList<>();
        bomList.add(topPart);

        Map<String, List<Part>> partData = new HashMap<>();
        partData.put("data", bomList);

        return partData;
    }

    public Result<String,Part> createPart(Part newPart){
        Result<String,Part> result = new Result<>();
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        boolean error = false;
        try {
            Part existingPart = this.partRepository.findPartByTNR(newPart.getType(),newPart.getName(),newPart.getRev());
            if(existingPart==null)
            {
                newPart.setUuid(UUID.randomUUID().toString());
                this.partRepository.save(newPart);
                result.setData(newPart);
                result.setMessage("OK");
            }
            else{
                result.setData(newPart);
                result.setMessage("FAIL"); // part already exists, cannot create new one.
            }

        }
        catch (Exception e)
        {
            transactionManager.rollback(status);
            result.setData(newPart);
            result.setMessage("FAIL");
            error = true;
        }
        finally {
            if(error) {
                transactionManager.rollback(status);
            }
            else {
                transactionManager.commit(status);
            }
        }
        return result;

    }
    public Result<String,Part> updatePart(Part part){

        Result<String,Part> result = new Result<>();
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        boolean error = false;
        try {
            Part existingPart = this.partRepository.findPartByUUID(part.getUuid());
            existingPart.setQuantity(part.getQuantity());
            existingPart.setName(part.getName());
            existingPart.setDescription(part.getDescription());
            existingPart.setLevel(part.getLevel());
            this.partRepository.save(existingPart, 1);
            result.setMessage("OK");
            result.setData(part);

        }
        catch(Exception exception){
            transactionManager.rollback(status);
            result.setData(part);
            result.setMessage("FAIL");
            error = true;
        }
        finally {

            if(error) {
                transactionManager.rollback(status);
            }
            else {
                transactionManager.commit(status);
            }
        }

        return result;
    }

    public List<Map<String, EBOM>> getEBOMRelationship(Part startNode, Part endNode){


        return this.partRepository.existsEBOMRelationship(startNode.getName(),endNode.getName());
    }





    public Result<String, EBOM> createRelationship(EBOM ebom,
                                                   boolean biDirectional){

        Result<String,EBOM> result = new Result<>();
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        boolean error = false;

        try {
            if(biDirectional){
                EBOM reverseEBOM = new EBOM(ebom.getEndNode(),
                        ebom.getStartNode());
                reverseEBOM.setVersion(ebom.getVersion());
                reverseEBOM.setName(ebom.getName());
                reverseEBOM.setQuantity(ebom.getQuantity());
                this.ebomRepository.save(ebom);
                this.ebomRepository.save(reverseEBOM);

            }
            else{
                EBOM outcome = this.ebomRepository.save(ebom);
                result.setData(outcome);
                result.setMessage("OK");
            }

        }
        catch(Exception e){
            error = true;
            result.setMessage("FAIL");
            result.setData(new EBOM(new Part(),new Part()));
        }
        finally {
            if(error)
                transactionManager.rollback(status);
            else
                transactionManager.commit(status);

        }

        return result;

    }

    public Result<String,String> importBOM(InputStream inputStream){

        Result<String,String> result = new Result<>();
        BOMReader reader = new BOMReader(inputStream);
        try {
            List<EBOM> listOfRels = reader.loadBOM();
            result = createBOM(listOfRels,false);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("FAIL");
        }
        return result;


    }

    private Result<String,String> createBOM(List<EBOM> listOfRelationships,
                                                 boolean biDirectional)
    {
        Result<String,String> result = new Result<>();
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        boolean error = false;

        try {
            for (EBOM ebom : listOfRelationships){

                Part start = ebom.getStartNode();
                Part end   = ebom.getEndNode();

                if(biDirectional) {

//                    ebom.setStartNode(start);
//                    ebom.setEndNode(end);
//
//                    System.out.println("Saving ebom " + ebom.getName());
//                    this.ebomRepository.save(ebom);
//
//                    EBOM ebomReverse = new EBOM(end,start);
//                    ebomReverse.setName(ebom.getName());
//                    ebomReverse.setVersion(ebom.getVersion());
//                    ebomReverse.setQuantity(ebom.getQuantity());
//
//                    this.partRepository.save(start);
//                    this.partRepository.save(end);
//                    this.ebomRepository.save(ebomReverse);



                }
                else {
                    Part startNode = this.partRepository.findPartByTNR(start.getType(),start.getName(),start.getRev());
                    if(startNode==null){
                        start.setUuid(UUID.randomUUID().toString());
                        startNode = this.partRepository.save(start);
                    }


                    Part endNode = this.partRepository.findPartByTNR(end.getType(),end.getName(),end.getRev());
                    if(endNode==null){
                        end.setUuid(UUID.randomUUID().toString());
                        endNode = this.partRepository.save(end);
                    }
                    ebom.setStartNode(startNode);
                    ebom.setEndNode(endNode);

                    this.ebomRepository.save(ebom);

                }


            }
            result.setMessage("OK");
            result.setData("");

        }
        catch(Exception e){
            e.printStackTrace();
            error = true;
            result.setMessage("FAIL");
            result.setData("");

        }
        finally{
            if(error)
                transactionManager.rollback(status);
            else
                transactionManager.commit(status);

        }
        return result;
    }


    private void navigateBOM(List<Map<String, Part>> list, Part topPart) {

        for (Map<String, Part> map : list) {

            if (map.get("data") != null) {
                System.out.println("========IF LOOP=========");
                Map<String, Part> dataMap = (Map<String, Part>) map.get("data");

                for (Map.Entry<String, Part> entry : dataMap.entrySet()) {
                    String key = entry.getKey();
                    System.out.println("==The key is ====" + key);


                    if (key.equals("ebom")) {
                        List<Map<String, Part>> ebomList =
                                (List<Map<String, Part>>) entry.getValue();

                        navigateBOM(ebomList, topPart);

                    }

                    if (key.equals("name")) {
                        Object name = (Object) entry.getValue();
                        if (name instanceof String) {
                            topPart.setName((String) name);
                            System.out.println("====name is ===" + name);
                        }
                    }
                    if(key.equals("quantity")){
                        Object quantity = (Object)entry.getValue();
                        if(quantity instanceof Long){
                            topPart.setQuantity((Long)quantity);
                        }
                        if(quantity instanceof String){
                            topPart.setGenericQuantity((String)quantity);
                        }
                    }
                    if (key.equals("description")) {
                        Object description = (Object) entry.getValue();
                        if (description instanceof String) {
                            topPart.setDescription((String) description);
                        }
                    }
                    if (key.equals("type")) {
                        Object type = (Object) entry.getValue();
                        if (type instanceof String) {
                            topPart.setType((String) type);
                            System.out.println("====type is ===" + type);
                        }
                    }
                    if (key.equals("rev")) {
                        Object rev = (Object) entry.getValue();
                        if (rev instanceof Long) {
                            topPart.setRev((Long) rev);
                        }
                    }

                }
                System.out.println("finding the latest for " + topPart
                        .getRev() + " name " + topPart.getName() + " type " +
                        "" + topPart.getType());

                Collection<Part> partRevs = this.partRepository
                        .findPartByRev
                                (topPart.getName(),
                                        topPart.getType(),
                                        topPart.getRev());

                System.out.println("the part rev is " + partRevs.toString());
                if (partRevs.size() > 0) {
                    Part outcome = (Part) partRevs.toArray()[0];
                    System.out.println("The latest part is " +
                            outcome.getRev());
                    topPart.setRev(outcome.getRev());
                }
            } else {
                System.out.println("========ELSE LOOP=============");
                if (map.get("ebom") != null) {
                    List<Map<String, Part>> bomList =
                            (List<Map<String, Part>>) map.get("ebom");

                    Part part = new Part();
                    for (Map.Entry<String, Part> entry : map.entrySet()) {


                        String key = entry.getKey();

                        if (key.equals("ebom")) {
                            topPart.addChild(part);
                            List<Map<String, Part>> ebomList =
                                    (List<Map<String, Part>>) entry
                                            .getValue();

                            navigateBOM(ebomList, part);

                        }

                        if (key.equals("name")) {
                            Object name = (Object) entry.getValue();
                            if (name instanceof String) {
                                part.setName((String) name);
                                System.out.println("====name is ===" + name);
                            }
                        }
                        if(key.equals("ebom.quantity")){
                            Object quantity = (Object)entry.getValue();
                            if(quantity instanceof Long){
                                part.setQuantity((Long)quantity);
                            }
                            if(quantity instanceof String){
                                part.setGenericQuantity((String)quantity);
                            }
                        }
                        if (key.equals("description")) {
                            Object description = (Object) entry.getValue();
                            if (description instanceof String) {
                                part.setDescription((String) description);
                            }
                        }
                        if (key.equals("type")) {
                            Object type = (Object) entry.getValue();
                            if (type instanceof String) {
                                part.setType((String) type);
                                System.out.println("====type is ===" + type);
                            }
                        }
                        if (key.equals("rev")) {
                            Object rev = (Object) entry.getValue();
                            if (rev instanceof Long) {
                                part.setRev((Long) rev);
                            }
                        }

                    }
                    System.out.println("finding the latest for " + part
                            .getRev() + " name " + part.getName() + " type" +
                            " " + part.getType());
                    Collection<Part> partRevs = this.partRepository
                            .findPartByRev
                                    (part.getName(),
                                            part.getType(),
                                            part.getRev());
                    System.out.println("the part rev is " + partRevs.toString());

                    if (partRevs.size() > 0) {
                        Part outcome = (Part) partRevs.toArray()[0];
                        System.out.println("The latest part is " +
                                outcome.getRev());
                        part.setRev(outcome.getRev());
                    }
                    //map.put("data",part);

                } else {

                    Part part = new Part();
                    topPart.addChild(part);
                    for (Map.Entry<String, Part> entry : map.entrySet()) {
                        String key = entry.getKey();

                        if (key.equals("name")) {
                            Object name = (Object) entry.getValue();
                            if (name instanceof String) {
                                part.setName((String) name);
                            }
                        }
                        if(key.equals("ebom.quantity")){
                            Object quantity = (Object)entry.getValue();
                            if(quantity instanceof Long){
                                part.setQuantity((Long)quantity);
                            }
                            if(quantity instanceof String){
                                part.setGenericQuantity((String)quantity);
                            }
                        }
                        if (key.equals("description")) {
                            Object description = (Object) entry.getValue();
                            if (description instanceof String) {
                                part.setDescription((String) description);
                            }
                        }
                        if (key.equals("type")) {
                            Object type = (Object) entry.getValue();
                            if (type instanceof String) {
                                part.setType((String) type);
                                System.out.println("====type is ===" + type);
                            }
                        }
                        if (key.equals("rev")) {
                            Object rev = (Object) entry.getValue();
                            if (rev instanceof Long) {
                                part.setRev((Long) rev);
                            }
                        }
                    }

                    System.out.println("finding the latest for " + part
                            .getRev() + " name " + part.getName() + " type" +
                            " " + part.getType());

                    Collection<Part> partRevs = this.partRepository
                            .findPartByRev
                                    (part.getName(),
                                            part.getType(),
                                            part.getRev());

                    System.out.println("the part rev is " + partRevs.toString());
                    if (partRevs.size() > 0) {
                        Part outcome = (Part) partRevs.toArray()[0];
                        System.out.println("The latest part is " +
                                outcome.getRev());
                        part.setRev(outcome.getRev());
                    }

                    //map.put("data",part);
                }
            }

        }

    }

    private void navigateBOMBFS() {

        while (!this.queue.isEmpty()) {

            Map<String, Part> map = this.queue.poll();

            System.out.println("========ELSE LOOP=============");
            if (map.get("ebom") != null) {
                List<Map<String, Part>> bomList =
                        (List<Map<String, Part>>) map.get("ebom");
                for (Map.Entry<String, Part> entry : map.entrySet()) {
                    String key = entry.getKey();
                    System.out.println(" the key is " + key);
                    if (key.equals("level")) {
                        Object level = (Object) entry.getValue();
                        if (level instanceof Long)
                            System.out.println(level);
                    }
                    if (key.equals("name")) {
                        Object name = (Object) entry.getValue();
                        if (name instanceof String)
                            System.out.println(name);
                    }
                    if (key.equals("description")) {
                        Object description = (Object) entry.getValue();
                        if (description instanceof String) {
                            System.out.println(description);
                        }
                    }
                    if (key.equals("_type")) {
                        Object _type = (Object) entry.getValue();
                        if (_type instanceof String) {
                            System.out.println(_type);
                        }
                    }
                    if (key.equals("ebom")) {
                        List<Map<String, Part>> ebomList =
                                (List<Map<String, Part>>) entry.getValue();
                        for (Map<String, Part> node : ebomList) {
                            this.queue.add(node);
                        }

                        //navigateBOM(bomList);

                    }

                }

            } else {
                for (Map.Entry<String, Part> entry : map.entrySet()) {
                    String key = entry.getKey();
                    System.out.println(" the key is " + key);
                    if (key.equals("level")) {
                        Object level = (Object) entry.getValue();
                        if (level instanceof Long)
                            System.out.println(level);
                    }
                    if (key.equals("name")) {
                        Object name = (Object) entry.getValue();
                        if (name instanceof String)
                            System.out.println(name);
                    }
                    if (key.equals("description")) {
                        Object description = (Object) entry.getValue();
                        if (description instanceof String) {
                            System.out.println(description);
                        }
                    }
                    if (key.equals("_type")) {
                        Object _type = (Object) entry.getValue();
                        if (_type instanceof String) {
                            System.out.println(_type);
                        }
                    }


                }
            }


        }
    }
}
