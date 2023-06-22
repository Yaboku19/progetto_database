package futureodissey.model.api;

import java.util.List;

import futureodissey.db.api.Table;
import futureodissey.model.api.rowtype.RowType;

public interface Model {
    void addElement(RowType<? extends Object> row);

    void removeElement(RowType<? extends Object> row);

    List<RowType<? extends Object>> getAllElement(Class<? extends Table> tableClass);

    RowType<? extends Object> getByPrimaryKey(Object key, Class<? extends Table> tableClass);

    List<String> getNomeInsediamentoFromNomeFazione (String nomeFazione);
}
