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
import showDoMilhao.model.Pessoa;

/**
 *
 * @author igori
 */
public class JSONDados {
    
    public static String toJSONDados(Pessoa pessoa) {
        Gson gson = new Gson();
        return gson.toJson(pessoa);
    }
    
    public static String toJSONDados(List<Pessoa> pessoas){   
        Gson gson = new Gson();
        return gson.toJson(pessoas);
    }
    
    public static Pessoa toPessoa(String json){
        Gson gson = new Gson();
        Pessoa pessoa = gson.fromJson(json, Pessoa.class);
        
        return pessoa;
    }
    
    public static List<Pessoa> toPessoas(String json){
        Gson gson = new Gson();
        Type pessoasTipo = new TypeToken<ArrayList<Pessoa>>(){}.getType();
        List<Pessoa> pessoas = gson.fromJson(json, pessoasTipo);
        
        return pessoas;
    }
}