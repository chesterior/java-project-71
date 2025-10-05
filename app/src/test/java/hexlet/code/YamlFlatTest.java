package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class YamlFlatTest {

    @Test
    void yamlFlatStylish() throws Exception {
        String expected = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";

        String actual = Differ.generate("file1.yml", "file2.yml", "stylish");
        assertThat(actual).isEqualTo(expected);
    }
}
