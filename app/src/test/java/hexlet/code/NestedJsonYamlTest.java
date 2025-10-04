package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NestedJsonYamlTest {
    private static final String EXPECTED_STYLISH =
            "{\n" +
                    "    chars1: [a, b, c]\n" +
                    "  - chars2: [d, e, f]\n" +
                    "  + chars2: false\n" +
                    "  - checked: false\n" +
                    "  + checked: true\n" +
                    "  - default: null\n" +
                    "  + default: [value1, value2]\n" +
                    "  - id: 45\n" +
                    "  + id: null\n" +
                    "  - key1: value1\n" +
                    "  + key2: value2\n" +
                    "    numbers1: [1, 2, 3, 4]\n" +
                    "  - numbers2: [2, 3, 4, 5]\n" +
                    "  + numbers2: [22, 33, 44, 55]\n" +
                    "  - numbers3: [3, 4, 5]\n" +
                    "  + numbers4: [4, 5, 6]\n" +
                    "  + obj1: {nestedKey=value, isNested=true}\n" +
                    "  - setting1: Some value\n" +
                    "  + setting1: Another value\n" +
                    "  - setting2: 200\n" +
                    "  + setting2: 300\n" +
                    "  - setting3: true\n" +
                    "  + setting3: none\n" +
                    "}";

    @Test
    void json_nested_stylish() throws Exception {
        String actual = Differ.generate(
                "nested/file1.json",
                "nested/file2.json",
                "stylish"
        );
        assertThat(actual).isEqualTo(EXPECTED_STYLISH);
    }

    @Test
    void yaml_nested_stylish() throws Exception {
        String actual = Differ.generate(
                "nested/file1.yml",
                "nested/file2.yml",
                "stylish"
        );
        assertThat(actual).isEqualTo(EXPECTED_STYLISH);
    }
}
