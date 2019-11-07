/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *
 * @author JoseM
 */
public class Validaciones {
    
    public static boolean Double(String valoravalidar,double valorminimo,double valormaximo) {
        try {
	boolean resultado = valoravalidar.matches("[-+]?\\b[0-9]*\\.?[0-9]+\\b");
            if(resultado){
                if(Float.parseFloat(valoravalidar)<valorminimo||Float.parseFloat(valoravalidar)>valormaximo)
                    return false;
            }
            return resultado;            
            }
        catch (PatternSyntaxException ex) {
             return false;
            }     
    }
}
