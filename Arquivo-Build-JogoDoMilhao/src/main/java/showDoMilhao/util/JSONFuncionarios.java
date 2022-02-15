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

/**
 *
 * @author igori
 */
public class JSONFuncionarios {
    public static String toJSONFuncionario(Funcionario funcionario) {
        Gson gson = new Gson();
        return gson.toJson(funcionario);
    }
    
    public static String toJSONFuncionarios(List<Funcionario> funcionarios){   
        Gson gson = new Gson();
        return gson.toJson(funcionarios);
    }
    
    public static Funcionario toFuncionario(String json){
        Gson gson = new Gson();
        Funcionario funcionario = gson.fromJson(json, Funcionario.class);
        
        return funcionario;
    }
    
    public static List<Funcionario> toFuncionarios(String json){
        Gson gson = new Gson();
        Type funcionariosTipo = new TypeToken<ArrayList<Funcionario>>(){}.getType();
        List<Funcionario> funcionarios = gson.fromJson(json, funcionariosTipo);
        
        return funcionarios;
    }
}
