package hexlet.code;

import java.util.*;

public final class DiffBuilder {
    private DiffBuilder() {
    }

    public static List<DiffEntry> build(Map<String, Object> left, Map<String, Object> right) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(left.keySet());
        keys.addAll(right.keySet());

        List<DiffEntry> result = new ArrayList<>();
        for (String k : keys) {
            boolean inLeft = left.containsKey(k);
            boolean inRight = right.containsKey(k);
            Object l = left.get(k);
            Object r = right.get(k);

            if (inLeft && !inRight) {
                result.add(DiffEntry.removed(k, l));
            } else if (!inLeft && inRight) {
                result.add(DiffEntry.added(k, r));
            } else {
                if (Objects.equals(l, r)) {
                    result.add(DiffEntry.unchanged(k, l));
                } else {
                    result.add(DiffEntry.updated(k, l, r));
                }
            }
        }
        return result;
    }
}
