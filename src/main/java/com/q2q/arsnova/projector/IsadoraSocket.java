package com.q2q.arsnova.projector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Kevin on 3/5/15.
 */
public class IsadoraSocket extends Thread {
    private final int portNumber = 8675;

    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            while(in.readLine() != null){

            }
            SendHTTPControls controls = new SendHTTPControls();
            controls.toggleProjector();

            this.run();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
