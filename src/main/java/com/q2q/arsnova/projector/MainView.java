package com.q2q.arsnova.projector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin on 3/5/15.
 */
public class MainView extends JFrame{
    private JRadioButton on = new JRadioButton("Projector On");
    private JRadioButton off = new JRadioButton("Projector Off");
    private ButtonGroup radios = new ButtonGroup();

    public MainView(String windowTitle){
        super(windowTitle);
        setLayout(new GridLayout());
        off.setSelected(true);

        radios.add(on);
        radios.add(off);
        on.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendHTTPControls control = new SendHTTPControls();
                control.projectorOn();
            }
        });
        off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendHTTPControls control = new SendHTTPControls();
                control.projectorOff();
            }
        });
        getContentPane().add(on);
        getContentPane().add(off);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);


    }


}
