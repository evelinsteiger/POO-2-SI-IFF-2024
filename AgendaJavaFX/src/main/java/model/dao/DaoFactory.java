/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author evelinsteiger
 */
public class DaoFactory {
    
    public static ContatoDaoJdbc newContatoDao() throws Exception {
        return new ContatoDaoJdbc();
    }
    
}
