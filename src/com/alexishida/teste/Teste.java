/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexishida.teste;

import com.alexishida.utils.LastFM;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;




/**
 *
 * @author 94954658268
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JSONException, MalformedURLException, UnsupportedEncodingException {
  
        LastFM last = new LastFM();
        
        
         String json_texto = "";
            
         //json_texto = last.buscaPorMusicaJson("Taylor Swift", "I Knew You Were Trouble");
           
         //String retorno;              
         //retorno = last.obtemCapa(json_texto);
         byte[] retorno = new byte[4096];  
         
         retorno = last.downloadUrl("http://userserve-ak.last.fm/serve/126/87559401.png");
         
            
       String file_string;
    try {
        file_string = new String(retorno, "UTF-8");
    } catch (UnsupportedEncodingException e) {
        // this should never happen because "UTF-8" is hard-coded.
        throw new IllegalStateException(e);
    }
        System.out.println(file_string);
        
    }
}
