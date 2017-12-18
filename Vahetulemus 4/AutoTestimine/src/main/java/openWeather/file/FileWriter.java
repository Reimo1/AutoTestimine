package openWeather.file;

import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileWriter {
    public static void writeJsonToFile(JSONObject jsonObject, String filename) {
        try{
            java.io.FileWriter file = new java.io.FileWriter(filename);
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
