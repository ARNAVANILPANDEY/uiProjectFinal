package com.example.taskupdateui;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class jsonConv {

    public static void writeInput(HashMap<String,String> accMap)
    {
        JSONObject jsonObject = new JSONObject();


        for (Map.Entry mapElement:accMap.entrySet())
        {
            jsonObject.put(mapElement.getKey(),mapElement.getValue());
        }

        try {
            FileWriter file = new FileWriter("input.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonObject);
    }


    public static void writeOutput(Vector<HashMap<String,String>> accVec) {
        //Creating a JSONObject object

        JSONArray jsonArray= new JSONArray();
        //Inserting key-value pairs into the json object
//        jsonObject.put("ID", "1");
//        jsonObject.put("First_Name", "Shikhar");
//        jsonObject.put("Last_Name", "Dhawan");
//        jsonObject.put("Date_Of_Birth", "1981-12-05");
//        jsonObject.put("Place_Of_Birth", "Delhi");
//        jsonObject.put("Country", "India");

        for (HashMap<String,String> tem : accVec)
        {
            JSONObject jsonObject = new JSONObject();
            for (Map.Entry mapElement:tem.entrySet())
            {
                jsonObject.put(mapElement.getKey(),mapElement.getValue());
            }
            jsonArray.add(jsonObject);
        }

        try {
            FileWriter file = new FileWriter("output.json");
            file.write(jsonArray.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonArray);
    }
}
