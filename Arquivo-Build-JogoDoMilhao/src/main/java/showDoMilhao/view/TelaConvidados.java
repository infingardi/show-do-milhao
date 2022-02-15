/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import showDoMilhao.controller.VoltarConvidados;

/**
 *
 * @author igori
 */
public class TelaConvidados extends JFrame{
    private JPanel painelPrincipal;
    private TelaJogo telaJogo;
    
    private int respCerta;
    private int rodada;
    
    private int respConvidado1;
    private int respConvidado2;
    private int respConvidado3;
    
    public TelaConvidados(TelaJogo telaJogo) {
        this.telaJogo = telaJogo;
        this.respCerta = this.telaJogo.getRespCorreta();
        this.rodada = this.telaJogo.getRodada();
        
        if(rodada <= 5){// Se a pergunta é de nivel Facil a resposta dos convidados sempre sera certa
            respConvidado1 = respCerta;
            respConvidado2 = respCerta;
            respConvidado3 = respCerta;
        } else if( rodada <= 10) {
            respostasNivelMedio();
            
        } else if(rodada <= 15) {
            respostasNivelDificil();
        }
    }
    
    private void respostasNivelMedio() {
        ArrayList<Integer> respostasErradas = new ArrayList<>();
        ArrayList<Integer> todasRespostas = new ArrayList<>();
        
        // Adiciona todas as possibilidades
        respostasErradas.add(1);
        respostasErradas.add(2);
        respostasErradas.add(3);
        respostasErradas.add(4);
        
        // Remove a resposta certa
        respostasErradas.remove(this.respCerta - 1);
        
        //Adiciona 80% de respostas certas
        for (int i = 0; i < 80; i++) {
            todasRespostas.add(this.respCerta);
        }
        
        //Adiciona 20% de respostas erradas
        Random random = new Random();
        int numero;
        for (int i = 0; i < 20; i++) {
            numero = random.nextInt(2);
            todasRespostas.add(respostasErradas.get(numero));
        }
        
        // Seta as respostas dos convidados
        numero = random.nextInt(99);
        respConvidado1 = todasRespostas.get(numero);
        
        numero = random.nextInt(99);
        respConvidado2 = todasRespostas.get(numero);
        
        numero = random.nextInt(99);
        respConvidado3 = todasRespostas.get(numero);
    }
    
    private void respostasNivelDificil() {
        ArrayList<Integer> respostasErradas = new ArrayList<>();
        ArrayList<Integer> todasRespostas = new ArrayList<>();
        
        // Adiciona todas as possibilidades
        respostasErradas.add(1);
        respostasErradas.add(2);
        respostasErradas.add(3);
        respostasErradas.add(4);
        
        // Remove a resposta certa
        respostasErradas.remove(this.respCerta - 1);
        
        //Adiciona 60% de respostas certas
        for (int i = 0; i < 60; i++) {
            todasRespostas.add(this.respCerta);
        }
        
        //Adiciona 40% de respostas erradas
        Random random = new Random();
        int numero;
        for (int i = 0; i < 40; i++) {
            numero = random.nextInt(2);
            todasRespostas.add(respostasErradas.get(numero));
        }
        
        // Seta as respostas dos convidados
        numero = random.nextInt(99);
        respConvidado1 = todasRespostas.get(numero);
        
        numero = random.nextInt(99);
        respConvidado2 = todasRespostas.get(numero);
        
        numero = random.nextInt(99);
        respConvidado3 = todasRespostas.get(numero);
    }
    
    public void desenha() {
        this.setSize(800, 600);
        
        painelPrincipal = new JPanel();
        this.painelPrincipal.setLayout(new BorderLayout());
        
        JLabel titulo = new JLabel("Resposta dos Convidados");
        titulo.setFont(new Font("Serif", Font.PLAIN, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(60, 260, 0, 0));
        
        JPanel painelBotaoVoltar = new JPanel();
        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(new VoltarConvidados(this));
        painelBotaoVoltar.add(voltar);
        
        this.painelPrincipal.add(titulo, BorderLayout.NORTH);
        desenhaConvidados();
        this.painelPrincipal.add(painelBotaoVoltar, BorderLayout.SOUTH);
        
        this.add(this.painelPrincipal, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setVisible(true);
    }
    
    public void desenhaConvidados() {
        JPanel painelConvidados = new JPanel();
        
        JPanel painelConvidado1 = new JPanel();
        JLabel convidado1 = new JLabel("Convidado 1");
        convidado1.setFont(new Font("Serif", Font.PLAIN, 20));
        JLabel jlRespConvidado1 = new JLabel("resp: " + respConvidado1);
        jlRespConvidado1.setFont(new Font("Serif", Font.PLAIN, 18));
        painelConvidado1.add(convidado1);
        painelConvidado1.add(jlRespConvidado1);
        painelConvidado1.setLayout(new GridLayout(0, 1));
        painelConvidado1.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 0));
        
        JPanel painelConvidado2 = new JPanel();
        JLabel convidado2 = new JLabel("Convidado 2");
        convidado2.setFont(new Font("Serif", Font.PLAIN, 20));
        JLabel jlRespConvidado2 = new JLabel("resp: " + respConvidado2);
        jlRespConvidado2.setFont(new Font("Serif", Font.PLAIN, 18));
        painelConvidado2.add(convidado2);
        painelConvidado2.add(jlRespConvidado2);
        painelConvidado2.setLayout(new GridLayout(0, 1));
        painelConvidado2.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 0));
        
        JPanel painelConvidado3 = new JPanel();
        JLabel convidado3 = new JLabel("Convidado 3");
        convidado3.setFont(new Font("Serif", Font.PLAIN, 20));
        JLabel jlRespConvidado3 = new JLabel("resp: " + respConvidado3);
        jlRespConvidado3.setFont(new Font("Serif", Font.PLAIN, 18));
        painelConvidado3.add(convidado3);
        painelConvidado3.add(jlRespConvidado3);
        painelConvidado3.setLayout(new GridLayout(0, 1));
        painelConvidado3.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 0));
        
        painelConvidados.add(painelConvidado1);
        painelConvidados.add(painelConvidado2);
        painelConvidados.add(painelConvidado3);
        
        painelConvidados.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));
        
        this.painelPrincipal.add(painelConvidados, BorderLayout.CENTER);
    }
}
