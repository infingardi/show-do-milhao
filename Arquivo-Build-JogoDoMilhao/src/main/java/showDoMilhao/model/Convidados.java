/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import showDoMilhao.view.TelaConvidados;
import showDoMilhao.view.TelaJogo;

/**
 *
 * @author igori
 */
public class Convidados implements Ajuda, ActionListener{
    
    private TelaJogo telaJogo;
    private int quantidade;
    
    public Convidados(TelaJogo telaJogo, int quantidade) {
        this.telaJogo = telaJogo;
        this.quantidade = quantidade;
    }

    @Override
    public void chamaAjuda() {
        if(desabilitaPerguntaMilhao() || quantidade <= 0) {
            JOptionPane.showMessageDialog(telaJogo, "Nao e possivel usar essa ajuda");
        } else {
            TelaConvidados telaConvidados = new TelaConvidados(telaJogo);
            telaConvidados.desenha();
            
            this.quantidade -= 1;
            this.telaJogo.setQuantiadeConvidados(quantidade);
            
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
