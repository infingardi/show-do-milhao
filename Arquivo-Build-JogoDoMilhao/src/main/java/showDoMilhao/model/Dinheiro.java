/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.model;

/**
 *
 * @author igori
 */
public class Dinheiro {
    private int rodada = 0;
    private int dinheiroAtual = 0;
    private int dinheiroErrar = 0;
    private int dinheiroAcertar = 0;
    
    private int dinheiroPerguntaFinal;
    
    private int primeiroInicial;
    private int segundoInicial;
    private int terceiroInicial;
    private int primeiroBonus;
    private int segundoBonus;
    private int terceiroBonus;
    
    public Dinheiro(
            int primeiroInicial, int segundoInicial, int terceiroInicial,
            int primeiroBonus, int segundoBonus, int terceiroBonus,
            int dinheiroPerguntaFinal
    ) {
        
        this.primeiroInicial = primeiroInicial;
        this.segundoInicial = segundoInicial;
        this.terceiroInicial = terceiroInicial;
        
        this.primeiroBonus = primeiroBonus;
        this.segundoBonus = segundoBonus;
        this.terceiroBonus = terceiroBonus;
        
        this.dinheiroPerguntaFinal = dinheiroPerguntaFinal;
    }
    
    public void arrumaDinheiro(int rodada) {     
        if(rodada == 1) {
            this.setDinheiroAcertar(this.getPrimeiroBonus());
        }
        else if(rodada <= 5) {
            this.setDinheiroAtual(this.getDinheiroAcertar());
            this.setDinheiroErrar(this.getDinheiroAtual() / 2);
            this.setDinheiroAcertar(this.getDinheiroAtual() + this.getPrimeiroBonus());
        } else if(rodada == 6) {
            this.setDinheiroAtual(this.getDinheiroAcertar());
            this.setDinheiroErrar(this.getDinheiroAtual() / 2);
            this.setDinheiroAcertar(this.getSegundoInicial());
        }
        else if(rodada <= 10) {
            this.setDinheiroAtual(this.getDinheiroAcertar());
            this.setDinheiroErrar(this.getDinheiroAtual() / 2);
            this.setDinheiroAcertar(this.getDinheiroAtual() + this.getSegundoBonus());
        }
        else if (rodada == 11 ) {
            this.setDinheiroAtual(this.getDinheiroAcertar());
            this.setDinheiroErrar(this.getDinheiroAtual() / 2);
            this.setDinheiroAcertar(this.getTerceiroInicial());
        }
        else if(rodada <= 15){ 
            this.setDinheiroAtual(this.getDinheiroAcertar());
            this.setDinheiroErrar(this.getDinheiroAtual() / 2);
            this.setDinheiroAcertar(this.getDinheiroAtual() + this.getTerceiroBonus());
        } else if(rodada == 16) {
            this.setDinheiroAtual(this.getDinheiroAcertar());
            this.setDinheiroErrar(0);
            this.setDinheiroAcertar(this.getDinheiroPerguntaFinal());
        }
    }

    public int getRodada() {
        return rodada;
    }

    public void setRodada(int rodada) {
        this.rodada = rodada;
    }

    public int getDinheiroAtual() {
        return dinheiroAtual;
    }

    public void setDinheiroAtual(int dinheiroAtual) {
        this.dinheiroAtual = dinheiroAtual;
    }

    public int getPrimeiroInicial() {
        return primeiroInicial;
    }

    public void setPrimeiroInicial(int primeiroInicial) {
        this.primeiroInicial = primeiroInicial;
    }

    public int getSegundoInicial() {
        return segundoInicial;
    }

    public void setSegundoInicial(int segundoInicial) {
        this.segundoInicial = segundoInicial;
    }

    public int getTerceiroInicial() {
        return terceiroInicial;
    }

    public void setTerceiroInicial(int terceiroInicial) {
        this.terceiroInicial = terceiroInicial;
    }
    
    

    public int getDinheiroPerguntaFinal() {
        return dinheiroPerguntaFinal;
    }

    public void setDinheiroPerguntaFinal(int dinheiroPerguntaFinal) {
        this.dinheiroPerguntaFinal = dinheiroPerguntaFinal;
    }

    public int getPrimeiroBonus() {
        return primeiroBonus;
    }

    public void setPrimeiroBonus(int primeiroBonus) {
        this.primeiroBonus = primeiroBonus;
    }

    public int getSegundoBonus() {
        return segundoBonus;
    }

    public void setSegundoBonus(int segundoBonus) {
        this.segundoBonus = segundoBonus;
    }

    public int getTerceiroBonus() {
        return terceiroBonus;
    }

    public void setTerceiroBonus(int terceiroBonus) {
        this.terceiroBonus = terceiroBonus;
    }

    public int getDinheiroErrar() {
        return dinheiroErrar;
    }

    public void setDinheiroErrar(int dinheiroErrar) {
        this.dinheiroErrar = dinheiroErrar;
    }

    public int getDinheiroAcertar() {
        return dinheiroAcertar;
    }

    public void setDinheiroAcertar(int dinheiroAcertar) {
        this.dinheiroAcertar = dinheiroAcertar;
    }
}
