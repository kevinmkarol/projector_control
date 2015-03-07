package com.q2q.arsnova.projector;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by Kevin Karol on 3/6/15.
 *
 * This software is provided without warenty.
 * If you wish to use this software for a theatrical production
 * please contact its author first, and provide an appropriate credit
 * in the show's playbill and other production billing.
 */
public class Projector extends Thread {
    private final String IPAddress;
    private ProjectorStates currentState;

    //Constants as defiened by projector interface
    private final String path = "/cgi-bin/pjctrl.cgi.elf/";
    private final String unmute  = path + "?D=%05%02%11%00%00%00";
    private final String mute = path + "?D=%05%02%10%00%00%00";
    private final String powerOff = path + "?D=%05%02%01%00%00%00";
    private final String powerOn = path + "?D=%05%02%00%00%00%00";

    /**
     * Initialize the projector's internal state with its IP address
     * @param IPAddress the IP address for the projector
     */
    public Projector(String IPAddress){
        this.IPAddress = IPAddress;
        currentState = ProjectorStates.ON;
    }

    /**
     * Set the projector state to be updated asyncronusly by the run call
     * @param state the ProjectorState to change the state to
     */
    public void setCurrentState(ProjectorStates state){
        currentState = state;
    }

    @Override
    public void run() {
        if(currentState.equals(ProjectorStates.ON)){
           sendMessage(IPAddress + powerOn);
        }else if(currentState.equals(ProjectorStates.OFF)){
            sendMessage(IPAddress + powerOff);
        }else if(currentState.equals(ProjectorStates.UNMUTE)){
            sendMessage(IPAddress + unmute);
        }else if(currentState.equals(ProjectorStates.MUTE)){
            sendMessage(IPAddress + mute);
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
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
