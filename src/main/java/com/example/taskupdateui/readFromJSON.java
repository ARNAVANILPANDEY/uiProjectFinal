package com.example.taskupdateui;

//import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class readFromJSON
{
    public static HashMap<String,String> readFromJSON() throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        FileReader reader = new FileReader(".//input.json");
        JSONObject obj = (JSONObject) jsonParser.parse(reader);

        HashMap<String,String> retMap=new HashMap<String,String>();

        Set<String> keys=obj.keySet();
        System.out.println(keys);
        Iterator<String> keyIterator= keys.iterator();
        while (keyIterator.hasNext())
        {
            String key=keyIterator.next();
            retMap.put(key, (String) obj.get(key));
        }

        //System.out.println(retMap);


        return retMap;




    }
}
