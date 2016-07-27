package com.javabase.corejava.part1;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * 
 * @author zhenhaiw
 *
 */
public class Chap6TimerTest {

	public static void main(String[] args) {
		ActionListener listener = new TimerPrinter();
		Timer timer = new Timer(1000, listener);
		timer.start();
		
		//construct a timer that calls the listener
		//once every 1 seconds
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}

}

class TimerPrinter implements ActionListener
{
	public void actionPerformed(ActionEvent event)
	{
		Date now = new Date();
		System.out.println("At the tone, the time is " + now);
		Toolkit.getDefaultToolkit().beep();
	}
}