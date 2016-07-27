package com.javabase.corejava.part1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A modification of the image viewer program that logs various events.
 * @author zhenhaiw
 *
 */
public class Chap11LoggingImageViewer 
{

	public static void main(String[] args) 
	{
		if(System.getProperty("java.util.logging.config.class") == null
				&& System.getProperty("java.util.logging.config.file") == null)
		{
			try 
			{
				Logger.getLogger("com.javabase.corejava。part1").setLevel(Level.ALL);
				final int LOG_ROTATION_COUNT = 10;
				Handler handler = new FileHandler("%h/LoggingImageViewer.log", 0, LOG_ROTATION_COUNT);
				Logger.getLogger("com.javabase.corejava.part1").addHandler(handler);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				Logger.getLogger("com.javabase.corejava.part1").log(Level.SEVERE, "Can't create log file handler", e);
			}
			
		}
		
		EventQueue.invokeLater
		(
			new Runnable() 
			{
				public void run() 
				{
					Handler windowHandler = new WindowHandler();
					windowHandler.setLevel(Level.ALL);
					Logger.getLogger("com.javabase.corejava.part1").addHandler(windowHandler);
					
					JFrame frame = new ImageViewerFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					Logger.getLogger("com.javabase.corejava.part1").fine("Showing frame.");
					frame.setVisible(true);
				}
			}
		);
	}

}

/**
 *The frame that shows the image.
 * @author zhenhaiw
 *
 */
class ImageViewerFrame extends JFrame
{
	public ImageViewerFrame()
	{
		logger.entering("ImageViewerFrame", "<init>");
		setTitle("LoggingImageViewer");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//set up menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new FileOpenListener());
		
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					logger.fine("Exiting.");
					System.exit(0);
				}
			}
		);
		
		//use a label to display the images
		label = new JLabel();
		add(label);
		logger.exiting("ImageViewerFrame", "<init>");
	}
		
	private class FileOpenListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			logger.entering("ImageViwerFrame.FileOpenListener", "actionPerformed", event);
			
			//set up file choose
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			
			//accept all files ending with .gif
			chooser.setFileFilter
			(
				new javax.swing.filechooser.FileFilter()//匿名内部类写法
				{
					public boolean accept(File f)
					{
						return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
					}
					
					public String getDescription()
					{
						return "GIF Images";
					}
				}
			);
			
			//show file chooser dialog
			int r = chooser.showOpenDialog(ImageViewerFrame.this);
			
			//if image file accepted, set it as icon of the label
			if(r == JFileChooser.APPROVE_OPTION)
			{
				String name = chooser.getSelectedFile().getPath();
				logger.log(Level.FINE, "Reading file {0}", name);
				label.setIcon(new ImageIcon(name));
			}
			else
				logger.fine("File open dialog cancelled.");	
			logger.exiting("ImageViewerFrame.FileOpenLostener", "actionPerformed");
		}
	}
	
	private static Logger logger = Logger.getLogger("com.javabase.core.part1");
	private JLabel label;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;
}

/**
 * a handler for displaying log records in a window
 * @author zhenhaiw
 *
 */
class WindowHandler extends StreamHandler
{
	public WindowHandler()
	{
		frame = new JFrame();
		final JTextArea output = new JTextArea();
		output.setEditable(false);
		frame.setSize(200, 200);
		frame.add(new JScrollPane(output));
		frame.setFocusableWindowState(false);
		frame.setVisible(true);
		setOutputStream
		(
			new OutputStream()
			{
				public void write(int b)
				{
				}//not called
				
				public void write(byte[] b, int off, int len)
				{
					output.append(new String(b, off, len));
				}
			}
		);
	}
	
	private JFrame frame;
}