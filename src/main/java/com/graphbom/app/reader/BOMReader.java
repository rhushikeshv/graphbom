package com.graphbom.app.reader;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import com.graphbom.app.model.EBOM;
import com.graphbom.app.model.Part;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BOMReader {

    private InputStream inputStream = null;

    public BOMReader(InputStream inputStream){
        this.inputStream = inputStream;
    }
    public List<EBOM> loadBOM() throws Exception {
        byte[] buffer = ByteStreams.toByteArray(this.inputStream);
        Reader targetReader = CharSource.wrap(new String(buffer)).openStream();
        BufferedReader bufferedReader = new BufferedReader(targetReader);
        String line;
        List<EBOM> listOfRelationships = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            EBOM ebom = parseBOMLine(line);
            listOfRelationships.add(ebom);
        }
        bufferedReader.close();
        targetReader.close();
        return listOfRelationships;
    }

    private EBOM parseBOMLine(String line) {
        String datavalue[] = line.split("\\t");
        //Create from Part Node
        String fromNodeName = datavalue[0];
        String fromNodeType = datavalue[1];
        String fromNodeRev = datavalue[2];
        System.out.println("the node rev is " + fromNodeRev);
        String fromNodeQuantity = datavalue[3];

        Part fromNode = new Part();
        fromNode.setName(fromNodeName);
        fromNode.setType(fromNodeType);
        fromNode.setRev(Long.parseLong(fromNodeRev));
        fromNode.setQuantity(Long.parseLong(fromNodeQuantity));


        // Create EBOM relationship
        String relName = datavalue[4];
        String relQuantity = datavalue[5];




        //Create to Part Node
        String toNodeName = datavalue[6];
        String toNodeType = datavalue[7];
        String toNodeRev = datavalue[8];
        String toNodeQuantity = datavalue[9];

        Part toNode = new Part();
        toNode.setName(toNodeName);
        toNode.setType(toNodeType);
        toNode.setRev(Long.parseLong(toNodeRev));
        toNode.setQuantity(Long.parseLong(toNodeQuantity));


        EBOM ebom = new EBOM(fromNode,toNode);
        ebom.setName(relName);
        ebom.setQuantity(relQuantity);

        return ebom;

    }

    public static void main(String[] args) {

    }
}
