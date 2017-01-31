/* 
 * LogFrame.java
 * 
 * Copyright (C) 2013-2017: Marco Dalla Libera 
 * 
 * acpi_call_GUI_systemd is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * acpi_call_GUI_systemd is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */
package acpi_call_gui_systemd;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
class LogFrame extends JFrame{
	
	//Constants area
	private static final int FRAME_WIDTH=590;
    private static final int FRAME_HEIGHT=290;
    
    //Variables area
    private JTextArea textArea;
	
	/**
	 * Constructor used by ACGCore logs functions, it creates the frame were the logs will be written.
	 * The Frame created isn't disposable, use 'disposeLogFrame' to change this state.
	 */
	public LogFrame(){
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Process running, please wait...");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon("/usr/share/pixmaps/acpiCallGui.png").getImage());
        textArea=new JTextArea();
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setVisible(true);
        JScrollPane scroll=new JScrollPane(textArea);
        add(scroll);
        setVisible(true);
	}
	/**
	 * Sets the text of the text area
	 * @param s the text to be set
	 */
	void setText(String s){
		textArea.setText(s);
	}
	/**
	 * makes the frame disposable
	 */
	void disposeLogFrame(){
        setTitle("Process complete.");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
