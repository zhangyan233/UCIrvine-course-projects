package Solution;

import org.json.JSONObject;
import org.json.XML;

import java.io.*;

/**
 * transfer xml to jsonObject
 *  pass example: small1(2KB),medium1(121KB),Large1(50MB)
 *  cannot pass example: veryLarge1(173MB), error: out of Memory
 *  args[0]=src/main/resources/+fileName
 */
public class Task1_XmlToJson {
    public static void main(String[] args) {

        //1. transfer XML to Json
        JSONObject jsonObject=getJson(args[0]);

        //2. output Json to file
        output("task1.json",jsonObject);
    }

    /**
     * output jsonObject to specify file
     * @param fileName: corresponding fileName
     * @param jsonObject: json from .xml
     */
    private static void output(String fileName, JSONObject jsonObject) {
        BufferedWriter bw=null;

        try {
            bw=new BufferedWriter(new FileWriter("src/main/java/Result/"+fileName));
            bw.write(jsonObject.toString());
            bw.close();;

        } catch (IOException e) {
            e.printStackTrace();
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
}
