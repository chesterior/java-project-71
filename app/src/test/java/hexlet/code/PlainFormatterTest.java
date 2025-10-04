package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlainFormatterTest {

    private static final String EXPECTED =
            "Property 'chars2' was updated. From [complex value] to false\n" +
                    "Property 'checked' was updated. From false to true\n" +
                    "Property 'default' was updated. From null to [complex value]\n" +
                    "Property 'id' was updated. From 45 to null\n" +
                    "Property 'key1' was removed\n" +
                    "Property 'key2' was added with value: 'value2'\n" +
                    "Property 'numbers2' was updated. From [complex value] to [complex value]\n" +
                    "Property 'numbers3' was removed\n" +
                    "Property 'numbers4' was added with value: [complex value]\n" +
                    "Property 'obj1' was added with value: [complex value]\n" +
                    "Property 'setting1' was updated. From 'Some value' to 'Another value'\n" +
                    "Property 'setting2' was updated. From 200 to 300\n" +
                    "Property 'setting3' was updated. From true to 'none'";

    @Test
    void json_plain() throws Exception {
        String actual = Differ.generate(
                "nested/file1.json",
                "nested/file2.json",
                "plain"
        );
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    void yaml_plain() throws Exception {
        String actual = Differ.generate(
                "nested/file1.yml",
                "nested/file2.yml",
                "plain"
        );
        assertThat(actual).isEqualTo(EXPECTED);
    }
}
