package app;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import modelo.Veiculo;
import util.Dao;

public class NovoVeiculoController {
    @FXML
    private TextField campoPlaca;

    @FXML
    private TextField campoModelo;

    @FXML
    private TextField campoMarca;


    @FXML
    private void cadastrarVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(campoPlaca.getText());
        veiculo.setModelo(campoModelo.getText());
        veiculo.setMarca(campoMarca.getText());
        veiculo.setDisponivel(true);
        Dao<Veiculo> dao = new Dao(Veiculo.class);
        dao.inserir(veiculo);
        limparCampos();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Veiculo cadastrado");
        alert.show();
    }

    @FXML
    private void limparCampos() {
        campoPlaca.setText("");
        campoModelo.setText("");
        campoMarca.setText("");
    }

    @FXML
    private void voltarAoMenu() throws IOException {
        App.setRoot("menu");
    }
}
