/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;
import javax.swing.JOptionPane;
import showDoMilhao.model.Funcionario;
import showDoMilhao.model.Gerente;
import showDoMilhao.model.Jogador;
import showDoMilhao.util.Arquivo;
import showDoMilhao.util.JSONFuncionarios;
import showDoMilhao.util.JSONGerente;
import showDoMilhao.util.JSONJogadores;
import showDoMilhao.view.TelaFuncionario;
import showDoMilhao.view.TelaGerente;
import showDoMilhao.view.TelaJogo;
import showDoMilhao.view.TelaLogin;

/**
 *
 * @author igori
 */
public class LoginUsuario implements ActionListener{
    private TelaLogin telaLogin;
    
    final String CAMINHO_JOGADORES = "jogadores.json";
    final String CAMINHO_FUNCIONARIOS = "funcionarios.json";
    final String CAMINHO_GERENTES = "gerentes.json";
    
    private boolean logado;
    
    public LoginUsuario(TelaLogin telaLogin) {
        this.telaLogin = telaLogin;
    }
    
    public void loginJogador() {
        String usuarioLogin = telaLogin.getJtUsuario().getText();
        String senhaLogin = telaLogin.getJtSenha().getText();
        logado = false;
        
        try{
            String usuarios = Arquivo.lerArquivo(CAMINHO_JOGADORES);
            List<Jogador> jogdoresList = JSONJogadores.toJogadores(usuarios);

            for (int i = 0; i < jogdoresList.size(); i++) {
                String usuario = jogdoresList.get(i).getUsuario();
                String senha = jogdoresList.get(i).getSenha();

                if(usuario.matches(usuarioLogin) && senha.matches(senhaLogin)) {
                    //manda para a tela certa
                    String classe = jogdoresList.get(i).getClasse();

                    //caso seja jogador, o jogo começa
                    if(classe.matches("Jogador")) {
                        telaLogin.dispose();
                        Jogador jogador = new Jogador(usuario, senha, classe, 0, 0);
                        TelaJogo telaJogo = new TelaJogo(jogador);
                    }
                    logado = true;
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(telaLogin, "Não foi possivel acessar o banco de dados de Jogadores");
        }
    }
    
    public void loginFuncionario() {
        String usuarioLogin = telaLogin.getJtUsuario().getText();
        String senhaLogin = telaLogin.getJtSenha().getText();
        logado = false;
        
        try{
            String funcionarios = Arquivo.lerArquivo(CAMINHO_FUNCIONARIOS);
            List<Funcionario> funcionariosList = JSONFuncionarios.toFuncionarios(funcionarios);

            for (int i = 0; i < funcionariosList.size(); i++) {
                String usuario = funcionariosList.get(i).getUsuario();
                String senha = funcionariosList.get(i).getSenha();

                if(usuario.matches(usuarioLogin) && senha.matches(senhaLogin)) {
                    //manda para a tela certa
                    String classe = funcionariosList.get(i).getClasse();

                    //caso seja funcionario, sera redirecionado para tela funcionario
                    if(classe.matches("Funcionario")) {
                        telaLogin.dispose();
                        Funcionario funcionario = new Funcionario(usuario, senha, classe);
                        TelaFuncionario telaFuncionario = new TelaFuncionario(funcionario);
                    }
                    
                    logado = true;
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(telaLogin, "Não foi possivel acessar o banco de dados de Jogadores");
        }
    }
    
    public void loginGerente() {
        String usuarioLogin = telaLogin.getJtUsuario().getText();
        String senhaLogin = telaLogin.getJtSenha().getText();
        logado = false;
        
        try{
            String gerentes = Arquivo.lerArquivo(CAMINHO_GERENTES);
            List<Gerente> gerentesList = JSONGerente.toGerentes(gerentes);

            for (int i = 0; i < gerentesList.size(); i++) {
                String usuario = gerentesList.get(i).getUsuario();
                String senha = gerentesList.get(i).getSenha();

                if(usuario.matches(usuarioLogin) && senha.matches(senhaLogin)) {
                    //manda para a tela certa
                    String classe = gerentesList.get(i).getClasse();

                    //caso seja funcionario, sera redirecionado para tela funcionario
                    if(classe.matches("Gerente")) {
                        telaLogin.dispose();
                        Gerente gerente = new Gerente(usuario, senha, classe);
                        TelaGerente telaGerente = new TelaGerente(gerente);
                    }
                    
                    logado = true;
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(telaLogin, "Não foi possivel acessar o banco de dados de Jogadores");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       logado = false;
       
        try {
            if(!logado) { // Tenta logar como jogador
                loginJogador();
            }
            
            if(!logado) { // Tenta logar como funcionario
                loginFuncionario();
            }
            
            if(!logado) { // Tenta logar como gerente
                loginGerente();
            }
            
            if(!logado) { // Caso não ache nenhum usuário correspondente
                JOptionPane.showMessageDialog(telaLogin, "Senha ou usuario incorretos");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(telaLogin, error);
        }
    }
}
