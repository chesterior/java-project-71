package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new YAMLMapper();

    private Parser() {
    }

    static Map<String, Object> parse(String content, String fileName) throws Exception {
        String ext = getExtension(fileName);
        switch (ext) {
            case "json":
                return JSON_MAPPER.readValue(content, new TypeReference<Map<String, Object>>() {
                });
            case "yml":
            case "yaml":
                return YAML_MAPPER.readValue(content, new TypeReference<Map<String, Object>>() {
                });
            default:
                throw new IllegalArgumentException("Unsupported file extension: " + ext);
        }
    }

    private static String getExtension(String fileName) {
        int idx = fileName.lastIndexOf('.');
        return (idx >= 0 && idx < fileName.length() - 1) ? fileName.substring(idx + 1).toLowerCase() : "";
    }
}
