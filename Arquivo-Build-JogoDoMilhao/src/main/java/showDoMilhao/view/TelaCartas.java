/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import showDoMilhao.controller.EscolheCarta;
import showDoMilhao.model.Cartas;

/**
 *
 * @author igori
 */
public class TelaCartas extends JFrame{
    
    private JPanel painelPrincipal;
    private String cartaEscolhida;
    
    private Cartas classeCartas;
    
    public TelaCartas(Cartas classeCartas) {
        this.classeCartas = classeCartas;
    }
    
    public void desenha() {
        this.setSize(520, 500);
        
        painelPrincipal = new JPanel();
        this.painelPrincipal.setLayout(new BorderLayout());
        
        desenhaCartas();
        
        this.add(this.painelPrincipal, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setVisible(true);
    }
    
    public void desenhaCartas() {
        JPanel painelCartas = new JPanel();
        
        JLabel jlTitulo = new JLabel("Escolha a sua carta");
        
        painelCartas.add(jlTitulo);
        
        List<String> cartas = Arrays.asList("AS", "DOIS", "TRES", "REI");
        
        // Embaralha as cartas
        Collections.shuffle(cartas);
        
        JButton btnCarta;
        for (int i = 0; i < cartas.size(); i++) {
            btnCarta = new JButton("Show do Milhao");
            btnCarta.addActionListener(new EscolheCarta(this, cartas.get(i), this.classeCartas));
            
            painelCartas.add(btnCarta);
        }
        
        this.painelPrincipal.add(painelCartas);
    }

    public String getCartaEscolhida() {
        return cartaEscolhida;
    }

    public void setCartaEscolhida(String cartaEscolhida) {
        this.cartaEscolhida = cartaEscolhida;
    }
}
