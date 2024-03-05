package app;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import modelo.Operador;
import util.Dao;
import util.ExclusaoException;

public class ExcluirOperadorController {

    @FXML
    private ComboBox<Operador> comboOperadores;

    private ObservableList<Operador> listaOb;
    private List<Operador> lista;
    private Dao<Operador> dao;

    @FXML
    private void initialize() {
        dao = new Dao(Operador.class);
        lista = dao.listarTodos();
        listaOb = FXCollections.observableArrayList(lista);
        comboOperadores.setItems(listaOb);
    }

    @FXML
    private void excluirOperador() {
        Operador temp = comboOperadores.getSelectionModel().getSelectedItem();
        try {
            dao.excluir(temp);            
        } catch (ExclusaoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Operador não pode ser excluído");
            alert.show();
        }
        // atualiza a lista 
        lista = dao.listarTodos();
        listaOb = FXCollections.observableArrayList(lista);
        comboOperadores.setItems(listaOb);
    }

    @FXML
    private void voltarAoMenu() throws IOException {
        App.setRoot("menu");
    }
    
}
