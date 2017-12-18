package file;

import openWeather.file.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static openWeather.weatherReport.UpdateWeatherTask.INPUT_FILE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FileReaderTest {
    private static String city;
    private static JSONObject content;
    private static String countryCode;
    private static String units;
    private static FileReader fileReaderMock;

    @BeforeClass
    public static void setUpTests() throws IOException, ParseException {
        FileReader fileReader = new FileReader();
        units = "metric";
        city = "Tallinn";
        countryCode = "EE";
        fileReaderMock = mock(FileReader.class);
        File f = new File(INPUT_FILE);
        JSONArray randomJSONArray = new JSONArray();
        JSONObject randomJSONObject = new JSONObject();
        randomJSONObject.put("city", "Tallinn");
        randomJSONObject.put("countryCode", "EE");
        randomJSONObject.put("units", "metric");
        randomJSONArray.add(randomJSONObject);
        if (!f.exists() && f.isDirectory()) {
            when(fileReader.readFile(any(String.class))).thenReturn(randomJSONArray);
        }
        JSONArray fileContent = fileReaderMock.readFile(INPUT_FILE);
        content = (JSONObject) fileContent.get(0);
    }

    @Test
    public void testIfFileIsRead() {
        String inputTestFile = "C:/Users/User/IdeaProjects/AutoTestimine/input_test.txt";
        try {
            verify(fileReaderMock).readFile(inputTestFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIfReaderReturnsCorrectCity() {
        assertEquals(city, content.get("city"));
    }


    @Test
    public void testIfReaderReturnsCorrectCountryCode() {
        assertEquals(countryCode, content.get("countryCode"));
    }


    @Test
    public void testIfReaderReturnsCorrectUnits() {
        assertEquals(units, content.get("units"));
    }
}
