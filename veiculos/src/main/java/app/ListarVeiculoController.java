package app;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Veiculo;
import util.Dao;

public class ListarVeiculoController {
    @FXML
    private TableView<Veiculo> tabelaVeiculos;

    @FXML
    private TableColumn<Veiculo, String> colunaMarca;
    
    @FXML
    private TableColumn<Veiculo, String> colunaModelo;
    
    @FXML
    private TableColumn<Veiculo, String> colunaPlaca;

    ObservableList<Veiculo> lista;
    List<Veiculo> todos;

    @FXML
    private void initialize() {
        
        Dao<Veiculo> dao = new Dao(Veiculo.class);
        todos = dao.listarTodos();
        lista = FXCollections.observableArrayList(todos);

        colunaPlaca = new TableColumn<>("Placa");
        colunaModelo = new TableColumn<>("Modelo");
        colunaMarca = new TableColumn<>("Marca");

        colunaPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colunaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));

        tabelaVeiculos.getColumns().addAll(colunaPlaca, colunaModelo, colunaMarca);
        tabelaVeiculos.setItems(lista);
        
    }

    @FXML
    public void voltarAoMenu() throws IOException {
        App.setRoot("menu");
    }
    
}
