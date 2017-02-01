#!/bin/sh
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
git clone https://github.com/marcoDallas/acpi_call.git /usr/local/bin/acpi_call 
cd /usr/local/bin/acpi_call 
make
pkexec sh -c "insmod /usr/local/bin/acpi_call/acpi_call.ko ; chmod 644 /proc/acpi/call ; cp $url/agg_acpi_call.service /etc/systemd/system/ ; systemctl -q enable agg_acpi_call.service ; echo 'disabling GPU drivers, please note that if necessary uninstall.sh will also re-enable them' ; cp $url/noGPUDriver.conf /etc/modprobe.d/"
#modprobe acpi_call
#pkexec chmod 644 /proc/acpi/call #root
chmod +x $url/agg_acpi_call.sh
uname -r > $url/kernel.txt
#pkexec cp $url/agg_acpi_call.service /etc/systemd/system/ #root
#pkexec systemctl -q enable agg_acpi_call.service #root
