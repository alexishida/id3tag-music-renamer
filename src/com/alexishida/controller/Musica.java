/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexishida.controller;

import com.alexishida.utils.Id3tag;
import com.alexishida.utils.LastFM;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.httpclient.URIException;
import org.blinkenlights.jid3.ID3Exception;
import org.json.JSONException;

/**
 *
 * @author 94954658268
 */
public class Musica {
    
    
    public String nomeMusica = "";
    public String nomeArtista = "";
    
    public boolean renomear(String localArquivo,String nomeArquivo) {
        
        
        Id3tag id3tag = new Id3tag();
        try {
            id3tag.renomear(localArquivo, getArtista(nomeArquivo), getTitulo(nomeArquivo));
            return true;
        } catch (ID3Exception ex) {
           return false;
        }
        
        
    }
    
    private String getTitulo(String nomeArquivo) {
        
   
            String retorno = "";
            String musica = removeExtensao(nomeArquivo);
            musica = musica.trim();
            String[] musica_string = musica.split("-");


            if (musica_string.length == 2)
            {

                retorno = musica_string[1].trim();

            }

            else if (musica_string.length == 3)
            {

                retorno = musica_string[2].trim();

            }

            else
            {

                retorno = "";

            }

            this.nomeMusica = retorno;

            return retorno;

    
    }
    
    
    
    
     private String getArtista(String nomeArquivo)
        {

            String retorno = "";

            String musica = removeExtensao(nomeArquivo);
            musica = musica.trim();
            String[] musica_string = musica.split("-");

            if (musica_string.length == 2)
            {

                retorno = musica_string[0].trim();

            }

            else if (musica_string.length == 3)
            {

                retorno = musica_string[0].trim() + "-" + musica_string[1].trim();

            }

            else 
            {

                retorno = musica;

            }
            
           

            this.nomeArtista = retorno;
            return retorno;

        }
    
     
     private String removeExtensao(String nome) {
         
        String retorno;
        
        retorno = nome.trim();
        retorno = retorno.replaceAll(".mp3", "");
        retorno = retorno.replaceAll(".Mp3", ""); 
        retorno = retorno.replaceAll(".MP3", ""); 
        
        return retorno;
         
     }
     
     
     
     public byte[] obtemCapa(String artista,String titulo) {
      
         String json = "";
         String link_capa = "";
         LastFM last_fm = new LastFM();
         
        try {
            json = last_fm.buscaPorMusicaJson(artista, titulo);
             
            
            try {
                 link_capa = last_fm.obtemLinkCapa(json);
                 System.out.println(link_capa);
                return last_fm.downloadUrl(link_capa);
                 
             } catch (JSONException ex) {
                 //Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
                 return null;
             }
            
            
            
            
        } catch (URIException ex) {
           // Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (MalformedURLException ex) {
           // Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
          //   Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
         
     }
}
