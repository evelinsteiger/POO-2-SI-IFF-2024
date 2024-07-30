/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Contato;
import model.dao.ContatoDaoJdbc;
import model.dao.DaoFactory;
import start.App;


/**
 * FXML Controller class
 *
 * @author evelinsteiger
 */
public class FormController implements Initializable {

    @FXML
    private Button btnGoBack;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEmail;
    
    private static Contato currentContato;

    public static void setCurrentContato(Contato currentContato) {
        FormController.currentContato = currentContato;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (currentContato != null) {
            txtNome.setText(currentContato.getNome());
            txtEmail.setText(currentContato.getEmail());
            txtTelefone.setText(currentContato.getTelefone());
        }
    }    
    
    @FXML
    private void onNavigate() {
        try {
            App.setRoot("main");    
        } catch (IOException e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

    
    @FXML
    private void onSubmit(ActionEvent event) throws Exception {
        try {
            ContatoDaoJdbc dao = DaoFactory.newContatoDao();
            Contato contato = new Contato();

            contato.setNome(txtNome.getText());
            contato.setEmail(txtEmail.getText());
            contato.setTelefone(txtTelefone.getText());
                
            if (currentContato == null) {
                dao.create(contato);
            } else {
                contato.setId(currentContato.getId());
                dao.update(contato, currentContato.getId());
            }
        } catch (IOException e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            currentContato = null;
            App.setRoot("main");
        }
    }

    
    @FXML
    private void onCancel(ActionEvent event) {
        try {
            App.setRoot("main");    
        } catch (IOException e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

}
