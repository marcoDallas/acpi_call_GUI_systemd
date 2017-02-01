#!/bin/sh
#Script that remove acpi_call_GUI_systemd, acpi_call, and all of the scripts.
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
 
rm -R /usr/local/bin/acpi_call
rm -R /usr/local/bin/acpi_call_GUI_systemd
rm /usr/share/pixmaps/acpiCallGui.png
rm /usr/share/applications/acpi_call_GUI.desktop
systemctl disable automates.service
systemctl disable agg_acpi_call.service
rm /etc/systemd/system/automates.service
rm /etc/systemd/system/agg_acpi_call.service

#enabling GPU drivers again
echo "enabling GPU drivers..."
rm /etc/modprobe.d/noGPUDriver.conf
echo "uninstall complete!"
