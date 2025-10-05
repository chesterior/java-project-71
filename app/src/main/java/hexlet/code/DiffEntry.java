package hexlet.code;

public final class DiffEntry {
    public enum Type {
        ADDED,
        REMOVED,
        UNCHANGED,
        UPDATED
    }

    private final String key;
    private final Type type;
    private final Object oldValue;
    private final Object newValue;

    private DiffEntry(String key, Type type, Object oldValue, Object newValue) {
        this.key = key;
        this.type = type;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public static DiffEntry added(String key, Object val) {
        return new DiffEntry(key, Type.ADDED, null, val);
    }

    public static DiffEntry removed(String key, Object val) {
        return new DiffEntry(key, Type.REMOVED, val, null);
    }

    public static DiffEntry unchanged(String key, Object val) {
        return new DiffEntry(key, Type.UNCHANGED, val, val);
    }

    public static DiffEntry updated(String key, Object oldV, Object nv) {
        return new DiffEntry(key, Type.UPDATED, oldV, nv);
    }

    public String getKey() {
        return key;
    }

    public Type getType() {
        return type;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
