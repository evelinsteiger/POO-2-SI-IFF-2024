/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.dao;

import java.util.List;

/**
 *
 * @author evelinsteiger
 * @param <T>
 */
public interface InterfaceDao<T> {
    
    public abstract void create(T input) throws Exception;
   
    public abstract void update(T input, int id) throws Exception;
    
    public abstract void delete(T input) throws Exception;
    
    public abstract T show(int id) throws Exception;
    
    public abstract List<T> index(String param) throws Exception;
}
