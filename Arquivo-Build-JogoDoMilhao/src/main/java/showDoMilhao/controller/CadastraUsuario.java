package showDoMilhao.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import showDoMilhao.model.Funcionario;
import showDoMilhao.model.Gerente;
import showDoMilhao.model.Jogador;
import showDoMilhao.model.Pessoa;
import showDoMilhao.util.Arquivo;
import showDoMilhao.util.JSONFuncionarios;
import showDoMilhao.util.JSONGerente;
import showDoMilhao.util.JSONJogadores;
import showDoMilhao.view.TelaCadastro;
import showDoMilhao.view.TelaLogin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author igori
 */
public class CadastraUsuario implements ActionListener{
    private TelaCadastro telaCadastro;
    final String CAMINHO_JOGADORES = "jogadores.json";
    final String CAMINHO_FUNCIONARIOS = "funcionarios.json";
    final String CAMINHO_GERENTES = "gerentes.json";

    public CadastraUsuario(TelaCadastro telaCadastro) {
        this.telaCadastro = telaCadastro;
        
        try {
            // Verifica se os arquivos de usados como banco de dados estão vazios
            // Caso algum esteja, será colocado o simbolo de Array ('[]') para iniciar o arquivo
            
            String verificaJogadoresVazio = Arquivo.lerArquivo(CAMINHO_JOGADORES);
            if(verificaJogadoresVazio.isEmpty()) {
                Arquivo.escreverArquivo(CAMINHO_JOGADORES, "[]");
            }
            
            String verificaFuncionariosVazio = Arquivo.lerArquivo(CAMINHO_FUNCIONARIOS);
            if(verificaFuncionariosVazio.isEmpty()) {
                Arquivo.escreverArquivo(CAMINHO_FUNCIONARIOS, "[]");
            }
           
            String verificaGerentesVazio = Arquivo.lerArquivo(CAMINHO_GERENTES);
            if(verificaGerentesVazio.isEmpty()) {
                Arquivo.escreverArquivo(CAMINHO_GERENTES, "[]");
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(telaCadastro, "Não foi possivel conectar com o banco de dados");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(telaCadastro, "Não foi possivel conectar com o banco de dados");
        }
    }
    
    public void cadastraJogador() {
        try {
            if(confereUsuarioExiste()) {
                Jogador jogador = new Jogador(
                        this.telaCadastro.getJtUsuario().getText(),
                        this.telaCadastro.getJtSenha().getText(),
                        this.telaCadastro.getClasse(), 0, 0
                );

                List<Jogador> jogadores;
                String pessoasArquivo = Arquivo.lerArquivo(CAMINHO_JOGADORES);

                jogadores = JSONJogadores.toJogadores(pessoasArquivo);
                jogadores.add(jogador);

                String toJSON = JSONJogadores.toJSONJogadores(jogadores);
                System.out.println("ToJson: " + toJSON);
                Arquivo.escreverArquivo(CAMINHO_JOGADORES, toJSON);
            } else {
                JOptionPane.showMessageDialog(telaCadastro, "Usuario ja existente");
            }
        } catch(FileNotFoundException ex) {
            JOptionPane.showMessageDialog(telaCadastro, "Não foi possivel contatar o banco de dados");
        } catch(IOException io) {
            JOptionPane.showMessageDialog(telaCadastro, "Não foi possivel cadastrar novo jogador");
        }
    }
    
    public void cadastraFuncionario() {
        try {
            if(confereUsuarioExiste()) {
                Funcionario funcionario = new Funcionario(
                            this.telaCadastro.getJtUsuario().getText(),
                            this.telaCadastro.getJtSenha().getText(),
                            this.telaCadastro.getClasse()
                        );

                List<Funcionario> funcionarios;
                String funcionariosArquivo = Arquivo.lerArquivo(CAMINHO_FUNCIONARIOS);

                funcionarios = JSONFuncionarios.toFuncionarios(funcionariosArquivo);
                funcionarios.add(funcionario);

                String toJSON = JSONFuncionarios.toJSONFuncionarios(funcionarios);
                Arquivo.escreverArquivo(CAMINHO_FUNCIONARIOS, toJSON);
            } else {
                JOptionPane.showMessageDialog(telaCadastro, "Usuario ja existente");
            }
        } catch(FileNotFoundException ex) {
            JOptionPane.showMessageDialog(telaCadastro, "Não foi possivel contatar o banco de dados");
        } catch(IOException io) {
            JOptionPane.showMessageDialog(telaCadastro, "Não foi possivel cadastrar novo funcionario");
        }
    }
    
    public void cadastraGerente() {
        try {
            if(confereUsuarioExiste()) {
                Gerente gerente = new Gerente(
                            this.telaCadastro.getJtUsuario().getText(),
                            this.telaCadastro.getJtSenha().getText(),
                            this.telaCadastro.getClasse()
                        );

                List<Gerente> gerentes;
                String gerentesArquivo = Arquivo.lerArquivo(CAMINHO_GERENTES);

                gerentes = JSONGerente.toGerentes(gerentesArquivo);
                gerentes.add(gerente);

                String toJSON = JSONGerente.toJSONGerentes(gerentes);
                Arquivo.escreverArquivo(CAMINHO_GERENTES, toJSON);
            } else {
                JOptionPane.showMessageDialog(telaCadastro, "Usuario ja existente");
            }
        } catch(FileNotFoundException ex) {
            JOptionPane.showMessageDialog(telaCadastro, "Não foi possivel contatar o banco de dados");
        } catch(IOException io) {
            JOptionPane.showMessageDialog(telaCadastro, "Não foi possivel cadastrar novo gerente");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            
            Pessoa pessoa;
            if("Jogador".equals(this.telaCadastro.getClasse())) {
                cadastraJogador();
            } else if(this.telaCadastro.getClasse().matches("Funcionario")) {
                cadastraFuncionario();
            } else if(this.telaCadastro.getClasse().matches("Gerente")) {
                cadastraGerente();
            }
            else {
                throw new Error("Tipo de usuario invalido");
            }
         } catch (Exception ex) {
             JOptionPane.showMessageDialog(this.telaCadastro, ex);
         } catch (Error error) {
             JOptionPane.showMessageDialog(this.telaCadastro, error);
         }
        //renderiza a tela de login
        this.telaCadastro.dispose();
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.desenha();
    }
    
    public boolean confereUsuarioExiste() {
        boolean jaExiste = false;
        try { 
            
            // Confere se este usuario já existe em jogadores.json
            String jogadoresArquivo = Arquivo.lerArquivo(CAMINHO_JOGADORES);
            List<Jogador> jogadores = JSONJogadores.toJogadores(jogadoresArquivo);

            for (int i = 0; i < jogadores.size(); i++) {
                if(jogadores.get(i).getUsuario().equals(this.telaCadastro.getJtUsuario().getText())) {
                    jaExiste = true;
                }
            }
            
            // Confere se este usuario já existe em funcionarios.son
            String funcionariosArquivo = Arquivo.lerArquivo(CAMINHO_FUNCIONARIOS);
            List<Funcionario> funcionarios = JSONFuncionarios.toFuncionarios(funcionariosArquivo);

            for (int i = 0; i < funcionarios.size(); i++) {
                if(funcionarios.get(i).getUsuario().equals(this.telaCadastro.getJtUsuario().getText())) {
                    jaExiste = true;
                }
            }
            
            // Confere se este usuario já existe em gerentes.json
            String gerentesArquivo = Arquivo.lerArquivo(CAMINHO_GERENTES);
            List<Gerente> gerentes = JSONGerente.toGerentes(gerentesArquivo);

            for (int i = 0; i < gerentes.size(); i++) {
                if(gerentes.get(i).getUsuario().equals(this.telaCadastro.getJtUsuario().getText())) {
                    jaExiste = true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(telaCadastro, "Erro ao acessar usuarios existentes");
        }
        
        return !jaExiste;
    }
}
