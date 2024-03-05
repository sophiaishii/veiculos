package app;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import modelo.Motorista;
import modelo.UsoVeiculo;
import modelo.Veiculo;
import util.Dao;

public class RetiradaController {

    // Veiculos
    @FXML
    private ChoiceBox<Veiculo> choiceVeiculos;

    private ObservableList<Veiculo> listaObVeiculo;
    private List<Veiculo> listaVeiculo;
    private Dao<Veiculo> daoVeiculo;

    // Motoristas
    @FXML
    private ChoiceBox<Motorista> choiceMotoristas;

    private ObservableList<Motorista> listaObMotorista;
    private List<Motorista> listaMotorista;
    private Dao<Motorista> daoMotorista;

    // Retirada
    UsoVeiculo usoVeiculo = new UsoVeiculo();
    private Dao<UsoVeiculo> daoRetirada = new Dao(UsoVeiculo.class);

    @FXML
    private void initialize() {
        daoVeiculo = new Dao(Veiculo.class);
        listaVeiculo = daoVeiculo.listarTodos();
        listaObVeiculo = FXCollections.observableArrayList(listaVeiculo);
        for (Veiculo veiculo : listaVeiculo) {
            if (veiculo.getDisponivel() == false) {
                listaObVeiculo.remove(veiculo);
            }
        }
        choiceVeiculos.setItems(listaObVeiculo);

        daoMotorista = new Dao(Motorista.class);
        listaMotorista = daoMotorista.listarTodos();
        listaObMotorista = FXCollections.observableArrayList(listaMotorista);
        choiceMotoristas.setItems(listaObMotorista);
    }

    @FXML
    private void retirarVeiculo() {
        Veiculo veiculo = choiceVeiculos.getValue();
        Motorista motorista = choiceMotoristas.getValue();
        LocalDate data = LocalDate.now();

        usoVeiculo.setVeiculo(veiculo);
        usoVeiculo.setMotorista(motorista);
        usoVeiculo.setRetirada(data);
        usoVeiculo.setDevolucao(null);
        // Setar o veiculo como indisponivel
        veiculo.setDisponivel(false);

        daoRetirada.inserir(usoVeiculo);
        daoVeiculo.alterar(veiculo);
        limparCampos();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Veículo retirado com sucesso");
        alert.show();
    }

    @FXML
    private void limparCampos() {
        choiceVeiculos.setValue(null);
        choiceMotoristas.setValue(null);

    }
    
    @FXML
    private void voltarAoMenu() throws IOException {
        App.setRoot("menu");
    }
}
