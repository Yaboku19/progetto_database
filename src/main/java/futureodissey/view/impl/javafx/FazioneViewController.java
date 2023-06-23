package futureodissey.view.impl.javafx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import futureodissey.view.api.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FazioneViewController {
    private View controller;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label NomeCapitanoText;

    @FXML
    private TextField TransferUominiText;

    @FXML
    private Button attaccareBtn;

    @FXML
    private ChoiceBox<String> attaccareDecider;

    @FXML
    private Button attaccareInfo;

    @FXML
    private Button creaGuerrieriBtn;

    @FXML
    private ChoiceBox<String> creaGuerrieriDecider;

    @FXML
    private Button creaGuerrieriInfo;

    @FXML
    private TextField creaGuerrieriText;

    @FXML
    private Button creaInsediamentoBtn;

    @FXML
    private ChoiceBox<String> creaInsediamentoDecider;

    @FXML
    private Button creaInsediamentoInfo;

    @FXML
    private TextField creaInsediamentoText;

    @FXML
    private Button creaLavoratoriBtn;

    @FXML
    private ChoiceBox<String> creaLavoratoriDecider;

    @FXML
    private Button creaLavoratoriInfo;

    @FXML
    private TextField creaLavoratoriText;

    @FXML
    private ChoiceBox<String> deciderBox;

    @FXML
    private Button guerrieriAltruiSeeBtn;

    @FXML
    private TextArea infoFiled;

    @FXML
    private Button guerrieriSeeBtn;

    @FXML
    private Button lavoratoriSeeBtn;

    @FXML
    private Label nomeFazioneText;

    @FXML
    private Button raccogliereRisorseBtn;

    @FXML
    private ChoiceBox<String> raccogliereRisorseDecider;

    @FXML
    private Button raccogliereRisorseInfo;

    @FXML
    private Button risorseSeeBtn;

    @FXML
    private Button taskSeeBtn;

    @FXML
    private ChoiceBox<String> transferUominiADecider;

    @FXML
    private Button transferUominiBtn;

    @FXML
    private ChoiceBox<String> transferUominiDaDecider;

    @FXML
    private Button transferUominiInfo;

    @FXML
    private ChoiceBox<String> transferUominiTipoDecider;

    @FXML
    void attaccare(MouseEvent event) {

    }

    @FXML
    void creaGuerrieri(MouseEvent event) {

    }

    @FXML
    void creaInsediamento(MouseEvent event) {

    }

    @FXML
    void creaLavoratori(MouseEvent event) {

    }

    @FXML
    void getGuerrieri(MouseEvent event) {

    }

    @FXML
    void getGuerrieriAltrui(MouseEvent event) {

    }

    @FXML
    void getLavoratori(MouseEvent event) {

    }

    @FXML
    void getRisorse(MouseEvent event) {
        infoFiled.setText("");
        int counter = 0;
        for(var value : controller.getAllRisorseDisponibili()) {
            counter++;
            infoFiled.appendText("NomeFazione: " + value.getNomeFazione() + 
                " Risorsa: " + value.getNomeRisorsa() + " Quantita': " + value.getQuantita());
            if (counter % 2 == 0) {
                infoFiled.appendText("\n");
            } else {
                infoFiled.appendText("\t");
            }
        }
    }

    @FXML
    void getTask(MouseEvent event) {

    }

    @FXML
    void infoAttaccare(MouseEvent event) {
        infoFiled.setText(controller.info(3));
    }

    @FXML
    void infoCreaGuerrieri(MouseEvent event) {
        infoFiled.setText(controller.info(1));
    }

    @FXML
    void infoCreaInsediamento(MouseEvent event) {
        infoFiled.setText(controller.info(2));
    }

    @FXML
    void infoCreaLavoratori(MouseEvent event) {
        infoFiled.setText(controller.info(0));
    }

    @FXML
    void infoRaccogliereRisorse(MouseEvent event) {
        infoFiled.setText(controller.info(6));
    }

    @FXML
    void infoTransferireUomini(MouseEvent event) {
        infoFiled.setText(controller.info(4) + "\n");
        infoFiled.appendText(controller.info(4));
    }

    @FXML
    void raccogliereRisorse(MouseEvent event) {

    }

    @FXML
    void transferireUomini(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert NomeCapitanoText != null : "fx:id=\"NomeCapitanoText\" was not injected: check your FXML file 'fazione.fxml'.";
        assert TransferUominiText != null : "fx:id=\"TransferUominiText\" was not injected: check your FXML file 'fazione.fxml'.";
        assert attaccareBtn != null : "fx:id=\"attaccareBtn\" was not injected: check your FXML file 'fazione.fxml'.";
        assert attaccareDecider != null : "fx:id=\"attaccareDecider\" was not injected: check your FXML file 'fazione.fxml'.";
        assert attaccareInfo != null : "fx:id=\"attaccareInfo\" was not injected: check your FXML file 'fazione.fxml'.";
        assert creaGuerrieriBtn != null : "fx:id=\"creaGuerrieriBtn\" was not injected: check your FXML file 'fazione.fxml'.";
        assert creaGuerrieriDecider != null : "fx:id=\"creaGuerrieriDecider\" was not injected: check your FXML file 'fazione.fxml'.";
        assert creaGuerrieriInfo != null : "fx:id=\"creaGuerrieriInfo\" was not injected: check your FXML file 'fazione.fxml'.";
        assert creaGuerrieriText != null : "fx:id=\"creaGuerrieriText\" was not injected: check your FXML file 'fazione.fxml'.";
        assert creaInsediamentoBtn != null : "fx:id=\"creaInsediamentoBtn\" was not injected: check your FXML file 'fazione.fxml'.";
        assert creaInsediamentoDecider != null : "fx:id=\"creaInsediamentoDecider\" was not injected: check your FXML file 'fazione.fxml'.";
        assert creaInsediamentoInfo != null : "fx:id=\"creaInsediamentoInfo\" was not injected: check your FXML file 'fazione.fxml'.";
        assert creaInsediamentoText != null : "fx:id=\"creaInsediamentoText\" was not injected: check your FXML file 'fazione.fxml'.";
        assert creaLavoratoriBtn != null : "fx:id=\"creaLavoratoriBtn\" was not injected: check your FXML file 'fazione.fxml'.";
        assert creaLavoratoriDecider != null : "fx:id=\"creaLavoratoriDecider\" was not injected: check your FXML file 'fazione.fxml'.";
        assert creaLavoratoriInfo != null : "fx:id=\"creaLavoratoriInfo\" was not injected: check your FXML file 'fazione.fxml'.";
        assert creaLavoratoriText != null : "fx:id=\"creaLavoratoriText\" was not injected: check your FXML file 'fazione.fxml'.";
        assert deciderBox != null : "fx:id=\"deciderBox\" was not injected: check your FXML file 'fazione.fxml'.";
        assert guerrieriAltruiSeeBtn != null : "fx:id=\"guerrieriAltruiSeeBtn\" was not injected: check your FXML file 'fazione.fxml'.";
        assert guerrieriSeeBtn != null : "fx:id=\"guerrieriSeeBtn\" was not injected: check your FXML file 'fazione.fxml'.";
        assert lavoratoriSeeBtn != null : "fx:id=\"lavoratoriSeeBtn\" was not injected: check your FXML file 'fazione.fxml'.";
        assert nomeFazioneText != null : "fx:id=\"nomeFazioneText\" was not injected: check your FXML file 'fazione.fxml'.";
        assert raccogliereRisorseBtn != null : "fx:id=\"raccogliereRisorseBtn\" was not injected: check your FXML file 'fazione.fxml'.";
        assert raccogliereRisorseDecider != null : "fx:id=\"raccogliereRisorseDecider\" was not injected: check your FXML file 'fazione.fxml'.";
        assert raccogliereRisorseInfo != null : "fx:id=\"raccogliereRisorseInfo\" was not injected: check your FXML file 'fazione.fxml'.";
        assert risorseSeeBtn != null : "fx:id=\"risorseSeeBtn\" was not injected: check your FXML file 'fazione.fxml'.";
        assert taskSeeBtn != null : "fx:id=\"taskSeeBtn\" was not injected: check your FXML file 'fazione.fxml'.";
        assert transferUominiADecider != null : "fx:id=\"transferUominiADecider\" was not injected: check your FXML file 'fazione.fxml'.";
        assert transferUominiBtn != null : "fx:id=\"transferUominiBtn\" was not injected: check your FXML file 'fazione.fxml'.";
        assert transferUominiDaDecider != null : "fx:id=\"transferUominiDaDecider\" was not injected: check your FXML file 'fazione.fxml'.";
        assert transferUominiInfo != null : "fx:id=\"transferUominiInfo\" was not injected: check your FXML file 'fazione.fxml'.";
        assert transferUominiTipoDecider != null : "fx:id=\"transferUominiTipoDecider\" was not injected: check your FXML file 'fazione.fxml'.";

        deciderBox.getItems().addAll(View.getDeciderList());
        deciderBox.setOnAction(this::getchoice);
        transferUominiTipoDecider.getItems().addAll(List.of("Lavoratore", "Guerriero"));
        transferUominiTipoDecider.setOnAction(this::setTransferDecider);
        View.addIntListener(creaLavoratoriText);
        View.addIntListener(creaGuerrieriText);
        View.addIntListener(TransferUominiText);
    }

    private void setTransferDecider(final ActionEvent event) {
        if (transferUominiTipoDecider.getValue().equals("Lavoratore")) {
            transferUominiDaDecider.getItems().remove("Esercito");
            transferUominiADecider.getItems().remove("Esercito");
        } else if (!transferUominiDaDecider.getItems().contains("Esercito") && 
                    !transferUominiADecider.getItems().contains("Esercito")) {
            transferUominiDaDecider.getItems().add("Esercito");
            transferUominiADecider.getItems().add("Esercito");
        }
    }

    public void setViewController(final View controller) {
        this.controller = controller;
    }

    private void getchoice(final ActionEvent event) {
        controller.changeStatus(deciderBox.getValue(), event);
    }

    void setLabel(final String nomeFazione, final String nomeCapitano) {
        this.nomeFazioneText.setText(nomeFazione);
        this.NomeCapitanoText.setText(nomeCapitano);
    }

    void setInsediamentoDecider() {
        creaLavoratoriDecider.getItems().clear();
        creaLavoratoriDecider.getItems().addAll(controller.getAllNomeInsediamento(nomeFazioneText.getText()));
        creaGuerrieriDecider.getItems().clear();
        creaGuerrieriDecider.getItems().addAll(controller.getAllNomeInsediamento(nomeFazioneText.getText()));
        raccogliereRisorseDecider.getItems().clear();
        raccogliereRisorseDecider.getItems().addAll(controller.getAllNomeInsediamento(nomeFazioneText.getText()));
        transferUominiDaDecider.getItems().removeAll(controller.getAllNomeInsediamento(nomeFazioneText.getText()));
        transferUominiDaDecider.getItems().addAll(controller.getAllNomeInsediamento(nomeFazioneText.getText()));
        transferUominiADecider.getItems().removeAll(controller.getAllNomeInsediamento(nomeFazioneText.getText()));
        transferUominiADecider.getItems().addAll(controller.getAllNomeInsediamento(nomeFazioneText.getText()));
    }

    void setPianetaFree() {
        creaInsediamentoDecider.getItems().clear();
        creaInsediamentoDecider.getItems().addAll(controller.getNomePianetaNomeRisorseFree());
    }

    void setAttaccareDecider() {
        attaccareDecider.getItems().clear();
        attaccareDecider.getItems().addAll(
            controller.getNomeInsediamentoRisorsaAltruiFromFazione(nomeFazioneText.getText()));
    }

}