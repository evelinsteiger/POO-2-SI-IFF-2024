/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Contato;

/**
 *
 * @author evelinsteiger
 */
public class ContatoDaoJdbc implements InterfaceDao<Contato>{
    
    private final Connection conn;
    
    public ContatoDaoJdbc() throws Exception {
        this.conn = ConnFactory.getConnection();
    }

    @Override
    public void create(Contato input) throws Exception {
        try {
            PreparedStatement insert = conn.prepareStatement("INSERT INTO Contato(nome, email, telefone) VALUES (?,?,?);");

            insert.setString(1, input.getNome());
            insert.setString(2, input.getEmail());
            insert.setString(3, input.getTelefone());

            insert.execute();
            
        } finally {
            if(conn != null){
                conn.close();
            }
        }
    }

    @Override
    public void update(Contato input, int id) throws Exception {
        try {
            input.setId(id);

            PreparedStatement update = conn.prepareStatement("UPDATE Contato SET nome = ?, email = ?, telefone = ? WHERE id = ?");

            update.setString(1, input.getNome());
            update.setString(2, input.getEmail());
            update.setString(3, input.getTelefone());
            update.setInt(4, input.getId());

            update.execute();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
    }

    @Override
    public void delete(Contato input) throws Exception {
        try {
            Contato contato = new Contato();
            contato.setId(input.getId());
            
            PreparedStatement delete = conn.prepareStatement("DELETE FROM Contato WHERE id = ?");

            delete.setInt(1, contato.getId());
            delete.execute();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
    }

    @Override
    public Contato show(int id) throws Exception {
        Contato item = new Contato();

        try {
            PreparedStatement select = conn.prepareStatement("SELECT * FROM Contato WHERE id = ?");

            select.setInt(1, id);

            ResultSet response = select.executeQuery();

            while (response.next()) {
                item.setId(response.getInt("id"));
                item.setNome(response.getString("nome"));
                item.setEmail(response.getString("email"));
                item.setTelefone(response.getString("telefone"));
            }
        } finally {
            if(conn != null){
                conn.close();
            }
        }

        return item;
    }

    @Override
    public List<Contato> index() throws Exception {
        List<Contato> list = new ArrayList();

        try {
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
        } finally {
            if(conn != null){
                conn.close();
            }
        }
        
        return list;
    }
    
}
