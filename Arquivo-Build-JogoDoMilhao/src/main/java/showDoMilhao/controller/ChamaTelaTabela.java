/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import showDoMilhao.view.TelaLogin;
import showDoMilhao.view.TelaTabela;

/**
 *
 * @author igori
 */
public class ChamaTelaTabela implements ActionListener{
    private TelaLogin telaLogin;
    
    public ChamaTelaTabela(TelaLogin telaLogin) {
        this.telaLogin = telaLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        telaLogin.dispose();
        TelaTabela telaTabela = new TelaTabela();
        telaTabela.desenha();
    }
    
}
