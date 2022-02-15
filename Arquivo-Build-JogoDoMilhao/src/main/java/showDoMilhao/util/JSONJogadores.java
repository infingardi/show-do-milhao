/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showDoMilhao.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import showDoMilhao.model.Jogador;

/**
 *
 * @author igori
 */
public class JSONJogadores {
    public static String toJSONJogador(Jogador jogador) {
        Gson gson = new Gson();
        return gson.toJson(jogador);
    }
    
    public static String toJSONJogadores(List<Jogador> jogadores){   
        Gson gson = new Gson();
        return gson.toJson(jogadores);
    }
    
    public static Jogador toJogador(String json){
        Gson gson = new Gson();
        Jogador jogador = gson.fromJson(json, Jogador.class);
        
        return jogador;
    }
    
    public static List<Jogador> toJogadores(String json){
        Gson gson = new Gson();
        Type jogadoresTipo = new TypeToken<ArrayList<Jogador>>(){}.getType();
        List<Jogador> jogadores = gson.fromJson(json, jogadoresTipo);
        
        return jogadores;
    }
}
