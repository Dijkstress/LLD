package com.example;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class FileDownloader {

    public void download(String url){
        try{
            URI uri = URI.create(url);
            String userProfile = System.getenv("USERPROFILE");
            Path downloadsFolder = Paths.get(userProfile, "Downloads");
            Path fileNamePath = Paths.get(uri.getPath()).getFileName();
            Path destination = downloadsFolder.resolve(fileNamePath);
            try(InputStream in = uri.toURL().openStream()){
                Files.copy(in,destination);
                System.out.println("File saved to: "+destination.toAbsolutePath());
            }
        }catch (FileAlreadyExistsException existsException){
            System.out.println("File already exists");
        }catch (Exception e){
            System.out.println("Error saving file: "+ e.getMessage());
            e.printStackTrace();
        }
    }
}
