/* 
 * AboutFrame.java
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
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class AboutFrame extends JFrame implements ActionListener{
	
	private ACGSCore core;
	
	private static final int FRAME_WIDTH=550;
    private static final int FRAME_HEIGHT=250;
    
    private static final String HOME_PAGE="https://github.com/marcoDallas/acpi_call_GUI_systemd";

	/**
	 * Create the frame.
	 */
	public AboutFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setLocationRelativeTo(null);
        setIconImage(new ImageIcon("/usr/share/pixmaps/acpiCallGui.png").getImage());
		setTitle("About");
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		core=new ACGSCore();
		
		JLabel lblAcpicallguisystemd = new JLabel("acpi_call_GUI_systemd");
		lblAcpicallguisystemd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcpicallguisystemd.setFont(new Font("Dialog", Font.BOLD, 20));
		contentPane.add(lblAcpicallguisystemd, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Version: "+ACGSFrame.VERSION_ID);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Author: Marco Dalla Libera");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email: dallalibera.marco@virgilio.it");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("Copyright (C) "+ACGSFrame.COPYRIGHT_YEARS+" Marco Dalla Libera");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("License: GNU GPL version 3");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_4);
		
		JButton btnProjectHomePage = new JButton("Project's home page on Github");
		btnProjectHomePage.addActionListener(this);
		contentPane.add(btnProjectHomePage, BorderLayout.SOUTH);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Project's home page on Github")){
			core.openWebBrowser(HOME_PAGE);
		}
	}
}
