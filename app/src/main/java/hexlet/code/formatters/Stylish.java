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
            switch (d.getType()) {
                case UNCHANGED:
                    sb.append("    ").append(d.getKey()).append(": ").append(stringify(d.getOldValue())).append("\n");
                    break;
                case REMOVED:
                    sb.append("  - ").append(d.getKey()).append(": ").append(stringify(d.getOldValue())).append("\n");
                    break;
                case ADDED:
                    sb.append("  + ").append(d.getKey()).append(": ").append(stringify(d.getNewValue())).append("\n");
                    break;
                case UPDATED:
                    sb.append("  - ").append(d.getKey()).append(": ").append(stringify(d.getOldValue())).append("\n");
                    sb.append("  + ").append(d.getKey()).append(": ").append(stringify(d.getNewValue())).append("\n");
                    break;
                default:
                    throw new IllegalStateException("Unknown diff type: " + d.getType());
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private static String stringify(Object v) {
        if (v == null) {
            return "null";
        }
        return String.valueOf(v);
    }
}
