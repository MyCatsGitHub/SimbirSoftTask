package com.company;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file;
        Parser parser;
        FileWriter writer;
        URL url;
        try {
            parser = new Parser();
            System.out.println("Введите адрес");
            String inputAddress = scanner.nextLine();
            System.out.println("Введите имя файла для записи");
            String fileName = scanner.nextLine();
            url = new URL(inputAddress);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            String inputLine;
            String result = "";
            while ((inputLine = bufferedReader.readLine()) != null) {
                result += inputLine;
            }
            parser.parse(result);
            file = new File(fileName);
            writer = new FileWriter(file);
            Map<String, Integer> endMap = parser.getWordMap();
            for (Map.Entry<String, Integer> pair : endMap.entrySet()) {
                System.out.println(pair.getKey() + " " + pair.getValue());
                writer.write(pair.getKey() + " " + pair.getValue());
                writer.append("\n");
            }
            bufferedReader.close();
            writer.close();
        } catch (IOException exception) {
            System.out.println("Неверный адрес");
            exception.printStackTrace();
        }
        catch (OutOfMemoryError exception){
            System.out.println("Недостаточно памяти");
            exception.printStackTrace();
        }


    }
}
