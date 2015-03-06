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
    //Using single character encoding due to limited necessary functionality
    //If more functionality is needed in future versions, create seperate parser class
    private final int portNumber = 8675;
    private final int muteProjector = 'm';
    private final int unmuteProjector = 'u';
    private final int projectorOff = 'o';
    private final SendHTTPControls controls = new SendHTTPControls();

    /**
     * Extension of Thread's run function which allows this code to
     * run on a background thread so that waiting for the socket connection
     * is non-blocking
     */
    public void run(){
        try {
            //Listen for a connection and message
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            int compareCall = -1;

            //Parse the message and trigger events
            while((compareCall = in.read()) != -1){
                switch(compareCall){
                    case(muteProjector):
                        controls.muteProjector();
                        break;
                    case(unmuteProjector):
                        controls.unmuteProjector();
                        break;
                    case(projectorOff):
                        controls.turnProjectorOff();
                        break;
                    default:
                        break;
                }
            }

            //Create infinite loop waiting for connections
            serverSocket.close();
            this.run();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
