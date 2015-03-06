package com.q2q.arsnova.projector;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

import javax.swing.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kevin on 3/5/15.
 */
public class SendHTTPControls {
    //Values to be modified by users
    private static String projectorSEIP = "http://169.254.186.217";
    private static String projectorSWIP = "http://169.254.243.73";
    private static String projectorNEIP = "http://169.254.247.248";
    private static String projectorNWIP = "http://169.254.7.223";

    //Constants as defiened by projector interface
    private final String path = "/cgi-bin/pjctrl.cgi.elf/";
    private final String unmute  = path + "?D=%05%02%11%00%00%00";
    private final String mute = path + "?D=%05%02%10%00%00%00";
    private final String powerOff = path + "?D=%05%02%01%00%00%00";
    private static ArrayList<String> allProjectorNames;


    /**
     * Main function creates the UI and sets up a socket for
     * Isadora control
     * @param args no launching arguments are used
     */
    public static void main(String[] args) {
        JFrame frame = new MainView("Projector Controls");
        allProjectorNames = new ArrayList<String>();
        allProjectorNames.add(projectorSEIP);
        allProjectorNames.add(projectorSWIP);
        allProjectorNames.add(projectorNEIP);
        allProjectorNames.add(projectorNWIP);

        //Setup Isadora Socket
        (new IsadoraSocket()).start();

    }

    /**
     * This function unmutes a muted projector.  In the event the projector
     * is already unmuted, no visible change occurs.
     */
    public void unmuteProjector(){
        for(String projector: allProjectorNames){
            sendMessage(projector + unmute);
        }
    }

    /**
     * This function mutes an unmuted projector.  In the event the projector
     * is already muted, no visible change occurs.
     */
    public void muteProjector(){
        for(String projector: allProjectorNames){
            sendMessage(projector + mute);
        }
    }

    /**
     * This function fully powers down the projector.  Projectors require
     * a re-start time when turned off unlike muting/unmuting the projector
     */
    public void turnProjectorOff(){
        for(String projector: allProjectorNames){
            sendMessage(projector + powerOff);
        }
    }

    /**
     * This function creates a post request to the url passed to the function
     * No data is passed in the request
     * @param url the url to deliver the psot request to,
     *            thereby triggering the event
     */
    private void sendMessage(String url){
        //Code adapted from http://stackoverflow.com/questions/3324717/sending-http-post-request-in-java
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);

        //Execute and get response
        try{
           //not checking response for errors
           httpclient.execute(httppost);
            System.out.println("end try");
        }catch (IOException e){
            System.out.println("in catch");
            e.printStackTrace();
        }
        System.out.println("done");
    }

}
