package futureodissey.model.api;

import java.util.List;
import java.util.Optional;

import futureodissey.db.api.Table;
import futureodissey.model.api.rowtype.RowType;
import futureodissey.model.impl.rowtype.Pianeta;
import futureodissey.model.impl.rowtype.Task;

public interface Model {
    void addElement(RowType<? extends Object> row);

    void removeElement(RowType<? extends Object> row);

    List<RowType<? extends Object>> getAllElement(Class<? extends Table> tableClass);

    RowType<? extends Object> getByPrimaryKey(Object key, Class<? extends Table> tableClass);

    List<String> getNomeInsediamentoFromNomeFazione (String nomeFazione);

    List<Pianeta> getFreePianeta();

    List<String> getInsediamentoRisorsaAltruiFromNomeFazione (String nomeFazione);

    void creaTask(int codiceTask, String nomeFazione, Optional<String> nomeInsediamento1,
        Optional<String> nomeInsediamento2, Optional<String> nomePianeta, final int num);
    
    List<Task> getTaskFromNomeFazione(final String nomeFazione);
}
