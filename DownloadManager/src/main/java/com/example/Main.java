package com.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter url:");
        String url = scanner.next();
        System.out.println("Source: "+ url);

        FileDownloader fileDownloader = new FileDownloader();
        fileDownloader.download(url);

    }
}