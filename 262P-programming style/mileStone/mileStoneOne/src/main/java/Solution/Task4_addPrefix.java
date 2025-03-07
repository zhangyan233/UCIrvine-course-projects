package Solution;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.io.*;

/**
 * add prefix "swe262_" to all of its keys.
 * args[0]=src/main/resources/+fileName.xml
 */
public class Task4_addPrefix {
    public static void main(String[] args) {
        //1. transfer XML to Json
        JSONObject jsonObject = getJson(args[0]);

        //2. add prefix to keys
        JSONObject jsonWithPrefix = addPrefix(jsonObject, "swe262_");

//        System.out.println(jsonWithPrefix.toString());

        //3. output JSON to file
        output("task4.json", jsonWithPrefix);
    }

    /**
     * to add prefix in all keys
     *
     * @param jsonObject: the jsonObject used to modified
     * @param prefix:     prefix to add
     * @return modifiedJSONObject
     */
    private static JSONObject addPrefix(JSONObject jsonObject, String prefix) {
        JSONObject modifiedJsonObject = new JSONObject();

        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);

            if (value instanceof JSONObject) {
                //nested: need to go deeper for more keys
                value = addPrefix((JSONObject) value, prefix);
            } else if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                JSONArray modifiedJsonArray = new JSONArray();

                for (int i = 0; i < jsonArray.length(); i++) {
                    Object element = jsonArray.get(i);
                    element = addPrefix((JSONObject) element, prefix);

                    modifiedJsonArray.put(element);
                }

                value = modifiedJsonArray;
            }

            key = prefix + key;
            modifiedJsonObject.put(key, value);
        }
        return modifiedJsonObject;
    }


    /**
     * transfer xml to json
     *
     * @param filePath: .xml's path
     * @return object of json
     */
    private static JSONObject getJson(String filePath) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
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
     *
     * @param fileName: corresponding fileName
     * @param object:   json from .xml
     */
    private static void output(String fileName, Object object) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter("src/main/java/Result/" + fileName));
            bw.write(object.toString());
            bw.close();
            ;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


