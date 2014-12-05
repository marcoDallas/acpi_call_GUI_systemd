/* 
 * Acpi_call_GUI_systemd.java
 * 
 * Copyright (C) 2013,2014: Marco Dalla Libera 
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * this program allow the user to manage the acpi_call module 
 * @author Marco Dalla Libera `marcoDallas`
 */
public class Acpi_call_GUI_systemd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(hasRootAccess()==false){
            JOptionPane.showMessageDialog(null,"This program must be run as root!","Exiting program...",JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        JFrame frame=new Acpi_call_GUI_systemdFrame();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Acpi_call_GUI 1.4");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    /**
     * checks if the current user has root access
     * @return true if the user has root privileges, false otherwise
     */
    private static boolean hasRootAccess(){
        try {
            Process p = Runtime.getRuntime().exec("id -u");
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line=reader.readLine();
            return line.equals("0");
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Acpi_call_GUI_systemd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
