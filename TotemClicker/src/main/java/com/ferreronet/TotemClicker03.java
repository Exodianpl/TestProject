package com.ferreronet;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.fazecast.jSerialComm.SerialPort;



public class TotemClicker03 implements ActionListener {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    public static void addComponentsToPane(Container pane) {
 
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

		ImageIcon top = new ImageIcon("FerreroTop.jpg");
		ImageIcon samochodIcon = new ImageIcon("Cysterna.jpg");
		ImageIcon ciezarowkaIcon = new ImageIcon("TransportWewnetrzny.jpg");
		ImageIcon samochod2Icon = new ImageIcon("Autobus.jpg");
		ImageIcon ciezarowka2Icon = new ImageIcon("DostawyTechniczne.jpg");	
        
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        JButton samochod = new JButton("", samochodIcon);
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.gridx = 0;
        c.gridy = 1;
        pane.add(samochod, c);

        JButton ciezarowka = new JButton("", ciezarowkaIcon);
        c.gridx = 1;
        c.gridy = 1;
        pane.add(ciezarowka, c);

        JButton samochod2 = new JButton("", samochod2Icon);
        c.gridx = 0;
        c.gridy = 2;
        pane.add(samochod2, c);

        JButton ciezarowka2 = new JButton("", ciezarowka2Icon);
        c.gridx = 1;
        c.gridy = 2;
        pane.add(ciezarowka2, c);

        JLabel topLabel = new JLabel(top);
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;

        //Serial Port Experiments
        //String x = toString(SerialPort.getCommPort("USB\\ROOT_HUB20\\4&78cd1a&0"));
        
        
        System.out.println(SerialPort.getVersion());
        SerialPort sp = SerialPort.getCommPort("COM1");
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
        
        if(sp.openPort()) {
        	System.out.println("Port is OPEN!");
        } else {
        	System.out.println("Port is not open");
        	return;
        }
        
        for (Integer i = 0 ; i < 5 ; ++i) {
        	try {
				sp.getOutputStream().write(i.byteValue());
				sp.getOutputStream().flush();
				System.out.println("Sent number: " + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
        }
        
    	if(sp.closePort()) {
    		System.out.println("Port is closed :)");
    	} else {
    		System.out.println("Port is not closed!");
    	}
        
        //===================== End of Experiment
		samochod.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
			
				JOptionPane.showMessageDialog(null, "Cysterna - otworzyc brame");
			}	
		});
				
		ciezarowka.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
			
				JOptionPane.showMessageDialog(null, "Transport Wewnetrzny - otworzyc brame");
			}	
		});
		
		samochod2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
			
				JOptionPane.showMessageDialog(null, "Autobus - otworzyc brame");
			}	
		});
		
		ciezarowka2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
			
				JOptionPane.showMessageDialog(null, "Dostawy Techniczne - otworzyc brame");
			}	
		});
		
		
		
        pane.add(topLabel, c);

        
       
    }
  

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(false);
        
        //Create and set up the window.
        JFrame frame = new JFrame("TotemClicker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(
        		new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}



}