package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NestedJsonYamlTest {

    private static final String EXPECTED_STYLISH = """
        {
            chars1: [a, b, c]
          - chars2: [d, e, f]
          + chars2: false
          - checked: false
          + checked: true
          - default: null
          + default: [value1, value2]
          - id: 45
          + id: null
          - key1: value1
          + key2: value2
            numbers1: [1, 2, 3, 4]
          - numbers2: [2, 3, 4, 5]
          + numbers2: [22, 33, 44, 55]
          - numbers3: [3, 4, 5]
          + numbers4: [4, 5, 6]
          + obj1: {nestedKey=value, isNested=true}
          - setting1: Some value
          + setting1: Another value
          - setting2: 200
          + setting2: 300
          - setting3: true
          + setting3: none
        }""";

    @Test
    void jsonNestedStylish() throws Exception {
        String actual = Differ.generate("nested/file1.json", "nested/file2.json", "stylish");
        assertThat(actual).isEqualTo(EXPECTED_STYLISH);
    }

    @Test
    void yamlNestedStylish() throws Exception {
        String actual = Differ.generate("nested/file1.yml", "nested/file2.yml", "stylish");
        assertThat(actual).isEqualTo(EXPECTED_STYLISH);
    }
}
