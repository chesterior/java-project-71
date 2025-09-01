package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true,
        version = "gendiff 0.1.0"
)
public class App implements Runnable {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        try {
            int exitCode = new CommandLine(new App()).execute(args);
            System.exit(exitCode);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(
            names = {"-f", "--format"},
            description = "output format [default: ${DEFAULT-VALUE}]",
            defaultValue = "stylish"
    )
    private String format;

    @Override
    public void run() {
        try {
            String content1 = readFile(filepath1);
            String content2 = readFile(filepath2);

            Map<String, Object> data1 = getData(content1);
            Map<String, Object> data2 = getData(content2);

            System.out.println("file1: " + data1);
            System.out.println("file2: " + data2);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFile(String pathStr) throws Exception {
        try (InputStream is = App.class.getClassLoader().getResourceAsStream(pathStr)) {
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

    public static Map<String, Object> getData(String content) throws Exception {
        return parse(content);
    }

    private static Map<String, Object> parse(String content) throws Exception {
        return MAPPER.readValue(content, new TypeReference<Map<String, Object>>() {
        });
    }
}