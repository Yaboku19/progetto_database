package futureodissey.model.api.rowtype;

public interface RowType<T> {
    public boolean isSameClass(Object object);

    public T getKey();
}
