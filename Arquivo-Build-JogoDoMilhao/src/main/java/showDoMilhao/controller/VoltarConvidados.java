/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import showDoMilhao.view.TelaConvidados;

/**
 *
 * @author igori
 */
public class VoltarConvidados implements ActionListener{
    TelaConvidados telaConvidados;
    
    public VoltarConvidados(TelaConvidados telaConvidados) {
        this.telaConvidados = telaConvidados;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        telaConvidados.dispose();
    }
    
}
