/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.model;

/**
 *
 * @author igori
 */
public class Funcionario extends Pessoa{
    
    public Funcionario(String usuario, String senha, String classe) {
        this.setUsuario(usuario);
        this.setSenha(senha);
        this.setClasse(classe);
    }
}
