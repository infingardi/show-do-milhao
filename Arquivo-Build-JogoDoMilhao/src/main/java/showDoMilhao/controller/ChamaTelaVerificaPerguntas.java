/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import showDoMilhao.view.TelaGerente;
import showDoMilhao.view.TelaVerificaPerguntas;

/**
 *
 * @author igori
 */
public class ChamaTelaVerificaPerguntas implements ActionListener{
    private TelaGerente telaGerente;
    
    public ChamaTelaVerificaPerguntas(TelaGerente telaGerente) {
        this.telaGerente = telaGerente;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.telaGerente.dispose();
        
        TelaVerificaPerguntas telaVerifica = new TelaVerificaPerguntas();
    }
}
