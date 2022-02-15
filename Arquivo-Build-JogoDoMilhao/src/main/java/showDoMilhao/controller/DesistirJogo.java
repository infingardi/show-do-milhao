/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import showDoMilhao.view.TelaJogo;
import showDoMilhao.view.TelaLogin;

/**
 *
 * @author igori
 */
public class DesistirJogo implements ActionListener{
    
    private TelaJogo telaJogo;

    public DesistirJogo(TelaJogo telaJogo) {
        this.telaJogo = telaJogo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.telaJogo.getJogador().salvarDados(
                this.telaJogo.getDinheiro().getDinheiroAtual(),
                this.telaJogo.getRodada()
        );
        
        this.telaJogo.dispose();
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.desenha();
    }
}
