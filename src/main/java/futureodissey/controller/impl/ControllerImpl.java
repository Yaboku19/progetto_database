package futureodissey.controller.impl;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import futureodissey.controller.api.Controller;
import futureodissey.db.impl.DisponibilitaTable;
import futureodissey.db.impl.FazioneTable;
import futureodissey.db.impl.InsediamentoTable;
import futureodissey.db.impl.RisorsaTable;
import futureodissey.db.impl.TaskTypeTable;
import futureodissey.model.api.Model;
import futureodissey.model.api.rowtype.RowType;
import futureodissey.model.impl.ModelImpl;
import futureodissey.model.impl.dataXml.PianetaList;
import futureodissey.model.impl.dataXml.RichiestaList;
import futureodissey.model.impl.dataXml.RisorsaList;
import futureodissey.model.impl.dataXml.TaskTypeList;
import futureodissey.model.impl.rowtype.Disponibilita;
import futureodissey.model.impl.rowtype.Fazione;
import futureodissey.model.impl.rowtype.Insediamento;
import futureodissey.model.impl.rowtype.Pianeta;
import futureodissey.model.impl.rowtype.Richiesta;
import futureodissey.model.impl.rowtype.Risorsa;
import futureodissey.model.impl.rowtype.TaskType;
import futureodissey.view.api.View;

public class ControllerImpl implements Controller {
    private final View view;
    private final String username = "root";
    private final String password = "Emanuele2002!";
    private final String dbName = "futureodissey";
    private final Model model;

    public ControllerImpl(View view) {
        this.view = view;
        model = new ModelImpl(username, password, dbName);
        try {
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws Exception{
        final JAXBContext jaxbContext = JAXBContext.newInstance(RisorsaList.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        final var risorseList = (RisorsaList) unmarshaller.unmarshal(ClassLoader.getSystemResource("xml/risorsa.xml"));
        for (var value : risorseList.getRisorse()) {
            model.addElement(new Risorsa(value.getName()));
        }

        final JAXBContext jaxbContext1 = JAXBContext.newInstance(TaskTypeList.class);
        final Unmarshaller unmarshaller1 = jaxbContext1.createUnmarshaller();
        final var taskTypeList = (TaskTypeList) unmarshaller1.unmarshal(ClassLoader.getSystemResource("xml/taskType.xml"));
        for (var value : taskTypeList.getTaskType()) {
            model.addElement(new TaskType(value.getCodice(), value.getDescrizione(), value.getNumPersone()));
        }

        final JAXBContext jaxbContext2 = JAXBContext.newInstance(RichiestaList.class);
        final Unmarshaller unmarshaller2 = jaxbContext2.createUnmarshaller();
        final var richiestaList = (RichiestaList) unmarshaller2.unmarshal(ClassLoader.getSystemResource("xml/richiesta.xml"));
        for (var value : richiestaList.getRichiesta()) {
            model.addElement(new Richiesta(value.getRisorsa(), value.getCodice(), value.getQuantita()));
        }

        final JAXBContext jaxbContext3 = JAXBContext.newInstance(PianetaList.class);
        final Unmarshaller unmarshaller3 = jaxbContext3.createUnmarshaller();
        final var pianetaList = (PianetaList) unmarshaller3.unmarshal(ClassLoader.getSystemResource("xml/pianeta.xml"));
        for (var value : pianetaList.getPianeta()) {
            model.addElement(new Pianeta(value.getName(), value.getRisorsa()));
        }
    }

    @Override
    public void fazione(String nomeFazione, String NomeCapitano, boolean isAdd) {
        addRemove(new Fazione(nomeFazione, NomeCapitano), isAdd);
        for (var value : model.getAllElement(RisorsaTable.class)) {
            addRemove(new Disponibilita((String) value.getKey(), nomeFazione, 30), isAdd);
        }
    }

    private <T extends RowType<? extends Object>> void addRemove(T value, boolean isAdd) {
        if(isAdd) {
            model.addElement(value);
        } else {
            model.removeElement(value);
        }
    }

    @Override
    public List<Fazione> getAllFazioni() {
        return model.getAllElement(FazioneTable.class)
            .stream()
            .map(l -> (Fazione)l)
            .toList();
    }

    @Override
    public String getInfo(int code) {
        TaskType taskType = (TaskType) model.getByPrimaryKey(code, TaskTypeTable.class);
        return taskType.getDescrizione();
    }

    @Override
    public List<Disponibilita> getAllRisorseDisponibili() {
        return model.getAllElement(DisponibilitaTable.class)
            .stream()
            .map(l -> (Disponibilita)l)
            .toList();
    }

    @Override
    public List<String> getAllInsediamenti(String nomeFazione) {
        return model.getNomeInsediamentoFromNomeFazione(nomeFazione);
    }
}
