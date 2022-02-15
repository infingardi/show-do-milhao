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
import showDoMilhao.model.Funcionario;
import showDoMilhao.model.Gerente;

/**
 *
 * @author igori
 */
public class JSONGerente {
    public static String toJSONGerente(Gerente gerente) {
        Gson gson = new Gson();
        return gson.toJson(gerente);
    }
    
    public static String toJSONGerentes(List<Gerente> gerentes){   
        Gson gson = new Gson();
        return gson.toJson(gerentes);
    }
    
    public static Gerente toGerente(String json){
        Gson gson = new Gson();
        Gerente gerente = gson.fromJson(json, Gerente.class);
        
        return gerente;
    }
    
    public static List<Gerente> toGerentes(String json){
        Gson gson = new Gson();
        Type gerentesTipo = new TypeToken<ArrayList<Gerente>>(){}.getType();
        List<Gerente> gerentes = gson.fromJson(json, gerentesTipo);
        
        return gerentes;
    }
}
