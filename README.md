acpi_call_GUI_Fedora
====================
This is a java program that provides a graphic interface that allows the user to:

install the acpi_call kernel module (kernel would be recompiled automatically after each update);

Deactivate discrete GPU;

Automatically deactivate discrete GPU on every boot.

******************************************************************************************************************
This is the Fedora version, needs to be installed firefox.

Ubuntu user? See there: https://github.com/marcoDallas/acpi_call_GUI
******************************************************************************************************************

To install:
after downloading the program, enter the directory acpi_call_GUI_Fedora and launch `install.sh` . 
if you have the program in your home directory (for example typing <code> sudo git clone https://github.com/marcoDallas/acpi_call_GUI_Fedora.git </code>), you only need to type in terminal:

<code> cd acpi_call_GUI_Fedora </code>

<code> sudo chmod +x install.sh </code>

<code> sudo ./install.sh </code>
****************
if you have already made rc.local, make sure that it has ' exit 0 ' at his end.
****************

To execute:

type in a terminal: 

<code> sudo java -jar /usr/local/bin/acpi_call_GUI_Fedora/acpi_call_GUI_Fedora.jar </code>

See there for a video guide (the commands are different but the usage of the program is the same): https://www.youtube.com/watch?v=h33bvoR14x8

Note:

acpi_call module should be used with caution. I reccomend you to take a look to the readme of the acpi_call module: https://github.com/mkottman/acpi_call

Copyright:

  Copyright (C) 2013: Marco Dalla Libera 
  
  acpi_call_GUI_Fedora is free software; you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation; either version 3 of the License, or
  (at your option) any later version.
  
  acpi_call_GUI_Fedora is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  
  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
  MA 02110-1301, USA.
  
  and:
  
  Copyright (c) 2010: Michal Kottman
  
  acpi_call is free software: you can redistribute it and/or modify 
  it under the terms of the GNU General Public License as published by 
  the Free Software Foundation, either version 3 of the License, or 
  (at your option) any later version.
 
  acpi_call is distributed in the hope that it will be useful, 
  but WITHOUT ANY WARRANTY; without even the implied warranty of 
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
  See the GNU General Public License for more details.
