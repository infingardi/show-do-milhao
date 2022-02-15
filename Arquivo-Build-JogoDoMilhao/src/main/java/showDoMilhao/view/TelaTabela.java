/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import showDoMilhao.model.Jogador;
import showDoMilhao.util.Arquivo;
import showDoMilhao.util.JSONJogadores;

/**
 *
 * @author igori
 */
public class TelaTabela extends JFrame{
    private JPanel painelPrincipal;
    private String CAMINHO = "jogadores.json";
    
    private List<Jogador> jogadores;
    
    public TelaTabela() {
        try {
            String strJogadores = Arquivo.lerArquivo(CAMINHO);
            jogadores = JSONJogadores.toJogadores(strJogadores);
        } catch (FileNotFoundException e) {
        }
    }
    
    public void desenha() {
        this.setSize(520, 500);
        
        painelPrincipal = new JPanel();
        this.painelPrincipal.setLayout(new BorderLayout());
        
        JLabel titulo = new JLabel("Tabela");
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 210, 20, 0));
        titulo.setFont(new Font("Arial", Font.PLAIN, 22));
        this.painelPrincipal.add(titulo, BorderLayout.NORTH);
        
        desenhaTabela();
        
        this.add(this.painelPrincipal, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setVisible(true);
    }
    
    public void desenhaTabela() {
        JPanel painelTabela = new JPanel();
        painelTabela.setLayout(new GridLayout(0, 1));
        
        JLabel usuario;
        JLabel rodada;
        JLabel dinheiro;
        
        for (int i = 0; i < jogadores.size(); i++) {
            JPanel painelUsuario = new JPanel();
            usuario = new JLabel((i + 1) + ". Usuario: " + jogadores.get(i).getUsuario());
            rodada = new JLabel("Rodada: " + Integer.toString(jogadores.get(i).getRodadaAlcancada()));
            dinheiro = new JLabel("Dinheiro: " +  Integer.toString(jogadores.get(i).getDinheiroGanho()));
            
            painelUsuario.add(usuario);
            painelUsuario.add(rodada);
            painelUsuario.add(dinheiro);
            
            painelTabela.add(painelUsuario);
        }
    
        this.painelPrincipal.add(new JScrollPane(painelTabela), BorderLayout.CENTER);
    }
}
