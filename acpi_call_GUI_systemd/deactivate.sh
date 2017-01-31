#!/bin/sh
#Script launched by acpi_call_GUI_systemd, java application that mangaes acpi_call modules

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

url="/usr/local/bin/acpi_call_GUI_systemd"
pkexec insmod /usr/local/bin/acpi_call/acpi_call.ko #root
B=$(sed -n '1p' $url/codes/off)
pkexec sh -c "echo '$B' > /proc/acpi/call" #root
cat /proc/acpi/call
