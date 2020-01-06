package com.graphbom.app.reader;

import com.graphbom.app.model.Part;

import java.io.BufferedReader;
import java.io.FileReader;

public class PartReader {



    public static void main(String[] args) throws Exception {

        String dataFile = "E:\\OpenSource\\graphbom\\parts.tsv";
        BufferedReader bReader = new BufferedReader(
                new FileReader(dataFile));

        String line;

        /**
         * Looping the read block until all lines in the file are read.
         */
        while ((line = bReader.readLine()) != null) {

            /**
             * Splitting the content of tabbed separated line
             */
            String datavalue[] = line.split("\\t");
            String value1 = datavalue[0]; // id
            String value2 = datavalue[1]; // name
            String value3 = datavalue[2]; // quantity_unit
            String value4 = datavalue[3]; // unit_price
            String value5 = datavalue[4]; // units_in_stock
            String value6 = datavalue[5]; // units_on_order
            String value7 = datavalue[6]; // reorder_level
            String value8 = datavalue[7]; // discontinued

            /**
             * Printing the value read from file to the console
             */
            /*System.out.println("===The val is ====" + value1);
            System.out.println("===The val is ====" + value2);
            System.out.println("===The val is ====" + value3);
            System.out.println("===The val is ====" + value4);
            System.out.println("===The val is ====" + value5);
            System.out.println("===The val is ====" + value6);
            System.out.println("===The val is ====" + value7);
            System.out.println("===The val is ====" + value8);*/

            Part p = new Part("Part",datavalue[1],1L);
            p.setQuantity(Long.parseLong(datavalue[2]));
            p.setUnitPrice(datavalue[3]);
            p.setUnitsInStock(datavalue[4]);
            p.setUnitsOnOrder(datavalue[5]);
            p.setReorderLevel(datavalue[6]);
            p.setDiscontinued(datavalue[7]);

            //create(bike:Part {name:'Bike',level:1,description:'top',type:'Part',rev:1})
            String createPart =
                    "create(" +
                            p.getName()+ ":Part" + "{" +
                            "name:"+ "'"+p.getName()+"'"+","+
                            "level:"+"1"+","+
                            "description:"+ "'"+p.getName()+"'"+","+
                            "rev:"+ "'"+"1"+"'"+","+
                            "quantity_unit:"+ "'"+p.getQuantity()+"'"+","+
                            "unit_price:"+ "'"+p.getUnitPrice()+"'"+","+
                            "units_in_stock:"+ "'"+p.getUnitsInStock()+"'"+","+
                            "units_on_order:"+ "'"+p.getUnitsOnOrder()+"'"+","+
                            "reorder_level:"+ "'"+p.getReorderLevel()+"'"+","+
                            "discontinued:"+ "'"+p.getDiscontinued()+"'"+","+
                            "type:"+ "'"+"Part"+"'"+"})";
            System.out.println(createPart);

        }
        bReader.close();
    }

    }

