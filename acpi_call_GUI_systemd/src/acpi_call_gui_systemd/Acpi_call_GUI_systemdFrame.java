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
 * 
 * and:
 * 
 * Copyright (c) 2010: Michal Kottman
 * 
 * acpi_call is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * acpi_call is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 */
package acpi_call_gui_systemd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * this frame contains the control panel that manages the operations
 * @author Marco Dalla Libera `marcoDallas`
 */
public class Acpi_call_GUI_systemdFrame extends JFrame{
    
	//Constants area
	private static final int FRAME_WIDTH=600;
    private static final int FRAME_HEIGHT=300;
    private	static final String VERSION_ID="1.4.1";
    private static final String COPYRIGHT_YEARS="2013-2017";
    
    //Variables area
    private JPanel intro; //label for the introduction
    private JPanel selection; // panel for RadioButtons
    private JPanel manager; //panel for buttons 'exit' and 'execute' 
    private JRadioButton sel; //button for the installation
    private JRadioButton deac; //button to turn off discrete GPU
    private JRadioButton change;//button to change the deactivation code
    private JRadioButton auto; //button for the automatization of the updates
    private JRadioButton find; //button to automatically find a deactivation code
    private JButton esegui; //button execute
    private JButton esci; //button exit
    private Process script;//The Process (Script) that is running
    /**
     * constructs the Frame
     */
    public Acpi_call_GUI_systemdFrame(){
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Acpi_call_GUI "+VERSION_ID);
        setLocationRelativeTo(null);
        intro=createIntro();
        add(intro,BorderLayout.NORTH);
        selection=createRadioButtons();
        add(selection,BorderLayout.CENTER);
        manager=createButtons();
        add(manager,BorderLayout.SOUTH);
        setVisible(rootPaneCheckingEnabled);
    }
    /**
     * Creates the intro panel
     * @return the panel containing the intro
     */
    private JPanel createIntro(){
        JPanel panel=new JPanel();
        JLabel in=new JLabel("Choose an operation and click 'execute' :");
        in.setHorizontalTextPosition(SwingConstants.CENTER);
        panel.setBorder(new TitledBorder(new EtchedBorder(),""));
        panel.add(in);        
        return panel;
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
        panel.setLayout(new GridLayout(7,1));
        panel.add(sel);
        panel.add(deac);
        panel.add(change);
        panel.add(find);
        panel.add(auto);
        panel.setBorder(new TitledBorder(new EtchedBorder(),"Operation:"));
        panel.setBackground(Color.LIGHT_GRAY);
        JLabel credits=new JLabel("Copyright (C) "+COPYRIGHT_YEARS+" Marco Dalla Libera");
        credits.setHorizontalAlignment(JLabel.CENTER);
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
        esci=new JButton("Exit");
        esci.setBackground(Color.red);
        esci.setCursor(new Cursor(Cursor.HAND_CURSOR));
        esegui=new JButton("Execute");
        esegui.setBackground(Color.GREEN);
        esegui.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(esci);
        panel.add(esegui);
        panel.setBorder(new TitledBorder(new EtchedBorder(),""));
        listenerManager();
        return panel;
    }
    /**
     * catches the user's choice in the manager panel
     */
    private void listenerManager(){
        esci.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });  
        esegui.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                chooseAction();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
    /**
     * 
     */
    private void chooseAction(){
        if(sel.isSelected()){ installazione(); }
        if(deac.isSelected()){ disattiva(); }
        if(change.isSelected()){ insertCode(); }
        if(auto.isSelected()){ automatizza(); }
        if(find.isSelected()){ findCode(); }
    }
    /**
     * installs the acpi_call module
     */
    private void installazione(){
        if(!(insertCode()))return;
        ProcessBuilder pb=new ProcessBuilder("/bin/sh","/usr/local/bin/acpi_call_GUI_systemd/installation.sh");
        pb.redirectErrorStream(true);
        try {
            script=pb.start();
        } catch (IOException ex) {
            Logger.getLogger(Acpi_call_GUI_systemdFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        logs(0);
    }
    /**
     * turns off discrete GPU
     */
    private void disattiva(){
        File f=new File("/usr/local/bin/acpi_call_GUI_systemd/codes/off");
        Scanner scan;
        try {
            scan = new Scanner(f);
            if(!(scan.hasNextLine())){ 
                if(!(insertCode()))return;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Acpi_call_GUI_systemdFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ProcessBuilder pb=new ProcessBuilder("/bin/sh","/usr/local/bin/acpi_call_GUI_systemd/deactivate.sh");
        pb.redirectErrorStream(true);
        try {
            script=pb.start();
        } catch (IOException ex) {
            Logger.getLogger(Acpi_call_GUI_systemdFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        logs(1);
    }
    /**
     * turns off the discrete GPU on every accension
     */
    private void automatizza(){
        ProcessBuilder pb=new ProcessBuilder("/bin/sh","/usr/local/bin/acpi_call_GUI_systemd/automates.sh");
        pb.redirectErrorStream(true);
        try {
            script=pb.start();
        } catch (IOException ex) {
            Logger.getLogger(Acpi_call_GUI_systemdFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        logs(2);
    }
    /**
     * Tries to automatically find a deactivation code.
     */
    private void findCode(){
        String s="It SHOULD be ok to test all of the methods,\n"
                + "however it comes with NO WARRANTY - it may hang your computer/laptop, fail to work, etc.\n"
                + "do you want to try anyway?";
        int returnValue = JOptionPane.showConfirmDialog(null,s,"Read carefully",JOptionPane.OK_CANCEL_OPTION);
        boolean found=false;
        String code="";
        if(returnValue==JOptionPane.YES_OPTION){
            ProcessBuilder pb=new ProcessBuilder("/bin/sh","/usr/local/bin/acpi_call/examples/turn_off_gpu.sh");
            pb.redirectErrorStream(true);
            try {
                script=pb.start();
                InputStream is=script.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);
                BufferedReader buffer=new BufferedReader(isr);
                String tmp="";
                while((tmp=buffer.readLine())!=null && !(found)){
                    if(tmp.contains("works!")){
                        found=true;
                        StringTokenizer tokenizer=new StringTokenizer(tmp);
                        for(int i=0;i<2;i++){
                            code=tokenizer.nextToken();
                        }
                        code=code.substring(0, code.length()-1);
                        try (PrintWriter printer = new PrintWriter("/usr/local/bin/acpi_call_GUI_systemd/codes/off")) {
                            printer.print(code);
                        }
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(Acpi_call_GUI_systemdFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(found)
            JOptionPane.showMessageDialog(null,"code: \""+code+"\"\nhas been found and set to be used!","result",JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null,"no deactivation code found!","result",JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * reads the deactivation code
     * @return true if the code was insert, false otherwise.
     */
    private boolean insertCode(){
        try{
            String s="By clicking ok, it will appear a web page. \nin the `Working ACPI handle OFF` column you will find the codes to deactivate the discrete GPU.\nthey are classified by PC manufacturer.";
            s+="\nType in the next window the code relative to your pc. \n\nIF YOU HAVEN'T FOUND YOUR PC:\nleave this input-box empty, click ok and then try \"Try to automatically find a deactivation code\".";
            JOptionPane.showMessageDialog(null,s,"Go to web page?",JOptionPane.QUESTION_MESSAGE);
            String link="http://hybrid-graphics-linux.tuxfamily.org/index.php?title=ACPI_calls#Individual_Model_results";
            ProcessBuilder pb=new ProcessBuilder("firefox",link);
            try {
                pb.start();
            } catch (IOException ex) {
                Logger.getLogger(Acpi_call_GUI_systemdFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            String codice=JOptionPane.showInputDialog(null, "Code:","Code");
            if(codice==null){
                JOptionPane.showMessageDialog(null,"You need to insert a code in order to procede!","Confirm",JOptionPane.WARNING_MESSAGE);
                return false;
            }
            try (PrintWriter printer = new PrintWriter("/usr/local/bin/acpi_call_GUI_systemd/codes/off")) {
                printer.print(codice);
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return true;
    }
    /**
     * Creates a JFrame that shows the output from the scripts,
     * and finally save the output to a log file
     * @param process identifies installation, deactivation, automatization.
     */
    private void logs(int process){
        manager.setVisible(false);
        JFrame log=new JFrame();
        log.setSize(FRAME_WIDTH-10,FRAME_HEIGHT-10);
        log.setLocationRelativeTo(null);
        log.setTitle("Process running, please wait...");
        log.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        log.setResizable(false);
        log.setVisible(true); 
        JTextArea textArea=new JTextArea();
        textArea.setLineWrap(true);
        textArea.setVisible(true);
        textArea.setEditable(false);
        JScrollPane scroll=new JScrollPane(textArea);
        log.add(scroll);
        String s="",tmp="",url="";
        InputStream is=script.getInputStream();
        InputStreamReader isr=new InputStreamReader(is);
        BufferedReader buffer=new BufferedReader(isr);
        try {
            while((tmp=buffer.readLine())!=null){
                if(process==1 && tmp.contains("0x0"))
                    s+="Discrete GPU correctly deactivated\n";
                else{
                    if(process==1 && tmp.contains("File exists"));
                    else {
                        s+=tmp+"\n";
                    }
                }
                textArea.setText(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(Acpi_call_GUI_systemdFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(process==0){ 
            url="/usr/local/bin/acpi_call_GUI_systemd/log/install_log"; 
        }
        else{
            if(process==1){ 
                url="/usr/local/bin/acpi_call_GUI_systemd/log/deactivate_log"; 
            }
            else{ 
                url="/usr/local/bin/acpi_call_GUI_systemd/log/automates_log"; 
            }
        }
        PrintWriter printer;
        try {
            printer = new PrintWriter(url);
            printer.print(s); 
            printer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Acpi_call_GUI_systemdFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        manager.setVisible(true);
        s+="Process Complete.";
        textArea.setText(s);
        log.setTitle("Process complete.");
    }
}
