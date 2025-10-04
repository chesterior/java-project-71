package hexlet.code.formatters;

import hexlet.code.DiffEntry;

import java.util.List;

public final class Stylish {
    private Stylish() {
    }

    public static String render(List<DiffEntry> diffs) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (DiffEntry d : diffs) {
            switch (d.type) {
                case UNCHANGED:
                    sb.append("    ").append(d.key).append(": ").append(stringify(d.oldValue)).append("\n");
                    break;
                case REMOVED:
                    sb.append("  - ").append(d.key).append(": ").append(stringify(d.oldValue)).append("\n");
                    break;
                case ADDED:
                    sb.append("  + ").append(d.key).append(": ").append(stringify(d.newValue)).append("\n");
                    break;
                case UPDATED:
                    sb.append("  - ").append(d.key).append(": ").append(stringify(d.oldValue)).append("\n");
                    sb.append("  + ").append(d.key).append(": ").append(stringify(d.newValue)).append("\n");
                    break;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private static String stringify(Object v) {
        if (v == null) return "null";
        return String.valueOf(v);
    }
}
