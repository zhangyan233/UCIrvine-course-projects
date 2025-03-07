package Solution;

import org.json.JSONObject;
import org.json.JSONPointer;
import org.json.JSONPointerException;
import org.json.XML;

import java.io.*;

/**
 * get subObject from .xml
 * pass example: small1(arg[1]:truePath:/Root/Customers/Customer/1 falsePath:/Root/Customers/1)
 *               medium1(org[1]:truePath:/company/employees falsePath: /company/manager)
 *               Large1(org[1]:truePath/feed/doc falsePath:/feed/doc/1/link)
 * fail example: veryLarge1(org[1]:/feed/doc/0)
 * args[0]=src/main/resources/+fileName
 */
public class Task3_isHavePath {
    public static void main(String[] args) {
        //1. transfer XML to Json
        JSONObject jsonObject=getJson(args[0]);

        //2. use JSONPointer to get subObject
        JSONPointer jp=new JSONPointer(args[1]);

        //3. judge whether exists this path in jsonObject, if yes, output the file, if not without any operate
        Object subObject = jp.queryFrom(jsonObject);
        if(subObject!=null){
            output("task3.json", jsonObject);
        }else{
            System.out.println("there is no this path in .xml");
        }
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


