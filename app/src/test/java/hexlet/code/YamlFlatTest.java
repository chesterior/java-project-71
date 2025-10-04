package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class YamlFlatTest {

    @Test
    void compareFlatYamlStylish() throws Exception {
        String path1 = "file1.yml";
        String path2 = "file2.yml";

        String expected = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";

        String actual = Differ.generate(path1, path2, "stylish");
        assertThat(actual).isEqualTo(expected);
    }
}
