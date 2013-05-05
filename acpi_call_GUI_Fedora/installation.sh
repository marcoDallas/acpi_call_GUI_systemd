#!/bin/sh
#Script launched by acpi_call_GUI_Fedora, java application that mangaes acpi_call modules

 # Copyright (C) 2013 Marco Dalla Libera 
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

url="/usr/local/bin/acpi_call_GUI_Fedora"
git clone http://github.com/mkottman/acpi_call.git /usr/local/bin/acpi_call 
cd /usr/local/bin/acpi_call 
make >> $url/log/install_log
insmod acpi_call.ko 
modprobe acpi_call
chmod +x $url/agg_acpi_call.sh 
chmod +x /etc/rc.d/rc.local
#Modifing the rc.local file:
uname -r > $url/kernel.txt 
C=1 
D="$url/agg_acpi_call.sh"
while read line
do
    if [ "$line" = "exit 0" ] 
    then
        break
    elif [ "$line" = "$url/agg_acpi_call.sh" ]
    then 
        break
    fi
    C=$(($C+1))
done < /etc/rc.d/rc.local
sed -i "$C i $D" /etc/rc.d/rc.local
