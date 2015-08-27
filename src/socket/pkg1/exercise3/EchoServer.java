package socket.pkg1.exercise3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoServer implements Runnable{

    BufferedReader in;
    PrintWriter out;
    Socket s;

    public EchoServer(Socket s) throws IOException {
        this.s = s;
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out = new PrintWriter(s.getOutputStream(), true);
    }
    
    @Override
    public void run() {
        String input;
        String command;
        String text;
        
        boolean isRunning = true;
        
        while(isRunning){
            try {
                input = in.readLine();
                command = input.split("[#]")[0];                
                text = input.split("[#]")[1];
            } catch (Exception ex) {
                isRunning = false;
                break;
            }
            
            switch(command){
                case "UPPER":
                    out.println(text.toUpperCase());
                    break;
                case "LOWER":
                    out.println(text.toLowerCase());
                    break;
                case "REVERSE":
                    out.println(new StringBuilder(text).reverse().toString());
                    break;
                case "TRANSLATE":
                    out.println(translate(text));
                    break;
                default:
                       isRunning = false;
            }
        }
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String translate(String text){
        Map<String, String> translates = new HashMap();
        translates.put("hund", "dog");
        translates.put("hus", "house");
        translates.put("bil", "car");
        translates.put("fulg", "bird");
        translates.put("kat", "cat");
        text = text.toLowerCase();
        if(!translates.containsKey(text)){
            return "Cant translate " + text;
        }
        return translates.get(text);
    }
}
