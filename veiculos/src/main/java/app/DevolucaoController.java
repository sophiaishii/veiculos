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

public class DevolucaoController {
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

    // Devolucao
    UsoVeiculo usoVeiculo;
    private Dao<UsoVeiculo> daoDevolucao = new Dao(UsoVeiculo.class);

    @FXML
    private void initialize() {
        daoVeiculo = new Dao(Veiculo.class);
        listaVeiculo = daoVeiculo.listarTodos();
        listaObVeiculo = FXCollections.observableArrayList(listaVeiculo);
        for (Veiculo veiculo : listaVeiculo) {
            if (veiculo.getDisponivel() == true) {
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
    private void devolverVeiculo(){
        Veiculo veiculo = choiceVeiculos.getValue();
        Motorista motorista = choiceMotoristas.getValue();
        LocalDate dataDevolucao = LocalDate.now();

        // Buscar o uso do veiculo pelo ID do veiculo
        usoVeiculo = daoDevolucao.buscarPorCarro(veiculo.getCodigo());


        usoVeiculo.setDevolucao(dataDevolucao);
        veiculo.setDisponivel(true);

        daoDevolucao.alterar(usoVeiculo);
        daoVeiculo.alterar(veiculo);
        limparCampos();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Devolucao confirmada");
        alert.show();  
        // daoDevolucao.alterar(usoVeiculo);
    }

    @FXML
    public void limparCampos() {
        choiceVeiculos.setValue(null);
        choiceMotoristas.setValue(null);
    }

    @FXML
    private void voltarAoMenu() throws IOException {
        App.setRoot("menu");
    }
    
}
