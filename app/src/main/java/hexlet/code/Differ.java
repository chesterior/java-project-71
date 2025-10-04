package hexlet.code;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    private Differ() {
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String content1 = readFile(filePath1);
        String content2 = readFile(filePath2);

        Map<String, Object> left = Parser.parse(content1, filePath1);
        Map<String, Object> right = Parser.parse(content2, filePath2);

        switch (format) {
            case "stylish":
                return buildStylishDiff(left, right);
            default:
                throw new IllegalArgumentException("Unknown format: " + format);
        }
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
                if (Objects.equals(lv, rv)) {
                    sb.append("    ").append(key).append(": ").append(stringify(lv)).append("\n");
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

    private static String stringify(Object v) {
        if (v == null) return "null";
        // строки в stylish показываем без кавычек — как в примере
        return String.valueOf(v);
    }

    // читаем либо из ресурсов, либо с диска
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
}
