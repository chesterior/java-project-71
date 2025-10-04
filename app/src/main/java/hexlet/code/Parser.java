package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON = new ObjectMapper();
    private static final ObjectMapper YAML = new YAMLMapper();

    private Parser() {
    }

    static Map<String, Object> parse(String content, String fileName) throws Exception {
        String ext = ext(fileName);
        switch (ext) {
            case "json":
                return JSON.readValue(content, new TypeReference<Map<String, Object>>() {
                });
            case "yml":
            case "yaml":
                return YAML.readValue(content, new TypeReference<Map<String, Object>>() {
                });
            default:
                throw new IllegalArgumentException("Unsupported file extension: " + ext);
        }
    }

    private static String ext(String name) {
        int i = name.lastIndexOf('.');
        return (i >= 0 && i < name.length() - 1) ? name.substring(i + 1).toLowerCase() : "";
    }
}
