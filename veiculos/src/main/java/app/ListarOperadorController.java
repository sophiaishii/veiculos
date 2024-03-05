package app;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Operador;
import util.Dao;

public class ListarOperadorController {
    @FXML
    private TableView<Operador> tabelaOperadores;

    @FXML
    private TableColumn<Operador, String> colunaNome;
    
    @FXML
    private TableColumn<Operador, String> colunaEndereco;
    
    @FXML
    private TableColumn<Operador, String> colunaLogin;

    ObservableList<Operador> lista;
    List<Operador> todos;

    @FXML
    private void initialize() {
        
        Dao<Operador> dao = new Dao(Operador.class);
        todos = dao.listarTodos();
        lista = FXCollections.observableArrayList(todos);

        colunaNome = new TableColumn<>("Nome");
        colunaEndereco = new TableColumn<>("Endereço");
        colunaLogin = new TableColumn<>("Login");

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colunaLogin.setCellValueFactory(new PropertyValueFactory<>("login"));

        tabelaOperadores.getColumns().addAll(colunaNome, colunaEndereco, colunaLogin);
        tabelaOperadores.setItems(lista);
        
    }

    @FXML
    public void voltarAoMenu() throws IOException {
        App.setRoot("menu");
    }
    
}
