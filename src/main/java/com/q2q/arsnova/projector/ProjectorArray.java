package com.q2q.arsnova.projector;

import javax.swing.*;

import java.util.ArrayList;

/**
 * Created by Kevin on 3/5/15.
 */
public class ProjectorArray {
    //Values to be modified by users
    private static String projectorSEIP = "http://169.254.186.217";
    private static String projectorSWIP = "http://169.254.243.73";
    private static String projectorNEIP = "http://169.254.247.248";
    private static String projectorNWIP = "http://169.254.7.223";

    private static ArrayList<Projector> allProjectors;

    /**
     * Main function creates the UI and sets up a socket for
     * Isadora control
     * @param args no launching arguments are used
     */
    public static void main(String[] args) {
        JFrame frame = new MainView("Projector Controls");
        allProjectors = new ArrayList<Projector>();
        allProjectors.add(new Projector(projectorNEIP));
        allProjectors.add(new Projector(projectorNWIP));
        allProjectors.add(new Projector(projectorSEIP));
        allProjectors.add(new Projector(projectorSWIP));

        //Setup Isadora Socket
        (new IsadoraSocket()).start();

    }

    /**
     * This function unmutes a muted projector.  In the event the projector
     * is already unmuted, no visible change occurs.
     */
    public void unmuteProjectors(){
        for(Projector p: allProjectors){
            p.setCurrentState(ProjectorStates.UNMUTE);
            p.start();
        }
    }

    /**
     * This function mutes an unmuted projector.  In the event the projector
     * is already muted, no visible change occurs.
     */
    public void muteProjectors(){
        for(Projector p: allProjectors){
            p.setCurrentState(ProjectorStates.MUTE);
            p.start();
        }
    }

    /**
     * This function powers on the projector.  Projectors require
     * a re-start time when turned off and on unlike muting/unmuting the projector
     */
    public void turnProjectorsOn(){
        for(Projector p: allProjectors){
            p.setCurrentState(ProjectorStates.ON);
            p.start();
        }
    }

    /**
     * This function fully powers down the projector.  Projectors require
     * a re-start time when turned off and on unlike muting/unmuting the projector
     */
    public void turnProjectorsOff(){
        for(Projector p: allProjectors){
            p.setCurrentState(ProjectorStates.OFF);
            p.start();
        }
    }
}
