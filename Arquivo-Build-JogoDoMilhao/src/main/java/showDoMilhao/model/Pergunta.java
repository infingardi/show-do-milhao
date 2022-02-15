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
public class Pergunta {
    private String pergunta;
    private String primeiraAlt;
    private String segundaAlt;
    private String terceiraAlt;
    private String quartaAlt;
    private int respCerta;
    private String dificuldade;
    
    public Pergunta(String pergunta, String primeiraAlt, String segundaAlt, String terceiraAlt, String quartaAlt, int respCerta, String dificuldade) {
        this.pergunta = pergunta;
        this.primeiraAlt = primeiraAlt;
        this.segundaAlt = segundaAlt;
        this.terceiraAlt = terceiraAlt;
        this.quartaAlt = quartaAlt;
        this.respCerta = respCerta;
        this.dificuldade = dificuldade;
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

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public int getRespCerta() {
        return respCerta;
    }

    public void setRespCerta(int respCerta) {
        this.respCerta = respCerta;
    }
}
