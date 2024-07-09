/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package view;

import java.util.List;
import javax.swing.JOptionPane;
import model.Contato;
import model.dao.ContatoDaoJdbc;
import model.dao.DaoFactory;

/**
 *
 * @author evelinsteiger
 */
public class Main {

    public static void main(String[] args){

        try {
            int option = -1;
            List<Contato> list;
            String response = "";

            while (option != 0) {
                option = Integer.parseInt(JOptionPane.showInputDialog("""
                                                                      Bem-vindo a agenda! 
                                                                      Informe o código da ação: 
                                                                       1.Inclusão 
                                                                       2.Edição 
                                                                       3.Exclusão 
                                                                       4.Listagem 
                                                                       0.Sair"""));

                switch (option) {
                    case 1 -> {
                        ContatoDaoJdbc dao = DaoFactory.newContatoDao();
                        Contato createContato = new Contato();
                        createContato.setNome(JOptionPane.showInputDialog("Informe o nome:"));
                        createContato.setEmail(JOptionPane.showInputDialog("Informe o e-mail:"));
                        createContato.setTelefone(JOptionPane.showInputDialog("Informe o telefone:"));
                        dao.create(createContato);
                    }
                    case 2 -> {
                        ContatoDaoJdbc dao = DaoFactory.newContatoDao();
                        list = dao.index();
                        response = "";

                        for (Contato item : list) {
                            response += item.toString() + "\n";
                        }

                        Contato oldContato = new Contato();
                        oldContato.setId(Integer.parseInt(JOptionPane.showInputDialog(response + "\n" + "Informe o ID do contato a editar:")));

                        Contato newContato = dao.show(oldContato.getId());

                        newContato.setNome(JOptionPane.showInputDialog("Confirme o nome:", newContato.getNome()));
                        newContato.setEmail(JOptionPane.showInputDialog("Confirme o e-mail:", newContato.getEmail()));
                        newContato.setTelefone(JOptionPane.showInputDialog("Confirme o telefone:", newContato.getTelefone()));

                        dao.update(newContato, newContato.getId());
                    }
                    case 3 -> {
                        ContatoDaoJdbc dao = DaoFactory.newContatoDao();
                        list = dao.index();
                        response = "";

                        for (Contato item : list) {
                            response += item.toString() + "\n";
                        }

                        int id = Integer.parseInt(JOptionPane.showInputDialog(response + "\n" + "Informe o id do contato a excluir: "));
                        Contato contato = new Contato();
                        contato.setId(id);
                        dao.delete(contato);
                    }
                    case 4 -> {
                        ContatoDaoJdbc dao = DaoFactory.newContatoDao();
                        list = dao.index();
                        response = "";

                        for (Contato item : list) {
                            response += item.toString() + "\n";
                        }
                        
                        JOptionPane.showMessageDialog(null, response);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }
}
