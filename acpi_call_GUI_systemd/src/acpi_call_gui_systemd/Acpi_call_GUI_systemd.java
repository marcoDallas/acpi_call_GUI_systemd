/* 
 * Acpi_call_GUI_systemd.java
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

import javax.swing.JOptionPane;

import rootutils.RootUtils;

/**
 * this program allow the user to manage the acpi_call module 
 * @author Marco Dalla Libera `marcoDallas`
 */
public class Acpi_call_GUI_systemd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	//RootUtils is a class made by me and it's closed source.
    	RootUtils checker = new RootUtils();
    	if(checker.hasRootAcces()){
            JOptionPane.showMessageDialog(null,"This program no longer needs to be run as root,\nplease run it as normal user: it will ask for password when necessary","Exiting program...",JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        @SuppressWarnings("unused")
		ACGSFrame frame = new ACGSFrame();
    }
}
