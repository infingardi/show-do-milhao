/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.view;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import showDoMilhao.controller.ChamaTelaVerificaPerguntas;
import showDoMilhao.model.Gerente;

/**
 *
 * @author igori
 */
public class TelaGerente extends JFrame{
    private Gerente gerente;
    
    private JPanel painelPrincipal;
    
    public TelaGerente(Gerente gerente) {
        this.gerente = gerente;
        
        desenha();
    }
    
    public void desenha() {
        this.setSize(520, 500);
        
        painelPrincipal = new JPanel();
        this.painelPrincipal.setLayout(new BorderLayout());
        
        JLabel titulo = new JLabel("Escolha o que deseja acessar: ");
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 110, 20, 0));
        titulo.setFont(new Font("Arial", Font.PLAIN, 22));
        this.painelPrincipal.add(titulo, BorderLayout.NORTH);
        
        desenhaOpcoes();
        
        this.add(this.painelPrincipal, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setVisible(true);
    }
    
    public void desenhaOpcoes() {
        JPanel painelOpcoes = new JPanel();
        
        JButton btnTabela = new JButton("Perguntas");
        btnTabela.addActionListener(new ChamaTelaVerificaPerguntas(this));
        
        painelOpcoes.add(btnTabela);
        this.painelPrincipal.add(painelOpcoes, BorderLayout.CENTER);
    }
}
