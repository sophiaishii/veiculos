package app;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;


public class MenuController {
    
    // Mototorista
    @FXML
    private void cadastrarMotorista() throws IOException {
        App.setRoot("motorista/novoMotorista");
    }
    
    @FXML
    private void excluirMotorista() throws IOException{
        App.setRoot("motorista/excluirMotorista");
    }
    
    @FXML
    private void listarMotorista() throws IOException{
        App.setRoot("motorista/listarMotorista");
    }

    @FXML
    private void editarMotorista() throws IOException{
        App.setRoot("motorista/editarMotorista");
    }

    //Veiculo
    @FXML
    private void cadastrarVeiculo() throws IOException {
        App.setRoot("veiculo/novoVeiculo");
    }

    @FXML
    private void listarVeiculo() throws IOException {
        App.setRoot("veiculo/listarVeiculo");
    }

    @FXML
    private void excluirVeiculo() throws IOException {
        App.setRoot("veiculo/excluirVeiculo");
    }

    @FXML
    private void editarVeiculo() throws IOException {
        App.setRoot("veiculo/editarVeiculo");
    }



    // Operador

    @FXML
    private void cadastrarOperador() throws IOException {
        App.setRoot("operador/novoOperador");
    }

    @FXML
    private void excluirOperador() throws IOException {
        App.setRoot("operador/excluirOperador");
    }

    @FXML
    private void listarOperador() throws IOException {
        App.setRoot("operador/listarOperador");
    }

    @FXML
    private void editarOperador() throws IOException {
        App.setRoot("operador/editarOperador");
    }
    
    // Uso de Veiculo
    @FXML
    private void retirarVeiculo() throws IOException{
        App.setRoot("retiradaVeiculo");
    }

    @FXML
    private void devolverVeiculo() throws IOException{
        App.setRoot("devolverVeiculo");
    }

    @FXML
    private void buscaRegistro() throws IOException{
        App.setRoot("buscaRegistro");
    }
    
    @FXML
    private void sair(){
        Platform.exit();
        System.exit(0);
    }
    
}
