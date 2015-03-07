package com.q2q.arsnova.projector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin Karol on 3/5/15.
 *
 * This software is provided without warenty.
 * If you wish to use this software for a theatrical production
 * please contact its author first, and provide an appropriate credit
 * in the show's playbill and other production billing.
 */
public class MainView extends JFrame{
    private JRadioButton unmute = new JRadioButton("Unmute Projector");
    private JRadioButton mute = new JRadioButton("Mute Projector");
    private Button turnOff = new Button("Projector Off");
    private Button turnOn = new Button("Projector On");
    private ButtonGroup radios = new ButtonGroup();

    /**
     * Construct the UI for major projector controls
     * @param windowTitle the title to display for the window
     */
    public MainView(String windowTitle){
        super(windowTitle);
        setLayout(new GridLayout());
        mute.setSelected(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create button group
        radios.add(unmute);
        radios.add(mute);

        //Link UI to functions with action listeners
        unmute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectorArray.unmuteProjectors();
            }
        });
        mute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectorArray.muteProjectors();
            }
        });
        turnOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectorArray.turnProjectorsOff();
            }
        });

        turnOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectorArray.turnProjectorsOn();
            }
        });

        //Add UI elements to display
        getContentPane().add(unmute);
        getContentPane().add(mute);
        getContentPane().add(turnOn);
        getContentPane().add(turnOff);

        pack();
        setVisible(true);
    }
}
