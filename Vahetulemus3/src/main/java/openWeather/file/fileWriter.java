package openWeather.file;

import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class fileWriter {
    public static void writeJsonToFile(JSONObject jsonObject) {
        try{
            FileWriter file = new FileWriter("C:/Users/User/IdeaProjects/AutoTestimine/output.txt");
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("cityName", "Tallinn");
        fileWriter.writeJsonToFile(object);
    }
}
