/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import showDoMilhao.view.TelaJogo;

/**
 *
 * @author igori
 */
public class Pular implements Ajuda, ActionListener{
    private TelaJogo telaJogo;
    private int quantidade;
    
    public Pular(TelaJogo telaJogo, int quantidade) {
        this.telaJogo = telaJogo;
        this.quantidade = quantidade;
    }

    @Override
    public void chamaAjuda() {
        if(desabilitaPerguntaMilhao() || this.telaJogo.getQuantiadePular() <= 0) {
            JOptionPane.showMessageDialog(telaJogo, "Nao e usar essa ajuda");
        }
        else {
            this.telaJogo.setRodada(this.telaJogo.getRodada() - 1);
            this.telaJogo.chamaProximaRodada();
            
            this.quantidade = this.quantidade - 1;
            this.telaJogo.setQuantiadePular(this.quantidade - 1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chamaAjuda();
    }

    @Override
    public boolean desabilitaPerguntaMilhao() {
        return telaJogo.getRodada() == 16;
    }
}
