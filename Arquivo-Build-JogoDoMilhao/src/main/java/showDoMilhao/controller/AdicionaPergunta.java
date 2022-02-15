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
import showDoMilhao.view.TelaFuncionario;
import showDoMilhao.view.TelaLogin;

/**
 *
 * @author igori
 */
public class AdicionaPergunta implements ActionListener{
    
    private TelaFuncionario telaFuncionario;
    private final String CAMINHO_PERGUNTAS = "verificacaoPerguntas.json";
    
    public AdicionaPergunta(TelaFuncionario telaFuncionario) {
        this.telaFuncionario = telaFuncionario;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Pergunta pergunta = new Pergunta(
                    this.telaFuncionario.getJtPergunta().getText(),
                    this.telaFuncionario.getJtPrimeiraAlternativa().getText(),
                    this.telaFuncionario.getJtSegundaAlternativa().getText(),
                    this.telaFuncionario.getJtTerceiraAlternativa().getText(),
                    this.telaFuncionario.getJtQuartaAlternativa().getText(),
                    Integer.parseInt(this.telaFuncionario.getJtRespCorreta().getText()),
                    "indefinido"
            );
            
            List<Pergunta> perguntas;
            String perguntasArquivo = Arquivo.lerArquivo(CAMINHO_PERGUNTAS);

            perguntas = JSONPerguntas.toPerguntas(perguntasArquivo);
            perguntas.add(pergunta);

            String toJSON = JSONPerguntas.toJSONPerguntas(perguntas);
            Arquivo.escreverArquivo(CAMINHO_PERGUNTAS, toJSON);
            
            this.telaFuncionario.dispose();
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.desenha();
            
        } catch(FileNotFoundException ex) {
            JOptionPane.showMessageDialog(telaFuncionario, "Não foi possivel acessar o banco de dados de pergutas");
        } catch(IOException io) {
            JOptionPane.showMessageDialog(telaFuncionario, "Não foi possivel cadastrar a perguta no banco de dados");
        } catch(NumberFormatException error) {
            JOptionPane.showMessageDialog(telaFuncionario, "O campo de Alternativa correta tem que ser um numero");
        }
    }
}
