package com.javabase.corejava.part1;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * This program demonstrates the use of inner classes
 * @author zhenhaiw
 *
 */
public class Chap6InnerClassTest 
{
	public static void main(String[] args) 
	{
		TalkingClock clock = new TalkingClock(1000, true);
		clock.start();
		
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}

}

/**
 * A clock that prints the time in regular intervals
 * @author zhenhaiw
 *
 */
class TalkingClock
{
	/**
	 * Construct a Talking Clock
	 * @param interval the interval between message(in milliseconds)
	 * @param beep true if the clock should beep
	 */
	public TalkingClock(int interval, boolean beep)
	{
		this.interval = interval;
		this.beep = beep;
	}
	
	private int interval;
	private boolean beep;
	
	/**
	 * Start the clock
	 */
	public void start()
	{
		ActionListener listener = new TimerPrinter();
		Timer timer = new Timer(interval, listener);
		timer.start();
	}
	
	//可以把内部类传递给Chap5ReflectionTest来反射看一下内部实现  完整内部类名 - "com.javabase.corejava.part1.TalkingClock$TimerPrinter"
	class TimerPrinter implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Date now = new Date();
			System.out.println("At the tone, time is " + now);
			if(beep)
				Toolkit.getDefaultToolkit().beep();
		}
	}
}
