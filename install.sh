#!/bin/sh
#Script for the installation of acpi_call_GUI_systemd
# Copyright (C) 2013-2017 Marco Dalla Libera 
 # 
 # acpi_call_GUI_systemd is free software; you can redistribute it and/or modify
 # it under the terms of the GNU General Public License as published by
 # the Free Software Foundation; either version 3 of the License, or
 # (at your option) any later version.
 # 
 # acpi_call_GUI_systemd is distributed in the hope that it will be useful,
 # but WITHOUT ANY WARRANTY; without even the implied warranty of
 # MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 # GNU General Public License for more details.
 # 
 # You should have received a copy of the GNU General Public License
 # along with this program; if not, write to the Free Software
 # Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 # MA 02110-1301, USA.

if [ $(id -u) != "0" ] 
then
    url="/usr/local/bin"
    echo "Copying files..."
    sudo cp -r acpi_call_GUI_systemd $url
    url="/usr/local/bin/acpi_call_GUI_systemd"
    sudo chown -R $USER $url
    sudo cp acpiCallGui.png /usr/share/pixmaps
    sudo cp acpi_call_GUI.desktop /usr/share/applications/
    sudo mkdir /usr/local/bin/acpi_call
    sudo chown $USER /usr/local/bin/acpi_call
    mkdir $url/log
    mkdir $url/codes
    touch $url/log/install_log
    touch $url/log/deactivate_log
    touch $url/log/automates_log
    touch $url/codes/off
    echo "Giving files the right permissions..."
    sudo chmod +x /usr/share/applications/acpi_call_GUI.desktop
    chmod 644 $url/log/install_log
    chmod 644 $url/log/deactivate_log
    chmod 644 $url/log/automates_log
    chmod 644 $url/codes/off
    chmod 555 $url/acpi_call_GUI_systemd.jar
    chmod 555 $url/agg_acpi_call.sh
    chmod 555 $url/automates.sh
    chmod 555 $url/deactivate.sh
    chmod 555 $url/installation.sh
    echo "Installation complete!"
else
    echo "Please don't run this script with sudo, it will ask for password when necessary: use './install.sh'"
fi
