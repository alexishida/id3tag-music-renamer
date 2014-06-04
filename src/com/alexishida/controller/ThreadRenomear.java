/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexishida.controller;

import com.alexishida.forms.Principal;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author 94954658268
 */
public class ThreadRenomear extends Thread {

    public void run() {

        int total = Principal.listaMusicas.size();
        Principal.jProgressBarRenomear.setMaximum(total);
        int contador = 0;
        String localMusica = Principal.pastaMusicas;
        int cont_erro = 0;
        Musica musica = new Musica();
        
        for (String musicaArquivo : Principal.listaMusicas) {
            
            
           contador++;
           Principal.jProgressBarRenomear.setValue(contador);
           Principal.jLabelTotalArquivos.setText("Total de Musicas: "+contador+"/"+total);
           Principal.jLabelArquivoRenomeado.setText(musicaArquivo);
           
           
           
          if(!musica.renomear(localMusica+musicaArquivo, musicaArquivo)){
              cont_erro++;
              Principal.jLabelArtista.setText("Não foi possível obter");
              Principal.jLabelTitulo.setText("Não foi possível obter");
          }
          else {
              
              byte[] capa = musica.obtemCapa(musica.nomeArtista, musica.nomeMusica);
              
            
              if(capa == null) {
                   Principal.jLabelAlbumArt.setIcon(new ImageIcon(getClass().getResource("/com/alexishida/imagens/sem_imagem.jpg"))); // NOI18N
              }
              else {
                  Principal.jLabelAlbumArt.setIcon(new ImageIcon(capa));
              }

              
              Principal.jLabelArtista.setText(musica.nomeArtista);
              Principal.jLabelTitulo.setText(musica.nomeMusica);
          }
          
            
                
          
          
          
        }

          JOptionPane.showMessageDialog(null,"Total de musicas renomeadas: " +(contador-cont_erro)+"\n Total de musicas com erros: "+cont_erro + "\n Musicas renomeadas com sucesso!");
          
    }
}


