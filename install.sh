#!/bin/sh
#Script for the installation of acpi_call_GUI
 # Copyright (C) 2013: Marco Dalla Libera 
 # 
 # acpi_call_GUI_Fedora is free software; you can redistribute it and/or modify
 # it under the terms of the GNU General Public License as published by
 # the Free Software Foundation; either version 3 of the License, or
 # (at your option) any later version.
 # 
 # acpi_call_GUI_Fedora is distributed in the hope that it will be useful,
 # but WITHOUT ANY WARRANTY; without even the implied warranty of
 # MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 # GNU General Public License for more details.
 # 
 # You should have received a copy of the GNU General Public License
 # along with this program; if not, write to the Free Software
 # Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 # MA 02110-1301, USA.
  
 # and:
 # 
 # Copyright (c) 2010: Michal Kottman
 # 
 # acpi_call is free software: you can redistribute it and/or modify 
 # it under the terms of the GNU General Public License as published by 
 # the Free Software Foundation, either version 3 of the License, or 
 # (at your option) any later version.
 # 
 # acpi_call is distributed in the hope that it will be useful, 
 # but WITHOUT ANY WARRANTY; without even the implied warranty of 
 # MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 # See the GNU General Public License for more details.

url="/usr/local/bin"
echo "Copying files..."
cp -r acpi_call_GUI_Fedora $url
mkdir $url/acpi_call_GUI_Fedora/log
mkdir $url/acpi_call_GUI_Fedora/codes
touch $url/acpi_call_GUI_Fedora/log/install_log
touch $url/acpi_call_GUI_Fedora/log/deactivate_log
touch $url/acpi_call_GUI_Fedora/log/automates_log
touch $url/acpi_call_GUI_Fedora/codes/off
echo "Giving files the execute permission..."
chmod 777 $url/acpi_call_GUI_Fedora/log/install_log
chmod 777 $url/acpi_call_GUI_Fedora/log/deactivate_log
chmod 777 $url/acpi_call_GUI_Fedora/log/automates_log
chmod 777 $url/acpi_call_GUI_Fedora/codes/off
chmod +x -R $url/acpi_call_GUI_Fedora
echo "Creating link of the java program to the user's home directory"
ln -f /usr/local/bin/acpi_call_GUI_Fedora/acpi_call_GUI_Fedora.jar $HOME
echo "Installing prerequisites..."
yum install git kernel-headers kernel-devel gcc  
echo "Installation complete"
