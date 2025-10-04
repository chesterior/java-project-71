package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Json {

    private Json() {
    }

    public static String render(List<DiffEntry> diffs) {
        List<Map<String, Object>> out = new ArrayList<>();
        for (DiffEntry d : diffs) {
            Map<String, Object> item = new HashMap<>();
            item.put("key", d.key);
            switch (d.type) {
                case ADDED:
                    item.put("type", "added");
                    item.put("value", d.newValue);
                    break;
                case REMOVED:
                    item.put("type", "removed");
                    item.put("value", d.oldValue);
                    break;
                case UNCHANGED:
                    item.put("type", "unchanged");
                    item.put("value", d.oldValue);
                    break;
                case UPDATED:
                    item.put("type", "updated");
                    item.put("oldValue", d.oldValue);
                    item.put("newValue", d.newValue);
                    break;
            }
            out.add(item);
        }

        try {
            return new ObjectMapper().writeValueAsString(out);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize diff to JSON", e);
        }
    }
}
