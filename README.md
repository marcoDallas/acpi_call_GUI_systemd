acpi_call_GUI_Fedora
=============
This is a java program that provides a graphic interface that allows the user to:

 * Install the acpi_call kernel module (kernel would be recompiled automatically after each update);
 
 * Deactivate discrete GPU;
 
 * Automatically deactivate discrete GPU on every boot.

***
This is the Fedora version, needs to be installed firefox.

Ubuntu user? See there: [acpi_call_GUI](https://github.com/marcoDallas/acpi_call_GUI "acpi_call_GUI")
***

## To install:
after downloading the program, enter the directory acpi_call_GUI_Fedora and launch `install.sh` . 
if you have the program in your home directory (for example typing `git clone https://github.com/marcoDallas/acpi_call_GUI_Fedora.git`), you only need to type in terminal:

```
cd acpi_call_GUI_Fedora 

sudo chmod +x install.sh 

sudo ./install.sh 
```
#### Attention
if you have already made rc.local, make sure that it has ' exit 0 ' at his end.
***
## To execute:

type in a terminal: 

```
sudo java -jar /usr/local/bin/acpi_call_GUI_Fedora/acpi_call_GUI_Fedora.jar
```

See there for a video guide: [Video tutorial acpi_call_GUI](https://www.youtube.com/watch?v=h33bvoR14x8 "Go to youtube")

(the installation commands are different because the video was made in ubuntu, but the usage of the program is the same)
***
## Note:

acpi_call module should be used with caution. I reccomend you to take a look to the readme of the acpi_call module: https://github.com/mkottman/acpi_call
***
## Screenshots:
#### acpi_call_GUI:
![Alt text](https://lh4.googleusercontent.com/-lTn3-VeTZoM/UYmjnhbQ0XI/AAAAAAAAA9k/8fv9l5QeNIM/w614-h314-no/Schermata+del+2013-05-08+02%253A46%253A34.png "acpi_call_GUI")
***
#### Here you can read eventual outputs or errors:
![Alt text](https://lh5.googleusercontent.com/-i8Q-6UxOSuk/UY-sz6OPtMI/AAAAAAAAA_Q/uterDsLyy2Q/w636-h335-no/Schermata+del+2013-05-12.png "here you can read eventual outputs or errors")
***
#### Copy here the 'Working ACPI handle OFF' code relative to your computer:
![Alt text](https://lh6.googleusercontent.com/-FuXDqo1CP64/UY-s2FpM2YI/AAAAAAAAA_Y/SPhWcoDQ1Gk/w882-h504-no/Schermata+del+2013-05-08+02%253A49%253A19.png "copy here the 'Working ACPI handle OFF' code relative to your computer")
***
## Copyright:

  Copyright (C) 2013: Marco Dalla Libera 
  
  acpi_call_GUI is free software; you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation; either version 3 of the License, or
  (at your option) any later version.
  
  acpi_call_GUI is distributed in the hope that it will be useful,
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
