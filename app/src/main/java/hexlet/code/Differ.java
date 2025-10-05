package hexlet.code;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public final class Differ {

    private Differ() { }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String c1 = readFile(filePath1);
        String c2 = readFile(filePath2);

        Map<String, Object> left  = Parser.parse(c1, filePath1);
        Map<String, Object> right = Parser.parse(c2, filePath2);

        List<DiffEntry> diff = DiffBuilder.build(left, right);
        return Formatter.render(format, diff);
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
}
