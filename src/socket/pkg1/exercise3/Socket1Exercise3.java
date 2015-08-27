/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.pkg1.exercise3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 *
 * @author AlexanderSteen
 */
public class Socket1Exercise3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String ip = "localhost";
        int port = 1234;
        
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, port));
        
        while(true){
           new Thread(new EchoServer(ss.accept())).start();
        }
    }
    
}
