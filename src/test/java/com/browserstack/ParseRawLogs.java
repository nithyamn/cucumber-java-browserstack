package com.browserstack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ParseRawLogs {
    public static void main(String[] args) throws FileNotFoundException {

        try {

            PrintWriter requests = new PrintWriter("src/test/resources/logs.txt");
            //PrintWriter responses = new PrintWriter("src/test/resources/raw-logs-response.txt");
            String currentLog = null;
            PrintWriter parsedFile = new PrintWriter("src/test/resources/logs.txt");

            Scanner s = new Scanner(new File("src/test/resources/raw-logs.txt"));
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.contains("annotate")) {
                    currentLog = line + "\n";
                    System.out.println(line);
                    parsedFile.append(line);
                    parsedFile.println(line);
                }
                else if (parsedFile != null) {
                    //System.out.printf("null");
                }
            }

            requests.close();
            //responses.close();
            s.close();
        } catch (IOException ioex) {
            // handle exception...
        }
    }
}
