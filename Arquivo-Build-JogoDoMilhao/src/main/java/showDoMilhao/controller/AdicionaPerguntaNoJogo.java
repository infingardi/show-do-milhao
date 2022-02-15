/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import showDoMilhao.model.Pergunta;
import showDoMilhao.util.Arquivo;
import showDoMilhao.util.JSONPerguntas;
import showDoMilhao.view.TelaLogin;
import showDoMilhao.view.TelaVerificaPerguntas;

/**
 *
 * @author igori
 */
public class AdicionaPerguntaNoJogo implements ActionListener{
    private TelaVerificaPerguntas telaVerificaPerguntas;
    
    private String pergunta;
    private String primeiraAlt;
    private String segundaAlt;
    private String terceiraAlt;
    private String quartaAlt;
    private int respCerta;
    private String dificuldade;
    
    private final String CAMINHO_PERGUNTAS = "verificacaoPerguntas.json";
    private final String CAMINHO_PERGUNTAS_FACIL = "perguntasFacil.json";
    private final String CAMINHO_PERGUNTAS_MEDIO = "perguntasMedio.json";
    private final String CAMINHO_PERGUNTAS_DIFICIL = "perguntasDificil.json";
    
    public AdicionaPerguntaNoJogo(
            TelaVerificaPerguntas telaVerificaPerguntas,
            String pergunta,
            String primeiraAlt,
            String segundaAlt,
            String terceiraAlt,
            String quartaAlt,
            int respCerta
    ) {
        this.telaVerificaPerguntas = telaVerificaPerguntas;
        
        this.pergunta = pergunta;
        this.primeiraAlt = primeiraAlt;
        this.segundaAlt = segundaAlt;
        this.terceiraAlt = terceiraAlt;
        this.quartaAlt = quartaAlt;
        this.respCerta = respCerta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.dificuldade = telaVerificaPerguntas.getDificuldade();
            
            Pergunta pergunta = new Pergunta(
                    this.pergunta,
                    this.primeiraAlt,
                    this.segundaAlt,
                    this.terceiraAlt,
                    this.quartaAlt,
                    this.respCerta,
                    this.dificuldade
            );
            
            if(this.dificuldade == "Facil") {
                List<Pergunta> perguntas;
                String perguntasArquivo = Arquivo.lerArquivo(CAMINHO_PERGUNTAS_FACIL);

                perguntas = JSONPerguntas.toPerguntas(perguntasArquivo);
                perguntas.add(pergunta);

                String toJSON = JSONPerguntas.toJSONPerguntas(perguntas);
                Arquivo.escreverArquivo(CAMINHO_PERGUNTAS_FACIL, toJSON);

                JOptionPane.showMessageDialog(telaVerificaPerguntas, "Pergunta adicionada com sucesso");
                
                tirarPerguntaDaLista();
            } else if(this.dificuldade == "Medio") {
                List<Pergunta> perguntas;
                String perguntasArquivo = Arquivo.lerArquivo(CAMINHO_PERGUNTAS_MEDIO);

                perguntas = JSONPerguntas.toPerguntas(perguntasArquivo);
                perguntas.add(pergunta);

                String toJSON = JSONPerguntas.toJSONPerguntas(perguntas);
                Arquivo.escreverArquivo(CAMINHO_PERGUNTAS_MEDIO, toJSON);

                JOptionPane.showMessageDialog(telaVerificaPerguntas, "Pergunta adicionada com sucesso");
                
                tirarPerguntaDaLista();
            } else if(this.dificuldade == "Dificil") {
                List<Pergunta> perguntas;
                String perguntasArquivo = Arquivo.lerArquivo(CAMINHO_PERGUNTAS_DIFICIL);

                perguntas = JSONPerguntas.toPerguntas(perguntasArquivo);
                perguntas.add(pergunta);

                String toJSON = JSONPerguntas.toJSONPerguntas(perguntas);
                Arquivo.escreverArquivo(CAMINHO_PERGUNTAS_DIFICIL, toJSON);

                JOptionPane.showMessageDialog(telaVerificaPerguntas, "Pergunta adicionada com sucesso");
                
                tirarPerguntaDaLista();
            } else {
                JOptionPane.showMessageDialog(telaVerificaPerguntas, "Dificulade invalida: " + this.dificuldade);
            }
            
        } catch(FileNotFoundException ex) {
            JOptionPane.showMessageDialog(telaVerificaPerguntas, "Não foi possivel acessar o banco de dados de pergutas");
        } catch(IOException io) {
            JOptionPane.showMessageDialog(telaVerificaPerguntas, "Não foi possivel cadastrar a perguta no banco de dados");
        } catch(NumberFormatException error) {
            JOptionPane.showMessageDialog(telaVerificaPerguntas, "O campo de Alternativa correta tem que ser um numero");
        }
    }
    
    public void tirarPerguntaDaLista() {
        Pergunta pergunta = new Pergunta(
                    this.pergunta,
                    this.primeiraAlt,
                    this.segundaAlt,
                    this.terceiraAlt,
                    this.quartaAlt,
                    this.respCerta,
                    this.dificuldade
        );
        try {
        
            List<Pergunta> perguntas;
            String perguntasArquivo = Arquivo.lerArquivo(CAMINHO_PERGUNTAS);

            perguntas = JSONPerguntas.toPerguntas(perguntasArquivo);
            
            for (int i = 0; i < perguntas.size(); i++) {
                if(perguntas.get(i).getPergunta().equals(this.pergunta)) {
                    perguntas.remove(i);
                    break;
                }
            }

            String toJSON = JSONPerguntas.toJSONPerguntas(perguntas);
            Arquivo.escreverArquivo(CAMINHO_PERGUNTAS, toJSON);
            
            this.telaVerificaPerguntas.dispose();
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.desenha();
        } catch(FileNotFoundException ex) {
            JOptionPane.showMessageDialog(telaVerificaPerguntas, "Não foi possivel acessar o banco de dados de pergutas");
        } catch(IOException io) {
            JOptionPane.showMessageDialog(telaVerificaPerguntas, "Não foi possivel cadastrar a perguta no banco de dados");
        } catch(NumberFormatException error) {
            JOptionPane.showMessageDialog(telaVerificaPerguntas, "O campo de Alternativa correta tem que ser um numero");
        }
    }
}

