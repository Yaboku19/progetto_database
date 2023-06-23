package futureodissey.view.impl.javafx;

import futureodissey.controller.api.Controller;
import futureodissey.controller.impl.ControllerImpl;
import futureodissey.model.impl.rowtype.Disponibilita;
import futureodissey.model.impl.rowtype.Fazione;
import futureodissey.model.impl.rowtype.Task;
import futureodissey.view.api.View;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Represents the {@link View} controller.
 */
public class ViewImpl implements View {
    private Parent root;
    private MainViewController mainViewController;
    private AdminViewController adminViewController;
    private FazioneViewController fazioneViewController;
    private static final double SETTINGS_MIN_HEIGHT = 430;
    private static final double SETTINGS_MIN_WIDTH = 600;
    private Controller controller;

    public void start(final Stage stage) {
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("layout/main.fxml"));
        View.addDeciderList("admin");
        try {
            root = loader.load();
            mainViewController = loader.getController();
            controller = new ControllerImpl(this);
            mainViewController.setViewController(this);
            final Scene mainViewScene = new Scene(root);
            stage.setTitle("Future-Odissey");
            stage.setScene(mainViewScene);
            stage.setMinHeight(SETTINGS_MIN_HEIGHT);
            stage.setMinWidth(SETTINGS_MIN_WIDTH);
            stage.setOnCloseRequest(e -> {
                Platform.exit();
                exit();
            });
            stage.show();
        } catch (IOException e) {
            //LOGGER.log(Level.SEVERE, "Main scene load error during start.");
            e.printStackTrace();
        }
        
    }

    private void exit() {
        System.exit(0);
    }

    @Override
    public void changeStatus(String value, ActionEvent event) {
        if (value == "admin") {
            setAdminController(converterFromEvent(event));
        } else if (controller.getAllFazioni().stream().map(f -> f.getNomeFazione()).toList().contains(value)){
            setFazioneController(converterFromEvent(event), value);
        } else {
            setMainController(converterFromEvent(event));
        }
    }

    private void setMainController(Stage stage) {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("layout/main.fxml"));
            final Scene main = new Scene(fxmlLoader.load());
            mainViewController = (MainViewController) fxmlLoader.getController();
            mainViewController.setViewController(this);
            stage.setScene(main);
            stage.sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAdminController(Stage stage) {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("layout/admin.fxml"));
            final Scene admin = new Scene(fxmlLoader.load());
            adminViewController = (AdminViewController) fxmlLoader.getController();
            adminViewController.setViewController(this);
            stage.setScene(admin);
            stage.sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFazioneController(Stage stage, String value) {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("layout/fazione.fxml"));
            final Scene fazione = new Scene(fxmlLoader.load());
            fazioneViewController = (FazioneViewController) fxmlLoader.getController();
            fazioneViewController.setViewController(this);
            fazioneViewController.setLabel(value, controller
                                                    .getAllFazioni()
                                                    .stream()
                                                    .filter(f -> f.getNomeFazione().equals(value))
                                                    .map(f -> f.getNomeCapitano())
                                                    .findFirst()
                                                    .get());
            fazioneViewController.setPianetaFree();
            fazioneViewController.setInsediamentoDecider();
            fazioneViewController.setAttaccareDecider();
            stage.setScene(fazione);
            stage.sizeToScene();
        } catch (IOException e) {
            System.out.println("bro");
            e.printStackTrace();
        }
    }

    static Stage converterFromEvent(final ActionEvent event) {
        final Node node = (Node) event.getSource();
        final var converter = node.getScene().getWindow();
        if (!(converter instanceof Stage)) {
            throw new AssertionError("Unexpected type: " + converter);
        }
        return (Stage) converter;
    }

    public void fazione(final String nomeNazione, final String nomeCapitano, final boolean isAdd) {
        controller.fazione(nomeNazione, nomeCapitano, isAdd);
    }

    @Override
    public List<Fazione> getAllFazioni() {
        return controller.getAllFazioni();
    }

    @Override
    public String info(final int code) {
        return controller.getInfo(code);
    }

    @Override
    public List<Disponibilita> getAllRisorseDisponibiliFromNomeFazione(final String nomeFazione) {
        return controller.getAllRisorseDisponibiliFromNomeFazione(nomeFazione);
    }

    @Override
    public List<String> getAllNomeInsediamento(final String nomeFazione) {
        return controller.getAllInsediamenti(nomeFazione);
    }

    @Override
    public List<String> getNomePianetaNomeRisorseFree() {
        return controller.getNomePianetaNomeRisorsaFree();
    }

    @Override
    public List<String> getNomeInsediamentoRisorsaAltruiFromFazione(final String nomeFazione) {
        return controller.getInsediamentoRisorsaAltruiFromFazione(nomeFazione);
    }

    @Override
    public List<Task> getTaskFromNomeFazione(final String nomeFazione) {
        return controller.getTaskFromNomeFazione(nomeFazione);
    }

    @Override
    public void creaTask(int codiceTask, String nomeFazione, Optional<String> nomeInsediamento,
        Optional<String> nomePianeta, final int num) {
        System.out.println("sei un figo");
    }
}
