package futureodissey.model.api;

import java.util.List;

import futureodissey.db.api.Table;
import futureodissey.model.api.rowtype.RowType;

public interface Model {
    public void addElement(RowType row);

    public void removeElement(RowType row);

    public List<RowType<? extends Object>> getAllElement(Class<? extends Table> tableClass);
}
