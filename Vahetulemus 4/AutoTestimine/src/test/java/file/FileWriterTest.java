package file;

import openWeather.file.FileWriter;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FileWriterTest {
    private static final String OUTPUT_TEST = "C:/Users/User/IdeaProjects/AutoTestimine/output_test.txt";

    @BeforeClass
    public static void setUpTests() {
    }

    @Test
    public void testIfFileIsWritten() {
        FileWriter fileWriterMock = mock(FileWriter.class);
        JSONObject randomJSONObject = new JSONObject();
        randomJSONObject.put("city", "Tallinn");
        verify(fileWriterMock).writeJsonToFile(randomJSONObject, OUTPUT_TEST);
    }
}