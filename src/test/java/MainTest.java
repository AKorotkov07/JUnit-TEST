import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MainTest {

    @Test
    public void parseCSV_validInput_returnsListOfEmployees() {
        // given:
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";

        // when:
        List<Employee> result = Main.parseCSV(columnMapping, fileName);

        // then:
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(1, result.get(0).id);
        Assertions.assertEquals("John", result.get(0).firstName);
        Assertions.assertEquals("Smith", result.get(0).lastName);
        Assertions.assertEquals("USA", result.get(0).country);
        Assertions.assertEquals(25, result.get(0).age);
        Assertions.assertEquals(2, result.get(1).id);
        Assertions.assertEquals("Inav", result.get(1).firstName);
        Assertions.assertEquals("Petrov", result.get(1).lastName);
        Assertions.assertEquals("RU", result.get(1).country);
        Assertions.assertEquals(23, result.get(1).age);
    }

    @Test
    public void listToJson_validInput_returnsJsonString() {
        // given:
        List<Employee> list = List.of(
                new Employee(1, "John", "Smith", "USA", 25),
                new Employee(2, "Inav", "Petrov", "RU", 23)
        );

        // when:
        String result = Main.listToJson(list);

        // then:
        String expectedJson = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"firstName\": \"Inav\",\n" +
                "    \"lastName\": \"Petrov\",\n" +
                "    \"country\": \"RU\",\n" +
                "    \"age\": 23\n" +
                "  }\n" +
                "]";
        Assertions.assertEquals(expectedJson, result);
    }

    @Test
    public void writeString_validInput_writesContentToFile() throws IOException {
        // given:
        String content = "test content";
        String fileName = "test.txt";

        // when:
        Main.writeString(content, fileName);

        // then:
        String fileContent;
        try (FileReader reader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            fileContent = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
        Assertions.assertEquals(content, fileContent);
    }
}