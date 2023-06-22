package futureodissey.controller.impl;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import futureodissey.controller.api.Controller;
import futureodissey.db.impl.FazioneTable;
import futureodissey.db.impl.RisorsaTable;
import futureodissey.model.api.Model;
import futureodissey.model.impl.ModelImpl;
import futureodissey.model.impl.dataXml.RisorsaList;
import futureodissey.model.impl.rowtype.Fazione;
import futureodissey.model.impl.rowtype.Risorsa;
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
        System.out.println(model.getAllElement(RisorsaTable.class));
    }

    private void initialize() throws Exception{
        final JAXBContext jaxbContext = JAXBContext.newInstance(RisorsaList.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        final var risorseList = (RisorsaList) unmarshaller.unmarshal(ClassLoader.getSystemResource("xml/risorsa.xml"));
        for (var value : risorseList.getRisorse()) {
            model.addElement(new Risorsa(value.getName()));
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
