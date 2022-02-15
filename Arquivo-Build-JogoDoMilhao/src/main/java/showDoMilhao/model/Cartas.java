/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import showDoMilhao.view.TelaCartas;
import showDoMilhao.view.TelaJogo;

/**
 *
 * @author igori
 */
public class Cartas implements Ajuda, ActionListener{
    
    private TelaJogo telaJogo;
    List<JButton> alternativas;
    
    private int quantidade;
    
    public Cartas(TelaJogo telaJogo, int quantidade) {
        this.telaJogo = telaJogo;
        
        this.quantidade = quantidade;
    }
    
    public void retornaEscolhaCartas(String carta) {                
        removeRespCerta();
        
        Collections.shuffle(alternativas);

        if("AS".equals(carta)){
            alternativas.get(0).setVisible(false);
            
            JOptionPane.showMessageDialog(telaJogo, "Uma opcao foi eliminada");
        } else if("DOIS".equals(carta)) {
            alternativas.get(0).setVisible(false);
            alternativas.get(1).setVisible(false);
            
            JOptionPane.showMessageDialog(telaJogo, "Duas opcao foi eliminada");
        }  else if("TRES".equals(carta)) {
            alternativas.get(0).setVisible(false);
            alternativas.get(1).setVisible(false);
            alternativas.get(2).setVisible(false);
            
            JOptionPane.showMessageDialog(telaJogo, "Tres opcao foi eliminada");
        }  else if("REI".equals(carta)) {
            JOptionPane.showMessageDialog(telaJogo, "Nenhuma opcao foi eliminada");
        }
        
        this.telaJogo.repaint();
    }
    
    public void removeRespCerta() {
        int resp = this.telaJogo.getRespCorreta();
        
        if(resp == 1) {
            alternativas =  Arrays.asList(
                this.telaJogo.getBtnSegundaAlt(),
                this.telaJogo.getBtnTerceiraAlt(),
                this.telaJogo.getBtnQuartaAlt()
            );
        } else if(resp == 2) {
            alternativas =  Arrays.asList(
                this.telaJogo.getBtnPrimeiraAlt(),
                this.telaJogo.getBtnTerceiraAlt(),
                this.telaJogo.getBtnQuartaAlt()
            );
        } else if(resp == 3) {
            alternativas =  Arrays.asList(
                this.telaJogo.getBtnPrimeiraAlt(),
                this.telaJogo.getBtnSegundaAlt(),
                this.telaJogo.getBtnQuartaAlt()
            );
        } else {
            alternativas =  Arrays.asList(
                this.telaJogo.getBtnPrimeiraAlt(),
                this.telaJogo.getBtnSegundaAlt(),
                this.telaJogo.getBtnTerceiraAlt()
            );
        }
    }

    @Override
    public void chamaAjuda() {
        if(desabilitaPerguntaMilhao() || this.quantidade <= 0) {
            JOptionPane.showMessageDialog(telaJogo, "Nao e possivel usar essa ajuda");
        } else {
            TelaCartas telaCartas = new TelaCartas(this);
            telaCartas.desenha();
            
            this.quantidade -= 1;
            this.telaJogo.setQuantiadeCartas(quantidade);
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
