/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnos;

import java.util.regex.Pattern;

/**
 *
 * @author Patricio
 */
public class RegexMatcher {
    static String regex;
    
    
 
    public static boolean testcedula(String CI){
        return Pattern.matches("[0-9]{10}", CI);  //valida que cedula ingresada sea de 10 digitos
    }

    
}
