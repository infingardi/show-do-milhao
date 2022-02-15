/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import showDoMilhao.model.Cartas;
import showDoMilhao.view.TelaCartas;

/**
 *
 * @author igori
 */
public class EscolheCarta implements ActionListener{
    private TelaCartas telaCartas;
    private String carta;
    private Cartas classeCartas;
    
    public EscolheCarta(TelaCartas telaCartas, String carta, Cartas classeCartas) {
        this.telaCartas = telaCartas;
        this.carta = carta;
        this.classeCartas = classeCartas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.telaCartas.setCartaEscolhida(this.carta);
        
        this.classeCartas.retornaEscolhaCartas(carta);
        
        telaCartas.dispose();
        System.out.println(this.telaCartas.getCartaEscolhida());
    }    
}
