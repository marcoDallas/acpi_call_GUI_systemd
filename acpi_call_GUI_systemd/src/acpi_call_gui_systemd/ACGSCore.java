/* 
 * ACGCore.java
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import linkutils.LinkUtils;

/**
 * This class contains all the core methods for acpi_call_GUI_systemd to work
 * @author Marco Dalla Libera
 *
 */
class ACGSCore {
	/**
     * installs the acpi_call module
     */
    void installazione(){
        if(!(insertCode()))return;
        ProcessBuilder pb=new ProcessBuilder("/bin/sh","/usr/local/bin/acpi_call_GUI_systemd/installation.sh");
        pb.redirectErrorStream(true);
        Process script=null;
        try {
            script=pb.start();
        } catch (IOException ex) {
            Logger.getLogger(ACGSFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"An error occured while launchig script,\nplease try again", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        logs(0,script);
        if(checkModule("acpi_call")){
        	JOptionPane.showMessageDialog(null, "Succesful installation, please reboot before using the deactivation option.", "result",JOptionPane.INFORMATION_MESSAGE);
        }else{
        	JOptionPane.showMessageDialog(null,"Installation failed. log file:\n/usr/local/bin/acpi_call_GUI_systemd/log/install_log","error",JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * turns off discrete GPU
     */
    void disattiva(){
    	if(checkModule("radeon") || checkModule("amdgpu") || checkModule("nouveau")){
        	JOptionPane.showMessageDialog(null,"Please use installation option and reboot before using this option","error",JOptionPane.WARNING_MESSAGE);
        	return;
        }
        ProcessBuilder pb=new ProcessBuilder("/bin/sh","/usr/local/bin/acpi_call_GUI_systemd/deactivate.sh");
        pb.redirectErrorStream(true);
        Process script=null;
        try {
            script=pb.start();
        } catch (IOException ex) {
            Logger.getLogger(ACGSFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"An error occured while launchig script,\nplease try again", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        logs(1,script);
    }
    /**
     * turns off the discrete GPU on every boot
     */
    void automatizza(){
    	if(!(checkModule("acpi_call"))){ 
        	JOptionPane.showMessageDialog(null,"Use the installation option first!","error",JOptionPane.WARNING_MESSAGE);
        	return;
        }
        ProcessBuilder pb=new ProcessBuilder("/bin/sh","/usr/local/bin/acpi_call_GUI_systemd/automates.sh");
        pb.redirectErrorStream(true);
        Process script=null;
        try {
            script=pb.start();
        } catch (IOException ex) {
            Logger.getLogger(ACGSFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"An error occured while launchig script,\nplease try again", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        logs(2,script);
    }
    /**
     * Tries to automatically find a deactivation code, it finds one it also calls installazione method
     */
    void findCode(){
    	if(!(checkModule("acpi_call"))){ 
        	JOptionPane.showMessageDialog(null,"Use the installation option first:\nlet its input box empty and click 'OK'.\nAfter that you can use this function","error",JOptionPane.WARNING_MESSAGE);
        	return;
        }
        String s="It SHOULD be ok to test all of the methods,\n"
                + "however it comes with NO WARRANTY - it may hang your computer/laptop, fail to work, etc.\n"
                + "do you want to try anyway?";
        int returnValue = JOptionPane.showConfirmDialog(null,s,"Read carefully",JOptionPane.OK_CANCEL_OPTION);
        String code="";
        if(returnValue==JOptionPane.YES_OPTION){
            ProcessBuilder pb=new ProcessBuilder("pkexec","/usr/local/bin/acpi_call/examples/turn_off_gpu.sh");
            pb.redirectErrorStream(true);
            try {
                Process process=pb.start();
                InputStream is=process.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);
                BufferedReader buffer=new BufferedReader(isr);
                String tmp="";
                while((tmp=buffer.readLine())!=null){
                    if(tmp.contains("works!")){
                        StringTokenizer tokenizer=new StringTokenizer(tmp);
                        for(int i=0;i<2;i++){
                            code=tokenizer.nextToken(); //removes 'Trying' from the output line
                        }
                        code=code.substring(0, code.length()-1); //removes ':' from the end of the code
                        try (PrintWriter printer = new PrintWriter("/usr/local/bin/acpi_call_GUI_systemd/codes/off")) {
                            printer.print(code);
                            printer.close();
                            JOptionPane.showMessageDialog(null,"code: \""+code+"\"\nhas been found and set to be used!","result",JOptionPane.INFORMATION_MESSAGE);
                            buffer.close();
                            isr.close();
                            is.close();
                            process.destroy();
                            return;
                        } catch (IOException e){
                        	e.printStackTrace();
                        	JOptionPane.showMessageDialog(null,"a code was found but an error occured\nwhile writing it to file", "error", JOptionPane.ERROR_MESSAGE);
                        	buffer.close();
                            isr.close();
                            is.close();
                            process.destroy();
                        	return;                        	
                        }
                    }
                }
                buffer.close();
                isr.close();
                is.close();
                process.destroy();

            } catch (IOException ex) {
                Logger.getLogger(ACGSFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"An error occured while launchig script,\nplease try again", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"no deactivation code found!","result",JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * opens firefox to let the user look for a the deactivation code, then writes it to file
     * @return true if the code was insert, false otherwise.
     */
    boolean insertCode(){
        String s="By clicking ok, it will appear a web page. \nin the `Working ACPI handle OFF` column you will find the codes to deactivate the discrete GPU.\nthey are classified by PC manufacturer.";
        s+="\nType in the next window the code relative to your pc. \n\nIF YOU HAVEN'T FOUND YOUR PC:\nleave this input-box empty, click ok and then try \"Try to automatically find a deactivation code\".";
        JOptionPane.showMessageDialog(null,s,"Go to web page?",JOptionPane.QUESTION_MESSAGE);
        String link="http://hybrid-graphics-linux.tuxfamily.org/index.php?title=ACPI_calls";
        ProcessBuilder pb=new ProcessBuilder("firefox",link);
        try {
            pb.start();
        } catch (IOException ex) {
            Logger.getLogger(ACGSFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"An error occured while opening firefox,\nplease make sure it's installed", "error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String codice=JOptionPane.showInputDialog(null, "Code:","Code");
        if(codice==null){
            JOptionPane.showMessageDialog(null,"You need to insert a code in order to procede!","Confirm",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try (PrintWriter printer = new PrintWriter("/usr/local/bin/acpi_call_GUI_systemd/codes/off")) {
            printer.print(codice);
            printer.close();
        }catch(FileNotFoundException e){
        	JOptionPane.showMessageDialog(null,"an error occured while writing to file,\nplease try again", "error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * Uses lsmod to check if the given module is present
     * @param  module : the module to search
     * @return true if the installation completed successfully, false otherwise
     */
    private boolean checkModule(String module){
    	ProcessBuilder pb = new ProcessBuilder("lsmod");
    	pb.redirectErrorStream(true);
    	try {
			Process process = pb.start();
			InputStream is=process.getInputStream();
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader buffer=new BufferedReader(isr);
            String tmp="";
            while((tmp=buffer.readLine())!=null){
                if(tmp.contains(module)){
                	buffer.close();
                	isr.close();
                	is.close();
                	process.destroy();
                	return true;
                }
            }
            buffer.close();
            isr.close();
            is.close();
            process.destroy();
		} catch (IOException e) {
			Logger.getLogger(ACGSFrame.class.getName()).log(Level.SEVERE, null, e);
			JOptionPane.showMessageDialog(null,"An error occured while launchig script,\nplease try again", "error", JOptionPane.ERROR_MESSAGE);
            return false;
		}
    	return false;
    }
    /**
     * Creates a JFrame using LogFrame to show the output from the scripts,
     * and finally saves the output to a log file
     * @param process identifies installation, deactivation, automatization to know in which file to save the log
     * @param script The method will read the output of this Process
     */
    private void logs(int process, Process script){
    	LogFrame frame = new LogFrame();
        String s="",tmp="",url="";
        InputStream is=script.getInputStream();
        InputStreamReader isr=new InputStreamReader(is);
        BufferedReader buffer=new BufferedReader(isr);
        try {
            while((tmp=buffer.readLine())!=null){
                if(process==1 && tmp.contains("0x0"))
                    s+="Discrete GPU correctly deactivated\n";
                else{
                    if(process==1 && tmp.contains("File exists"));//error suppression, the file is always present after installation
                    else {
                        s+=tmp+"\n";
                    }
                }
                frame.setText(s);
            }
            buffer.close();
            isr.close();
            is.close();
            script.destroy();
        } catch (IOException ex) {
            Logger.getLogger(ACGSFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"An error occured while reading the output, log incomplete", "error", JOptionPane.ERROR_MESSAGE);
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
            Logger.getLogger(ACGSFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Log file not found, can't save the report", "error", JOptionPane.ERROR_MESSAGE);
            frame.disposeLogFrame();
            return;
        }
        s+="Process Complete.";
        frame.setText(s);
        frame.disposeLogFrame();
    }
    /**
     * tries to open the default web browser, if it's not possible it opens Firefox,
     * this method uses class LinkUtils that is made by me and it's closed source.
     * @param link The link to be opened in web browser
     * @return true if the web browser is successfully opened, false otherwise 
     */
    boolean openWebBrowser(String link){
    	LinkUtils webBrowser=new LinkUtils();
    	int check=webBrowser.openDefaultWebBrowser(link);
    	if(check<0){
    		if(!(webBrowser.openFirefox(link))){
    			return false;
    		}
    	}
    	return true;
    }
}
