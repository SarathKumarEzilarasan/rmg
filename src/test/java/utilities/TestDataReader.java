package utilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class TestDataReader {
    private static JSONParser jsonParser = new JSONParser();
    private static JSONObject testDataJson;

    private TestDataReader() {

    }

    public static void init() {
        //excel, csv , json , database
        if (testDataJson == null) {
            try {
                testDataJson = (JSONObject) jsonParser
                        .parse(new FileReader("src/test/resources/testData.json"));
            } catch (Exception e) {
                System.out.println("Test Data file not found");
            }
        }
    }

    public static JSONObject getTestData(String testCaseName) {
        return (JSONObject) testDataJson.get(testCaseName);
    }

}
