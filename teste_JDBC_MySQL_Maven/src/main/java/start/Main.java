/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author evelinsteiger
 */
public class Main {

    public static void main(String[] args) {

        try {
            int option = -1;
            List<Contato> list;
            String response = "";

            while (option != 0) {
                option = Integer.parseInt(JOptionPane.showInputDialog("""
                                                                      Bem-vindo a agenda! 
                                                                      Informe o c\u00f3digo da a\u00e7\u00e3o: 
                                                                       1.Inclus\u00e3o 
                                                                       2.Edi\u00e7\u00e3o 
                                                                       3.Exclus\u00e3o 
                                                                       4.Listagem 
                                                                       0.Sair"""));

                switch (option) {
                    case 1 -> {
                        Contato createContato = new Contato();
                        createContato.setNome(JOptionPane.showInputDialog("Informe o nome:"));
                        createContato.setEmail(JOptionPane.showInputDialog("Informe o e-mail:"));
                        createContato.setTelefone(JOptionPane.showInputDialog("Informe o telefone:"));
                        create(createContato);
                    }
                    case 2 -> {
                        list = select();
                        response = "";

                        for (Contato item : list) {
                            response += item.toString() + "\n";
                        }

                        Contato oldContato = new Contato();
                        oldContato.setId(Integer.parseInt(JOptionPane.showInputDialog(response + "\n" + "Informe o ID do contato a editar:")));

                        Contato newContato = show(oldContato);

                        newContato.setNome(JOptionPane.showInputDialog("Confirme o nome:", newContato.getNome()));
                        newContato.setEmail(JOptionPane.showInputDialog("Confirme o e-mail:", newContato.getEmail()));
                        newContato.setTelefone(JOptionPane.showInputDialog("Confirme o telefone:", newContato.getTelefone()));

                        update(newContato, newContato.getId());
                    }
                    case 3 -> {
                        list = select();
                        response = "";

                        for (Contato item : list) {
                            response += item.toString() + "\n";
                        }

                        int id = Integer.parseInt(JOptionPane.showInputDialog(response + "\n" + "Informe o id do contato a excluir: "));
                        Contato contato = new Contato();
                        contato.setId(id);
                        delete(contato.getId());
                    }
                    case 4 -> {
                        list = select();
                        response = "";

                        for (Contato item : list) {
                            response += item.toString() + "\n";
                        }
                        JOptionPane.showMessageDialog(null, response);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public static void create(Contato contato) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/java_agenda", "root", "");

        PreparedStatement insert = conn.prepareStatement("INSERT INTO Contato(nome, email, telefone) VALUES (?,?,?);");

        insert.setString(1, contato.getNome());
        insert.setString(2, contato.getEmail());
        insert.setString(3, contato.getTelefone());

        insert.execute();

        conn.close();
    }

    public static Contato show(Contato contato) throws SQLException {
        Contato item = new Contato();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/java_agenda", "root", "");

        PreparedStatement select = conn.prepareStatement("SELECT * FROM Contato WHERE id = ?");

        select.setInt(1, contato.getId());

        ResultSet response = select.executeQuery();

        while (response.next()) {
            item.setId(response.getInt("id"));
            item.setNome(response.getString("nome"));
            item.setEmail(response.getString("email"));
            item.setTelefone(response.getString("telefone"));
        }

        conn.close();

        return item;
    }

    public static void update(Contato contato, int id) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/java_agenda", "root", "");

        contato.setId(id);

        PreparedStatement update = conn.prepareStatement("UPDATE Contato SET nome = ?, email = ?, telefone = ? WHERE id = ?");

        update.setString(1, contato.getNome());
        update.setString(2, contato.getEmail());
        update.setString(3, contato.getTelefone());
        update.setInt(4, contato.getId());

        update.execute();

        conn.close();

    }

    public static List<Contato> select() throws SQLException {
        List<Contato> list = new ArrayList();

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/java_agenda", "root", "");
        PreparedStatement select = conn.prepareStatement("SELECT * FROM Contato");
        ResultSet response = select.executeQuery();

        while (response.next()) {
            Contato item = new Contato();

            item.setId(response.getInt("id"));
            item.setNome(response.getString("nome"));
            item.setEmail(response.getString("email"));
            item.setTelefone(response.getString("telefone"));

            list.add(item);
        }

        conn.close();

        return list;
    }

    public static void delete(int id) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/java_agenda", "root", "");

        Contato contato = new Contato();
        contato.setId(id);
        PreparedStatement delete = conn.prepareStatement("DELETE FROM Contato WHERE id = ?");

        delete.setInt(1, contato.getId());
        delete.execute();

        conn.close();
    }
}
