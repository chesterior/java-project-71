package hexlet.code;

public final class DiffEntry {
    public enum Type {ADDED, REMOVED, UNCHANGED, UPDATED}

    public final String key;
    public final Type type;
    public final Object oldValue;
    public final Object newValue;

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
}
