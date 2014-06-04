/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexishida.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Alex
 */
public class LastFM {

    private String api_key = "CHAVE";
    private String api_url = "http://ws.audioscrobbler.com/2.0/";

    public String buscaPorMusicaJson(String artista, String titulo) throws URIException, MalformedURLException, IOException {

        String retorno = "";

        String url_busca = URIUtil.encodeQuery(api_url + "?method=track.search&track=" + artista + " - " + titulo + "&api_key=" + api_key + "&format=json");

        System.out.println(url_busca);
        
        //Seta configurações de proxy e user_agent
        this.aplicaConfigs();

        URL url = new URL(url_busca);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String linha;
        while ((linha = in.readLine()) != null) {
            retorno = retorno + linha;
        }
        in.close();

        return retorno;
        //  InputStream is = url.openStream();
        // OutputStream os = new FileOutputStream(local_salvo);
        //OutputStream os = new S

        // System.out.println(is.);

    }

    public String obtemLinkCapa(String json_string) throws JSONException {

        String retorno = "";

        String text = "";
        String size = "";

        JSONObject jObject = new JSONObject(json_string);

        JSONObject results = jObject.getJSONObject("results");
        JSONObject trackmatches = results.getJSONObject("trackmatches");
        JSONArray track = trackmatches.getJSONArray("track");
        for (int i = 0; i < track.length(); i++) {

            try {
                JSONArray image = track.getJSONObject(i).getJSONArray("image");

                for (int i2 = 0; i2 < image.length(); i2++) {


                    try {
                        text = image.getJSONObject(i2).getString("#text").toString();
                        size = image.getJSONObject(i2).getString("size").toString();

                        if (size.equals("large") && retorno.equals("")) {
                            retorno = text;
                            return retorno;
                        }


                    } catch (Exception e) {
                        //sem link
                    }

                }
            } catch (Exception e) {
                //Sem image
            }

        }

        return retorno;
    }

    public byte[] downloadUrl(String link) throws MalformedURLException {
        //
        this.aplicaConfigs();
        
        URL toDownload = new URL(link);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            byte[] chunk = new byte[4096];
            int bytesRead;
            InputStream stream = toDownload.openStream();

            while ((bytesRead = stream.read(chunk)) > 0) {
                outputStream.write(chunk, 0, bytesRead);
            }

        } catch (IOException e) {
            return null;
        }

        return outputStream.toByteArray();
    }

    private void aplicaConfigs() {
        // Seta o user agente para fazer o Download
        System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 5.1; rv:20.0) Gecko/20100101 Firefox/20.0");

    }
}
