package com.javabase.corejava.part1;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Chap6AnonymousInnerClassTest 
{

	public static void main(String[] args) 
	{
		TalkingClockA clock = new TalkingClockA();
		clock.start(1000, true);
		
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}

}

class TalkingClockA
{
//	public TalkingClockA(int interval, boolean beep)
//	{
//		this.interval = interval;
//		this.beep = beep;
//	}
	
//	private int interval;
//	private boolean beep;
	
	/**
	 * Start the clock
	 * @param interval the interval between messages (in milliseconds)
	 * @param beep true if the clock should beep
	 */
	public void start(int interval, final boolean beep)
	{
		ActionListener listener = new ActionListener()
		//匿名(局部)内部类, 块要以;结尾 
		{
			public void actionPerformed(ActionEvent event)
			{
				Date now = new Date();
				System.out.println("At the tone, the time is " + now);
				Toolkit.getDefaultToolkit().beep();
			}
		};//注意结尾;
		
		Timer timer = new Timer(interval, listener);
		timer.start();
	}
}