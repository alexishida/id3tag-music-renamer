
package com.alexishida.utils;

import java.io.File;
import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.MediaFile;
import org.blinkenlights.jid3.v1.ID3V1_0Tag;
import org.blinkenlights.jid3.v2.ID3V2_3_0Tag;
import org.blinkenlights.jid3.v2.TIT2TextInformationID3V2Frame;
import org.blinkenlights.jid3.v2.TPE1TextInformationID3V2Frame;



public class Id3tag {

    public void renomear(String caminho, String artista, String musica) throws ID3Exception {
        
        
 // the file we are going to modify
        File oSourceFile = new File(caminho);

        // create an MP3File object representing our chosen file
        MediaFile oMediaFile = new MP3File(oSourceFile);

        // create a v1.0 tag object, and set some values
        ID3V1_0Tag oID3V1_0Tag = new ID3V1_0Tag();
        oID3V1_0Tag.setArtist(artista);
        oID3V1_0Tag.setTitle(musica);
  
       
        // set this v1.0 tag in the media file object
        oMediaFile.setID3Tag(oID3V1_0Tag);
       
        // create a v2.3.0 tag object, and set some frames
        ID3V2_3_0Tag oID3V2_3_0Tag = new ID3V2_3_0Tag();
        TPE1TextInformationID3V2Frame oTPE1 = new TPE1TextInformationID3V2Frame(artista);
        oID3V2_3_0Tag.setTPE1TextInformationFrame(oTPE1);
        //TRCKTextInformationID3V2Frame oTRCK = new TRCKTextInformationID3V2Frame(3, 9);
        //oID3V2_3_0Tag.setTRCKTextInformationFrame(oTRCK);
        TIT2TextInformationID3V2Frame oTIT2 = new TIT2TextInformationID3V2Frame(musica);
        oID3V2_3_0Tag.setTIT2TextInformationFrame(oTIT2);
       
        // set this v2.3.0 tag in the media file object
        oMediaFile.setID3Tag(oID3V2_3_0Tag);
       
        // update the actual file to reflect the current state of our object 
        oMediaFile.sync();



    }

   
}
