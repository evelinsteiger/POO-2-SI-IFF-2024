/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author evelinsteiger
 */
public class Produto {
    private int id;
    private String nome;
    private int qtde;
    private Float preco;

    public Produto() {
    }

    public Produto(int id, String nome, int qtde, Float preco) {
        this.id = id;
        this.nome = nome;
        this.qtde = qtde;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto: " + id + " | " + nome + " | " + qtde + " | " + preco;
    }
}
