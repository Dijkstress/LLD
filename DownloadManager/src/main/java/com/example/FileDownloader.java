package com.example;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLConnection;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class FileDownloader {

    private static final int BUFFER_SIZE = 64 * 1024;

    public void download(String url){
        try{
            URI uri = URI.create(url);
            String userProfile = System.getenv("USERPROFILE");
            Path downloadsFolder = Paths.get(userProfile, "Downloads");
            Path fileNamePath = Paths.get(uri.getPath()).getFileName();
            Path destination = downloadsFolder.resolve(fileNamePath);

            URLConnection connection = uri.toURL().openConnection();

            long file_size = connection.getContentLength();

            if(-1==file_size){
                System.out.println("Size unknown");
            }else{
                System.out.println("Total file size "+ file_size+ " bytes or "+ file_size/1024.0 + " KB" );
            }
            try(InputStream in = connection.getInputStream()){
                OutputStream out = Files.newOutputStream(destination);

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;
                long totalBytesDownloaded = 0;
                int lastPrintedPercentage = -1;
                System.out.println("Download started....");
                long startTime = System.currentTimeMillis();

                while ((bytesRead=in.read(buffer))!=-1){
                    out.write(buffer,0,bytesRead);

                    totalBytesDownloaded+=bytesRead;

                    if(file_size>0){
                        int currentPercentage = (int)((totalBytesDownloaded*100)/file_size);
                        if(currentPercentage!=lastPrintedPercentage){
                            System.out.println("Downloading: "+ currentPercentage+"%");
                            lastPrintedPercentage=currentPercentage;
                        }
                    }
                }
                long endTime = System.currentTimeMillis();
                long totalTimeMs = endTime - startTime;
                long totalSeconds = totalTimeMs / 1000;
                long minutes = totalSeconds / 60;
                long seconds = totalSeconds % 60; // Gets the remaining seconds
                long remainingMs = totalTimeMs % 1000; // Gets the fractional milliseconds

                System.out.println(); // Clear the percentage line
                System.out.println("Buffer Size [" + (BUFFER_SIZE / 1024) + " KB]");
                System.out.println("Finished in: " + minutes + " min " + seconds + " sec (" + totalTimeMs + " ms)");
                System.out.println("Download complete! Saved to: " + destination.toAbsolutePath());
            }
        }catch (FileAlreadyExistsException existsException){
            System.out.println("File already exists");
        }catch (Exception e){
            System.out.println("Error saving file: "+ e.getMessage());
            e.printStackTrace();
        }
    }
}
