/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import showDoMilhao.util.Arquivo;
import showDoMilhao.util.JSONJogadores;

/**
 *
 * @author igori
 */
public class Jogador extends Pessoa{
    
    //private ;
    
    private int dinheiroGanho;
    private int rodadaAlcancada;

    public Jogador(String usuario, String senha, String classe, int dinheiro, int rodada) {
        this.setUsuario(usuario);
        this.setSenha(senha);
        this.setClasse(classe);
        
        this.dinheiroGanho = dinheiro;
        this.rodadaAlcancada = rodada;
    }
    
    public void salvarDados(int dinheiroGanho, int rodadaAlcancada) {
        String CAMINHO = "jogadores.json";
        this.dinheiroGanho = dinheiroGanho;
        this.rodadaAlcancada = rodadaAlcancada;
        
        try {
            String arquivoJogadores = Arquivo.lerArquivo(CAMINHO);
            
            if(arquivoJogadores.isEmpty()) {
                Arquivo.escreverArquivo(CAMINHO, "[]");
            }
        
            List<Jogador> jogadores;

            jogadores = JSONJogadores.toJogadores(arquivoJogadores);
            
            for (int i = 0; i < jogadores.size(); i++) {
                boolean verifyUsuario = jogadores.get(i).getUsuario().equals(this.getUsuario());
                if(verifyUsuario) {
                    if(jogadores.get(i).getDinheiroGanho() < this.dinheiroGanho) {
                        // salva as informações se ele conseguiu mais dinheiro do que anteriormente
                        jogadores.get(i).setDinheiroGanho(this.dinheiroGanho);
                        jogadores.get(i).setRodadaAlcancada(this.rodadaAlcancada);
                    }
                }
            }

            String toJSON = JSONJogadores.toJSONJogadores(jogadores);
            Arquivo.escreverArquivo("jogadores.json", toJSON);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel conectar com o banco de dados");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel conectar com o banco de dados");
        }
    }

    public int getDinheiroGanho() {
        return dinheiroGanho;
    }

    public void setDinheiroGanho(int dinheiroGanho) {
        this.dinheiroGanho = dinheiroGanho;
    }

    public int getRodadaAlcancada() {
        return rodadaAlcancada;
    }

    public void setRodadaAlcancada(int rodadaAlcancada) {
        this.rodadaAlcancada = rodadaAlcancada;
    }
    
    
}
