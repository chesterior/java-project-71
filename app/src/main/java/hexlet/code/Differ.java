package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String generate(String filePath1, String filePath2) throws Exception {
        String content1 = readFile(filePath1);
        String content2 = readFile(filePath2);

        Map<String, Object> left = parse(content1);
        Map<String, Object> right = parse(content2);

        return buildStylishDiff(left, right);
    }

    private static String buildStylishDiff(Map<String, Object> left, Map<String, Object> right) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(left.keySet());
        keys.addAll(right.keySet());

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (String key : keys) {
            boolean inLeft = left.containsKey(key);
            boolean inRight = right.containsKey(key);

            Object lv = left.get(key);
            Object rv = right.get(key);

            if (inLeft && inRight) {
                if (equalsByValue(lv, rv)) {
                    sb.append("  ").append("  ").append(key).append(": ").append(stringify(lv)).append("\n");
                } else {
                    sb.append("  - ").append(key).append(": ").append(stringify(lv)).append("\n");
                    sb.append("  + ").append(key).append(": ").append(stringify(rv)).append("\n");
                }
            } else if (inLeft) {
                sb.append("  - ").append(key).append(": ").append(stringify(lv)).append("\n");
            } else {
                sb.append("  + ").append(key).append(": ").append(stringify(rv)).append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private static boolean equalsByValue(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }

    private static String stringify(Object v) {
        if (v == null) return "null";
        if (v instanceof String) return (String) v;
        return String.valueOf(v);
    }

    private static String readFile(String pathStr) throws Exception {
        try (InputStream is = Differ.class.getClassLoader().getResourceAsStream(pathStr)) {
            if (is != null) {
                byte[] bytes = is.readAllBytes();
                return new String(bytes, StandardCharsets.UTF_8);
            }
        }
        Path path = Path.of(pathStr).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File not found: " + path);
        }
        return Files.readString(path);
    }

    private static Map<String, Object> parse(String content) throws Exception {
        return MAPPER.readValue(content, new TypeReference<Map<String, Object>>() {
        });
    }
}
