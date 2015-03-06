package com.q2q.arsnova.projector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin on 3/5/15.
 */
public class MainView extends JFrame{
    private JRadioButton unmute = new JRadioButton("Unmute Projector");
    private JRadioButton mute = new JRadioButton("Mute Projector");
    private Button turnOff = new Button("Projector Off");
    private ButtonGroup radios = new ButtonGroup();
    private SendHTTPControls control = new SendHTTPControls();

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
                control.unmuteProjector();
            }
        });
        mute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.muteProjector();
            }
        });
        turnOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.turnProjectorOff();
            }
        });

        //Add UI elements to display
        getContentPane().add(unmute);
        getContentPane().add(mute);
        getContentPane().add(turnOff);

        pack();
        setVisible(true);
    }
}
