package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlainFormatterTest {

    private static final String EXPECTED = """
        Property 'chars2' was updated. From [complex value] to false
        Property 'checked' was updated. From false to true
        Property 'default' was updated. From null to [complex value]
        Property 'id' was updated. From 45 to null
        Property 'key1' was removed
        Property 'key2' was added with value: 'value2'
        Property 'numbers2' was updated. From [complex value] to [complex value]
        Property 'numbers3' was removed
        Property 'numbers4' was added with value: [complex value]
        Property 'obj1' was added with value: [complex value]
        Property 'setting1' was updated. From 'Some value' to 'Another value'
        Property 'setting2' was updated. From 200 to 300
        Property 'setting3' was updated. From true to 'none'""";

    @Test
    void jsonPlain() throws Exception {
        String actual = Differ.generate("nested/file1.json", "nested/file2.json", "plain");
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    void ymlPlain() throws Exception {
        String actual = Differ.generate("nested/file1.yml", "nested/file2.yml", "plain");
        assertThat(actual).isEqualTo(EXPECTED);
    }
}
