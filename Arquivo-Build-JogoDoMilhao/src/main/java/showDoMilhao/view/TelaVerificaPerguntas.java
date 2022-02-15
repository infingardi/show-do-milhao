/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import showDoMilhao.controller.AdicionaPerguntaNoJogo;
import showDoMilhao.model.Pergunta;
import showDoMilhao.util.Arquivo;
import showDoMilhao.util.JSONPerguntas;

/**
 *
 * @author igori
 */
public class TelaVerificaPerguntas extends JFrame{
    
    private JPanel painelPrincipal;
    private final String CAMINHO_PERGUNTAS = "verificacaoPerguntas.json";
    
    private String dificuldade = "Facil";
    
    public TelaVerificaPerguntas() {
        desenha();
    }
    
    public void desenha() {
        this.setSize(800, 500);
        
        painelPrincipal = new JPanel();
        this.painelPrincipal.setLayout(new BorderLayout());
        
        desenhaPerguntas();
        
        this.add(this.painelPrincipal, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setVisible(true);
    }
    
    public void desenhaPerguntas() {
        
        try {
            JPanel painelTodasPerguntas = new JPanel();
            painelTodasPerguntas.setLayout(new GridLayout(0, 1));
            
            List<Pergunta> perguntas;
            String perguntasArquivo = Arquivo.lerArquivo(CAMINHO_PERGUNTAS);

            perguntas = JSONPerguntas.toPerguntas(perguntasArquivo);
            
            for (int i = 0; i < perguntas.size(); i++) {
                JPanel painelPerguntas = new JPanel();
                painelPerguntas.setLayout(new GridLayout(0, 2));
                painelPerguntas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                
                JLabel pergunta = new JLabel("Pergunta: " + perguntas.get(i).getPergunta());
                JLabel primeiraAlt = new JLabel("Primeira Alt.: " + perguntas.get(i).getPrimeiraAlt());
                JLabel segundaAlt = new JLabel("Segunda Alt.: " + perguntas.get(i).getSegundaAlt());
                JLabel terceiraAlt = new JLabel("terceira Alt.: " + perguntas.get(i).getTerceiraAlt());
                JLabel quartaAlt = new JLabel("Quarta Alt.: " + perguntas.get(i).getQuartaAlt());
                JLabel respCerta = new JLabel("Resp. Certa: " + perguntas.get(i).getRespCerta());
                
                String[] dificildadeStrings = { "Facil", "Medio", "Dficil" };

                JComboBox dificuldadeList = new JComboBox(dificildadeStrings);
                // Realiza a ação de mudança de escolha do ComboBox
                ActionListener handleComboBox = (ActionEvent e) -> {
                    setDificuldade(dificuldadeList.getSelectedItem().toString());
                };
                dificuldadeList.setSelectedIndex(0);
                dificuldadeList.addActionListener(handleComboBox);
                
                
                JButton btnAdicionar = new JButton("Adicionar");
                btnAdicionar.addActionListener(new AdicionaPerguntaNoJogo(
                        this,
                        perguntas.get(i).getPergunta(),
                        perguntas.get(i).getPrimeiraAlt(),
                        perguntas.get(i).getSegundaAlt(),
                        perguntas.get(i).getTerceiraAlt(),
                        perguntas.get(i).getQuartaAlt(),
                        perguntas.get(i).getRespCerta()
                ));
                
                painelPerguntas.add(pergunta);
                painelPerguntas.add(primeiraAlt);
                painelPerguntas.add(segundaAlt);
                painelPerguntas.add(terceiraAlt);
                painelPerguntas.add(quartaAlt);
                painelPerguntas.add(respCerta);
                painelPerguntas.add(dificuldadeList);
                painelPerguntas.add(btnAdicionar);
                
                painelTodasPerguntas.add(painelPerguntas);
            }

            painelPrincipal.add(new JScrollPane(painelTodasPerguntas));
            
        } catch(FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Nao foi possivel acessar as perguntas");
        }
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }
}
