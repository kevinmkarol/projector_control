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
    private final String turnOn  = path + "?D=%05%02%11%00%00%00";
    private final String turnOff = path + "?D=%05%02%10%00%00%00";
    private static ArrayList<String> allProjectorNames;

    private boolean isProjectorOn = false;



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

    public void projectorOn(){
        for(String projector: allProjectorNames){
            sendMessage(projector + turnOn);
        }
    }

    public void projectorOff(){
        for(String projector: allProjectorNames){
            sendMessage(projector + turnOff);
        }
    }

    public void toggleProjector(){
        if(isProjectorOn){
            projectorOff();
            isProjectorOn = false;
        }else{
            projectorOn();
            isProjectorOn = true;
        }
    }

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
