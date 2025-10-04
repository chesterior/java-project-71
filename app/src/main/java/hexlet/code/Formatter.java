package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;

public final class Formatter {
    private Formatter() {
    }

    public static String render(String format, List<DiffEntry> diffs) {
        switch (format) {
            case "stylish":
                return Stylish.render(diffs);
            case "plain":
                return Plain.render(diffs);
            default:
                throw new IllegalArgumentException("Unknown format: " + format);
        }
    }
}
