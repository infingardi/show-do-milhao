/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import showDoMilhao.view.TelaCadastro;
import showDoMilhao.view.TelaLogin;

/**
 *
 * @author igori
 */
public class ChamaTelaCadastro implements ActionListener{
    private TelaLogin telaLogin;
    
    public ChamaTelaCadastro(TelaLogin telaLogin ) {
        this.telaLogin = telaLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        telaLogin.dispose();
        TelaCadastro telaCadastro = new TelaCadastro();
        telaCadastro.desenha();
    }
    
}
