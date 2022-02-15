/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import showDoMilhao.controller.ConfereResposta;
import showDoMilhao.controller.DesistirJogo;
import showDoMilhao.model.Cartas;
import showDoMilhao.model.Convidados;
import showDoMilhao.model.Dinheiro;
import showDoMilhao.model.Jogador;
import showDoMilhao.model.Pergunta;
import showDoMilhao.model.Pular;
import showDoMilhao.util.Arquivo;
import showDoMilhao.util.JSONPerguntas;

/**
 *
 * @author igori
 */
public class TelaJogo extends JFrame{
    private Jogador jogador;
    
    private JPanel painelPrincipal;
    private JPanel painelJogo;
    
    private List<Pergunta> perguntaFacilList;
    private List<Pergunta> perguntaMedioList;
    private List<Pergunta> perguntaDificilList;
    private int perguntaFacilSize;
    private int perguntaMedioSize;
    private int perguntaDificilSize;
    
    private int rodada = 0;
    private int perguntaIndex;
    
    private Dinheiro dinheiro;
    
    private JButton btnPrimeiraAlt;
    private JButton btnSegundaAlt;
    private JButton btnTerceiraAlt;
    private JButton btnQuartaAlt;
    
    private String pergunta;
    private String primeiraAlt;
    private String segundaAlt;
    private String terceiraAlt;
    private String quartaAlt;
    private int respCorreta;
    
    private int quantiadePular = 3;
    private int quantiadeConvidados = 1;
    private int quantiadeCartas = 1;
    
    private String cartaSelecionada;
    
    public TelaJogo(Jogador jogador) {
        super("Show do Milhão");
        this.jogador = jogador;

        try {
            String perguntasFacil = Arquivo.lerArquivo("perguntasFacil.json");
            this.perguntaFacilList = JSONPerguntas.toPerguntas(perguntasFacil);
            this.perguntaFacilSize = this.perguntaFacilList.size();
            
            String perguntasMedio = Arquivo.lerArquivo("perguntasMedio.json");
            this.perguntaMedioList = JSONPerguntas.toPerguntas(perguntasMedio);
            this.perguntaMedioSize = this.perguntaMedioList.size();
            
            String perguntasDificil = Arquivo.lerArquivo("perguntasDificil.json");
            this.perguntaDificilList = JSONPerguntas.toPerguntas(perguntasDificil);
            this.perguntaDificilSize = this.perguntaDificilList.size();
        } catch (FileNotFoundException ex) {
            System.out.println("Não foi possivel acessar o banco de dados de perguntas");
        }
        
        dinheiro = new Dinheiro(0,10000, 100000, 1000, 10000, 100000, 1000000);
        
        this.desenha();
    }
    
    public int escolhePergunta() {
        Random random = new Random();
        
        if(rodada <= 5) {
            this.perguntaIndex = random.nextInt(this.perguntaFacilSize - 1);
        } else if(rodada <= 10) {
            this.perguntaIndex = random.nextInt(this.perguntaMedioSize - 1);
        } else if(rodada <= 15){
            this.perguntaIndex = random.nextInt(this.perguntaDificilSize - 1);
        } else{
            JOptionPane.showMessageDialog(this, "Voce ganhou 1milhão de reais");
            
            jogador.salvarDados(this.dinheiro.getDinheiroAcertar(), this.rodada);
        
            this.dispose();
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.desenha();
        }
        
        this.rodada += 1;
        return this.perguntaIndex;
    }
    
    public void arrumaPergunta(int index) {
        Pergunta proxPergunta;

        if(rodada <= 5) { // pega uma pergunta facil, (as 5 primeiras rodadas)
            proxPergunta = this.perguntaFacilList.get(index);
        } else if(rodada <= 10) { // pega uma pergunta de nivel medio, (rodada de 6 a 10)
            proxPergunta = this.perguntaMedioList.get(index);
        } else { // pega uma pergunta de nivel dificil, (as rodadas finais, e a pergunta do milhão)
            proxPergunta = this.perguntaDificilList.get(index);
        }

        this.setPergunta(proxPergunta.getPergunta());
        this.setPrimeiraAlt(proxPergunta.getPrimeiraAlt());
        this.setSegundaAlt(proxPergunta.getSegundaAlt());
        this.setTerceiraAlt(proxPergunta.getTerceiraAlt());
        this.setQuartaAlt(proxPergunta.getQuartaAlt());
        this.setRespCorreta(proxPergunta.getRespCerta());
        
        dinheiro.arrumaDinheiro(rodada);
    }
    
