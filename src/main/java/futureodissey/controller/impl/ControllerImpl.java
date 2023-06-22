package futureodissey.controller.impl;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import futureodissey.controller.api.Controller;
import futureodissey.db.impl.FazioneTable;
import futureodissey.db.impl.RichiestaTable;
import futureodissey.db.impl.RisorsaTable;
import futureodissey.db.impl.TaskTypeTable;
import futureodissey.model.api.Model;
import futureodissey.model.impl.ModelImpl;
import futureodissey.model.impl.dataXml.RichiestaList;
import futureodissey.model.impl.dataXml.RisorsaList;
import futureodissey.model.impl.dataXml.TaskTypeList;
import futureodissey.model.impl.rowtype.Fazione;
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
        System.out.println(model.getAllElement(RisorsaTable.class).size());
        System.out.println(model.getAllElement(TaskTypeTable.class).size());
        System.out.println(model.getAllElement(RichiestaTable.class).size());
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
            model.addElement(new TaskType(value.getCodice(), value.getDescrizione(), value.getNumPersone(), value.getTempo()));
        }

        final JAXBContext jaxbContext2 = JAXBContext.newInstance(RichiestaList.class);
        final Unmarshaller unmarshaller2 = jaxbContext2.createUnmarshaller();
        final var richiestaList = (RichiestaList) unmarshaller2.unmarshal(ClassLoader.getSystemResource("xml/richiesta.xml"));
        for (var value : richiestaList.getRichiesta()) {
            model.addElement(new Richiesta(value.getRisorsa(), value.getCodice(), value.getQuantita()));
        }
    }

    @Override
    public void fazione(String nomeFazione, String NomeCapitano, boolean isAdd) {
        if (isAdd) {
            model.addElement(new Fazione(nomeFazione, NomeCapitano));
        } else {
            model.removeElement(new Fazione(nomeFazione, NomeCapitano));
        }
    }

    @Override
    public List<Fazione> getAllFazioni() {
        return model.getAllElement(FazioneTable.class)
            .stream()
            .map(l -> (Fazione)l)
            .toList();
    }

    
    
}
