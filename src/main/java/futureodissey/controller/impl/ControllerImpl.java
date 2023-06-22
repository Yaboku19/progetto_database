package futureodissey.controller.impl;

import java.util.List;

import futureodissey.controller.api.Controller;
import futureodissey.db.impl.FazioneTable;
import futureodissey.model.api.Model;
import futureodissey.model.impl.ModelImpl;
import futureodissey.model.impl.rowtype.Fazione;
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
