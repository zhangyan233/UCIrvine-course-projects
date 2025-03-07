package Solution;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONPointer;
import org.json.XML;

import java.io.*;

/**
 * replace Sub-Object
 * pass: small1(arg[1]:truePath: /Root/Customers/Customer/1 falsePath: /Root/Customers/1)
 *       medium1(arg[1]:truePath: /company/employees falsePath: /company/manager)
 *       Large1(arg[1]:truePath: /feed/doc falsePath: /feed/doc/1/link)
 *       veryLarge1(arg[1]:truePath: /feed/doc/0 falsePath: /feed/root)
 * args[0]=src/main/resources/+fileName.xml
 */
public class Task5_replaceSubObject {
    public static void main(String[] args) {
        //1. transfer XML to Json
        JSONObject jsonObject=getJson(args[0]);

        //2. getting to the sub-object
        JSONPointer jp=new JSONPointer(args[1]);

        //3. Replace the sub-object
        Object newObject = createNewObject();
        boolean modified = replace(jsonObject, jp, newObject);

//        System.out.println(jsonObject.toString());

        //4. output JSON to file if having correct keyPath to modify
        if(modified){
            output("task5.json", jsonObject);
        }
    }

    /**
     * replace certain sub-object in original object on a certain key path with another JSON object
     * @param jsonObject: this original JSONObject
     * @param keyPath: path to the key in JSONObject
     * @param newObject: new Object used to replace certain sub-object in original object
     * @return boolean: to check if we successfully update the JSONObject
     */
    private static boolean replace(JSONObject jsonObject, JSONPointer keyPath, Object newObject){
        Object subObject = keyPath.queryFrom(jsonObject);
        if(subObject !=null){
            String[] keys = keyPath.toString().replaceAll("^/|/$", "").split("/");
            JSONObject currentObject = jsonObject;

            for (int i = 0; i < keys.length - 1; i++) {
                String key = keys[i];
                Object nextObject = currentObject.get(key);

                if (nextObject instanceof JSONObject) {
                    currentObject = (JSONObject) nextObject;
                } else if (nextObject instanceof JSONArray){
                    JSONArray jsonArray = (JSONArray) nextObject;
                    int index = Integer.parseInt(keys[i +1]);
                    currentObject = jsonArray.getJSONObject(index);

                    if (index < jsonArray.length()) {
                        jsonArray.put(index, newObject);
                        return true;
                    } else {
                        System.out.println("Index out of bounds in JSON array.");
                        return false;
                    }
                }
            }
            String lastKey = keys[keys.length - 1];
            currentObject.put(lastKey, newObject);
            return true;
        }else {
            System.out.println("there is no this path in .xml");
            return false;
        }
    }

    /**
     * create a new JSONObject
     * @return JSONObject
     */
    private static JSONObject createNewObject(){
        JSONObject object = new JSONObject();

        object.put("key1", 1);
        object.put("key2", 2);
        object.put("key3", 3);
        object.put("key4", 4);

        return object;
    }

    /**
     * transfer xml to json
     * @param filePath: .xml's path
     * @return object of json
     */
    private static JSONObject getJson(String filePath) {
        StringBuilder sb=new StringBuilder();
        BufferedReader br=null;

        try {
            br=new BufferedReader(new FileReader(filePath));
            String line=br.readLine();

            while(line!=null){
                sb.append(line);
                line=br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return XML.toJSONObject(sb.toString());
    }

    /**
     * output jsonObject to specify file
     * @param fileName: corresponding fileName
     * @param object: json from .xml
     */
    private static void output(String fileName, Object object) {
        BufferedWriter bw=null;

        try {
            bw=new BufferedWriter(new FileWriter("src/main/java/Result/"+fileName));
            bw.write(object.toString());
            bw.close();;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


