package futureodissey.model.api;

import futureodissey.model.api.rowtype.RowType;

public interface Model {
    public <T> void addElement(RowType row);
}
