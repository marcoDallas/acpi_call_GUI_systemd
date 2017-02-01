/* 
 * Acpi_call_GUI_systemdFrame.java
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * this frame contains the control panel that manages the operations,
 * package access because it has no sense to be used outside
 * @author Marco Dalla Libera `marcoDallas`
 */
@SuppressWarnings("serial")
class ACGSFrame extends JFrame implements ActionListener{
    
	//Constants area
	private static final int FRAME_WIDTH=600;
    private static final int FRAME_HEIGHT=300;
    static final String VERSION_ID="2.0.1";
    static final String COPYRIGHT_YEARS="2013-2017";
    private static final String DOCUMENTATION="https://github.com/marcoDallas/acpi_call_GUI_systemd/blob/master/README.md";
    private static final String ISSUE_TRACKER="https://github.com/marcoDallas/acpi_call_GUI_systemd/issues";
    
    //Variables area
    private ACGSCore core;
    
    private JPanel manager; //panel for buttons 'exit' and 'execute'
    private JMenuBar menuBar; //Menu bar 
    private JRadioButton sel; //button for the installation
    private JRadioButton deac; //button to turn off discrete GPU
    private JRadioButton change;//button to change the deactivation code
    private JRadioButton auto; //button for the automatization of the updates
    private JRadioButton find; //button to automatically find a deactivation code
    /**
     * constructs the Frame
     */
    public ACGSFrame(){
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setTitle("Acpi_call_GUI_systemd "+VERSION_ID);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon("/usr/share/pixmaps/acpiCallGui.png").getImage());
        menuBar=createMenuBar();
        setJMenuBar(menuBar);
        JPanel selection=createRadioButtons();
        add(selection,BorderLayout.CENTER);
        manager=createButtons();
        add(manager,BorderLayout.SOUTH);
        //Creates an ACGSCore object which will be used to perform the operations
        core=new ACGSCore();
        //Sets the frame visible
        setVisible(true);
    }
    /**
     * Creates the intro panel
     * @return the panel containing the intro
     */
    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenu helpMenu = new JMenu("help");
        menuBar.add(helpMenu);
        //setup of file menu
        JMenuItem menuEsci = new JMenuItem("Exit");
        fileMenu.add(menuEsci);
        //setup of help menu
        JMenuItem doc = new JMenuItem("Online documentation");
        helpMenu.add(doc);
        JMenuItem report = new JMenuItem("Report issue");
        helpMenu.add(report);
        JMenuItem about = new JMenuItem("About");
        helpMenu.add(about);
        //end setup menu
        //listeners
        menuEsci.addActionListener(this);
        doc.addActionListener(this);
        report.addActionListener(this);
        about.addActionListener(this);
        return menuBar;
    }
    /**
     * creates the RadioButtons panel
     * @return the panel containing the RadioButtons
     */
    private JPanel createRadioButtons(){
        sel=new JRadioButton("Install acpi_call");
        sel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deac=new JRadioButton("Turn off discrete GPU");
        deac.setCursor(new Cursor(Cursor.HAND_CURSOR));
        change=new JRadioButton("Change deactivation code");
        change.setCursor(new Cursor(Cursor.HAND_CURSOR));
        find=new JRadioButton("Try to automatically find a deactivation code");
        find.setCursor(new Cursor(Cursor.HAND_CURSOR));
        auto=new JRadioButton("Automate disabling discrete GPU every boot");
        auto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ButtonGroup gruppo=new ButtonGroup();
        gruppo.add(sel);
        gruppo.add(deac);
        gruppo.add(change);
        gruppo.add(find);
        gruppo.add(auto);
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(6,1));
        panel.add(sel);
        panel.add(deac);
        panel.add(change);
        panel.add(find);
        panel.add(auto);
        panel.setBorder(new TitledBorder(new EtchedBorder(),"Choose an operation and click 'execute':"));
        JLabel credits=new JLabel("Copyright (C) "+COPYRIGHT_YEARS+" Marco Dalla Libera");
        credits.setHorizontalAlignment(JLabel.CENTER);
        credits.setVerticalAlignment(JLabel.BOTTOM);
        panel.add(credits);
        return panel;
    }
    /**
     * Creates the panel for 'exit' and 'execute' buttons
     * @return the panel containing the buttons
     */
    private JPanel createButtons(){
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(1,2));
        JButton esci=new JButton("Exit");
        esci.setBackground(new Color(255,51,51));
        esci.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton esegui=new JButton("Execute");
        esegui.setBackground(new Color(26,255,102));
        esegui.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(esci);
        panel.add(esegui);
        //listeners
        esci.addActionListener(this);
        esegui.addActionListener(this);
        return panel;
    }
    /**
     * makes the Frame not closable and disables menu and manager panels
     */
    private void lockFrame(){
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	manager.setEnabled(false);
    	menuBar.setEnabled(false);
    }
    /**
     * makes the Frame closable again, also brings back menu and manager panels
     */
    private void releaseFrame(){
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	manager.setEnabled(true);
    	menuBar.setEnabled(true);
    }
    /**
     * catches the user's choice in the manager panel
     */
    public void actionPerformed(ActionEvent e){
    	String command=e.getActionCommand();
    	if(command.equals("Exit")){
    		System.exit(0);
    	}
    	if(command.equals("Online documentation")){
    		if(!core.openWebBrowser(DOCUMENTATION)){
    			JOptionPane.showMessageDialog(null, "Please install Firefox", "error", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(command.equals("Report issue")){
    		if(!core.openWebBrowser(ISSUE_TRACKER)){
    			JOptionPane.showMessageDialog(null, "Please install Firefox", "error", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(command.equals("About")){
    		@SuppressWarnings("unused")
			AboutFrame aboutFrame = new AboutFrame();
    	}
    	if(command.equals("Execute")){
    		lockFrame();
    		if(sel.isSelected()){
    			core.installazione(); 
    		}
            if(deac.isSelected()){ 
            	core.disattiva(); 
            }
            if(change.isSelected()){ 
            	core.insertCode(); 
            }
            if(auto.isSelected()){ 
            	core.automatizza(); 
            }
            if(find.isSelected()){ 
            	core.findCode(); 
            }
            releaseFrame();
    	}
    }
}
