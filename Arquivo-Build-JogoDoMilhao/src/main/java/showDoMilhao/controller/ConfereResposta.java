/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import showDoMilhao.view.TelaJogo;

/**
 *
 * @author igori
 */
public class ConfereResposta implements ActionListener  {
    private TelaJogo telaJogo;
    private int resp;
    
    public ConfereResposta(TelaJogo telaJogo, int resp) {
        this.telaJogo = telaJogo;
        this.resp = resp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.resp == telaJogo.getRespCorreta()) {
            this.telaJogo.chamaProximaRodada();
        } else {
            this.telaJogo.respostaErrada();
        }
    }
}
