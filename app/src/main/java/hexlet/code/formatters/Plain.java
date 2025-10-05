package hexlet.code.formatters;

import hexlet.code.DiffEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Plain {

    private Plain() {
    }

    public static String render(List<DiffEntry> diffs) {
        List<String> lines = new ArrayList<>();
        for (DiffEntry d : diffs) {
            switch (d.getType()) {
                case UNCHANGED:
                    break;
                case REMOVED:
                    lines.add("Property '" + d.getKey() + "' was removed");
                    break;
                case ADDED:
                    lines.add("Property '" + d.getKey() + "' was added with value: " + toPlainValue(d.getNewValue()));
                    break;
                case UPDATED:
                    lines.add("Property '" + d.getKey() + "' was updated. From " + toPlainValue(d.getOldValue())
                            + " to " + toPlainValue(d.getNewValue()));
                    break;
                default:
                    throw new IllegalStateException("Unknown diff type: " + d.getType());
            }
        }
        return String.join("\n", lines);
    }

    private static String toPlainValue(Object v) {
        if (v == null) {
            return "null";
        }
        if (v instanceof String) {
            return "'" + v + "'";
        }
        if (v instanceof Map || v instanceof Iterable) {
            return "[complex value]";
        }
        return String.valueOf(v);
    }
}
