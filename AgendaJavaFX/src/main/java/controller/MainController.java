/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Contato;
import model.dao.ContatoDaoJdbc;
import model.dao.DaoFactory;
import start.App;

/**
 * FXML Controller class
 *
 * @author evelinsteiger
 */
public class MainController implements Initializable {

    @FXML
    private Button btnCreate;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtFilter;
    @FXML
    private Button btnFilter;
    @FXML
    private Button btnClean;
    @FXML
    private TableView<Contato> tableContato;
    @FXML
    private TableColumn<Contato, String> colNome;
    @FXML
    private TableColumn<Contato, String> colTelefone;
    @FXML
    private TableColumn<Contato, String> colEmail;
    
    
    private List<Contato> list;
    private ObservableList<Contato> observableList;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fetchContato("");
        } catch (Exception ex) {
            System.out.println("Erro:" + ex.getMessage());
        }
    }    

    @FXML
    private void onCreate(ActionEvent event) throws IOException {
        Contato currentContato = tableContato.selectionModelProperty().getValue().getSelectedItem();
        
        try {
            if (currentContato != null) {
                FormController.setCurrentContato(null);
            }
            
            App.setRoot("form");
        } catch (IOException e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

    @FXML
    private void onEdit(ActionEvent event) throws IOException {
        Contato currentContato = tableContato.selectionModelProperty().getValue().getSelectedItem();
        
        if (currentContato != null) {
            FormController.setCurrentContato(currentContato);
            App.setRoot("form");
        } else {
            Alert info =  new Alert(Alert.AlertType.INFORMATION);
            
            info.setTitle("Atenção");
            info.setContentText("Selecione um contato abaixo antes de editar.");
            info.showAndWait();
        }
    }

    @FXML
    private void onSearch(ActionEvent event) throws Exception {
        fetchContato(txtFilter.getText());
    }

    @FXML
    private void onClean(ActionEvent event) throws Exception {
        txtFilter.clear();
        fetchContato("");
    }
    
    @FXML
    private void onDelete(ActionEvent event) throws Exception {
        
        Contato currentContato = tableContato.selectionModelProperty().getValue().getSelectedItem();
        
        if (currentContato != null) {
            ContatoDaoJdbc daoDelete = DaoFactory.newContatoDao();    
            Alert alert =  new Alert(Alert.AlertType.CONFIRMATION);
            
            alert.setTitle("Aviso");
            alert.setContentText("Deseja excluir " + currentContato.getNome() + "?");
            
            Optional<ButtonType> response = alert.showAndWait();
            
            if (response.get() == ButtonType.OK) {
                try {
                    daoDelete.delete(currentContato);
                } catch (IOException e) {
                    String message = "Ocorreu um erro" + e.getMessage();
                    
                    Alert error =  new Alert(Alert.AlertType.INFORMATION);
            
                    error.setTitle("Aviso");
                    error.setContentText(message);
                    error.showAndWait();
                }   
            }
            
            fetchContato("");
        }
        
    }
    
    private void fetchContato(String param) throws Exception {
        colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        
        ContatoDaoJdbc dao = DaoFactory.newContatoDao();
        list = dao.index(param);
        
        observableList = FXCollections.observableArrayList(list);
        
        tableContato.setItems(observableList);
    }

}