    public void chamaProximaRodada() {
       this.painelPrincipal.removeAll();
       this.desenhaJogo();
       
       // reescreve a tela
       this.validate();
    }
    
    public void respostaErrada(){
        JOptionPane.showMessageDialog(this, "Resposta errada");
        
        // salva o score do usuario
        jogador.salvarDados(this.dinheiro.getDinheiroErrar(), this.rodada);
        
        this.dispose();
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.desenha();
    }
    
    public void desenha() {
        this.setSize(800, 600);
        
        painelPrincipal = new JPanel();
        this.painelPrincipal.setLayout(new BorderLayout());
        
        //responsavel por gerar uma pergunta e desenhar na tela
        desenhaJogo();
        
        this.add(this.painelPrincipal, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setVisible(true);
    }
    
    public void desenhaJogo() {
        painelJogo = new JPanel();
        
        int index = escolhePergunta();
        arrumaPergunta(index);
        
        if(this.rodada <= 5){
            this.perguntaFacilList.remove(index);
            this.perguntaFacilSize -= 1;
        }
        else if(this.rodada <= 10) {
            this.perguntaMedioList.remove(index);
            this.perguntaMedioSize -= 1;
        } else if(this.rodada <= 16) {
            this.perguntaDificilList.remove(index);
            this.perguntaDificilSize -= 1;
        }
        
        desenhaPergunta();
        desenhaAjuda();
       
        this.painelPrincipal.add(painelJogo, BorderLayout.CENTER);
    }
    
    public void desenhaPergunta() {
        JPanel painelPergunta = new JPanel();
        
        JPanel pergunta = new JPanel();
        JTextArea jtPergunta = new JTextArea(this.getPergunta(), 80, 36);
        jtPergunta.setLineWrap(true);
        jtPergunta.setEditable(false);
        jtPergunta.setFont(new Font("Times new Roman", Font.BOLD, 20));
        pergunta.add(jtPergunta, BorderLayout.NORTH);
        painelPergunta.add(pergunta, BorderLayout.NORTH);
        
        JPanel respostas = new JPanel();
        this.btnPrimeiraAlt = new JButton("1. " + this.getPrimeiraAlt());
        this.btnSegundaAlt = new JButton("2. " + this.getSegundaAlt());
        this.btnTerceiraAlt = new JButton("3. " + this.getTerceiraAlt());
        this.btnQuartaAlt = new JButton("4. " + this.getQuartaAlt());
        
        btnPrimeiraAlt.addActionListener(new ConfereResposta(this, 1));
        btnSegundaAlt.addActionListener(new ConfereResposta(this, 2));
        btnTerceiraAlt.addActionListener(new ConfereResposta(this, 3));
        btnQuartaAlt.addActionListener(new ConfereResposta(this, 4));
        
        respostas.add(btnPrimeiraAlt);
        respostas.add(btnSegundaAlt);
        respostas.add(btnTerceiraAlt);
        respostas.add(btnQuartaAlt);
        painelPergunta.add(respostas);
        
        respostas.setLayout(new GridLayout(0, 1));
        painelPergunta.setLayout(new GridLayout(0, 1));
        painelPergunta.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        
        this.painelPrincipal.add(painelPergunta, BorderLayout.WEST);
    }
    
    public void desenhaAjuda() {
        JPanel painelMenu = new JPanel();
        
        JButton parar = new JButton("Parar");
        parar.addActionListener(new DesistirJogo(this));
        
        JPanel painelAjuda = new JPanel();
        painelAjuda.setBorder(BorderFactory.createTitledBorder("Ajudas"));
        
        // Cria os componentes de ajuda
        JButton cartas = new JButton("Cartas");
        cartas.addActionListener(new Cartas(this, this.quantiadeCartas));
        
        JButton convidados = new JButton("Convidados");
        convidados.addActionListener(new Convidados(this, this.quantiadeConvidados));
        
        JButton pular = new JButton("Pular");
        pular.addActionListener(new Pular(this, this.quantiadePular));
        
        // Adiciona as ajudas na tela
        painelAjuda.add(cartas);
        painelAjuda.add(convidados);
        painelAjuda.add(pular);
        
        String errarDinheiro = Integer.toString(dinheiro.getDinheiroErrar());
        String pararDinheiro = Integer.toString(dinheiro.getDinheiroAtual());
        String acertarDinheiro = Integer.toString(dinheiro.getDinheiroAcertar());
        
        JPanel painelPremiacao = new JPanel();
        
        JPanel painelErrar = new JPanel();
        JLabel dinheiroErrar = new JLabel(errarDinheiro);
        painelErrar.setLayout(new GridLayout(0, 1));
        painelErrar.add(dinheiroErrar);
        JLabel textErrar = new JLabel("Errar");
        painelErrar.add(textErrar);
        painelPremiacao.add(painelErrar);
        
        JPanel painelParar = new JPanel();
        JLabel dinheiroParar = new JLabel(pararDinheiro);
        painelParar.setLayout(new GridLayout(0, 1));
        painelParar.add(dinheiroParar);
        JLabel textParar = new JLabel("Parar");
        painelParar.add(textParar);
        painelPremiacao.add(painelParar);
        
        JPanel painelAcertar = new JPanel();
        JLabel dinheiroAcertar = new JLabel(acertarDinheiro);
        painelAcertar.setLayout(new GridLayout(0, 1));
        painelAcertar.add(dinheiroAcertar);
        JLabel textAcertar = new JLabel("Acertar");
        painelAcertar.add(textAcertar);
        painelPremiacao.add(painelAcertar);
        
        painelPremiacao.setBorder(BorderFactory.createTitledBorder("Premiacao"));
                
        painelMenu.add(parar);
        painelAjuda.setLayout(new GridLayout(0, 1));
        painelMenu.add(painelAjuda);
        painelMenu.add(painelPremiacao);
        painelMenu.setLayout(new BoxLayout(painelMenu, BoxLayout.Y_AXIS));
        painelMenu.setBorder(BorderFactory.createEmptyBorder(50,0,0,50));
        
        this.painelPrincipal.add(painelMenu, BorderLayout.LINE_END);
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getPrimeiraAlt() {
        return primeiraAlt;
    }

    public void setPrimeiraAlt(String primeiraAlt) {
        this.primeiraAlt = primeiraAlt;
    }

    public String getSegundaAlt() {
        return segundaAlt;
    }

    public void setSegundaAlt(String segundaAlt) {
        this.segundaAlt = segundaAlt;
    }

    public String getTerceiraAlt() {
        return terceiraAlt;
    }

    public void setTerceiraAlt(String terceiraAlt) {
        this.terceiraAlt = terceiraAlt;
    }

    public String getQuartaAlt() {
        return quartaAlt;
    }

    public void setQuartaAlt(String quartaAlt) {
        this.quartaAlt = quartaAlt;
    }

    public int getRespCorreta() {
        return respCorreta;
    }

    public void setRespCorreta(int respCorreta) {
        this.respCorreta = respCorreta;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public int getRodada() {
        return rodada;
    }

    public void setRodada(int rodada) {
        this.rodada = rodada;
    }

    public Dinheiro getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(Dinheiro dinheiro) {
        this.dinheiro = dinheiro;
    }

    public String getCartaSelecionada() {
        return cartaSelecionada;
    }

    public void setCartaSelecionada(String cartaSelecionada) {
        this.cartaSelecionada = cartaSelecionada;
    }

    public JButton getBtnPrimeiraAlt() {
        return btnPrimeiraAlt;
    }

    public void setBtnPrimeiraAlt(JButton btnPrimeiraAlt) {
        this.btnPrimeiraAlt = btnPrimeiraAlt;
    }

    public JButton getBtnSegundaAlt() {
        return btnSegundaAlt;
    }

    public void setBtnSegundaAlt(JButton btnSegundaAlt) {
        this.btnSegundaAlt = btnSegundaAlt;
    }

    public JButton getBtnTerceiraAlt() {
        return btnTerceiraAlt;
    }

    public void setBtnTerceiraAlt(JButton btnTerceiraAlt) {
        this.btnTerceiraAlt = btnTerceiraAlt;
    }

    public JButton getBtnQuartaAlt() {
        return btnQuartaAlt;
    }

    public void setBtnQuartaAlt(JButton btnQuartaAlt) {
        this.btnQuartaAlt = btnQuartaAlt;
    }

    public int getQuantiadePular() {
        return quantiadePular;
    }

    public void setQuantiadePular(int quantiadePular) {
        this.quantiadePular = quantiadePular;
    }

    public int getQuantiadeConvidados() {
        return quantiadeConvidados;
    }

    public void setQuantiadeConvidados(int quantiadeConvidados) {
        this.quantiadeConvidados = quantiadeConvidados;
    }

    public int getQuantiadeCartas() {
        return quantiadeCartas;
    }

    public void setQuantiadeCartas(int quantiadeCartas) {
        this.quantiadeCartas = quantiadeCartas;
    }
    
    
}
