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
import showDoMilhao.model.Pergunta;

/**
 *
 * @author igori
 */
public class JSONPerguntas {
    public static String toJSONPegunta(Pergunta pergunta) {
        Gson gson = new Gson();
        return gson.toJson(pergunta);
    }
    
    public static String toJSONPerguntas(List<Pergunta> pergunta){   
        Gson gson = new Gson();
        return gson.toJson(pergunta);
    }
    
    public static Pergunta toPergunta(String json){
        Gson gson = new Gson();
        Pergunta pergunta = gson.fromJson(json, Pergunta.class);
        
        return pergunta;
    }
    
    public static List<Pergunta> toPerguntas(String json){
        Gson gson = new Gson();
        Type perguntasTipo = new TypeToken<ArrayList<Pergunta>>(){}.getType();
        List<Pergunta> perguntas = gson.fromJson(json, perguntasTipo);
        
        return perguntas;
    }
}
