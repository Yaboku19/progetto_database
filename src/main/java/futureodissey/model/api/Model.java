package futureodissey.model.api;

import futureodissey.model.api.rowtype.RowType;

public interface Model {
    public void addElement(RowType row);

    public void removeElement(RowType row);
}
