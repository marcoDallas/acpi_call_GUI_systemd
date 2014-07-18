acpi_call_GUI_systemd
=============
#### Wanna help me and the project?
**[Here's the way to make a donation](http://marcodallas.github.io/donation.html), thank you.**


***
This is a java program that provides a graphic interface that allows the user to:

 * Install the acpi_call kernel module (kernel would be recompiled automatically after each update);
 
 * Deactivate discrete GPU;
 
 * Automatically deactivate discrete GPU on every boot.

***
This version is compatible whit all linux distros that use systemd, like:
 * Fedora since version 15
 * Frugalware 1.5+
 * Mageia 2+
 * Mandriva 2011+
 * mer since 2012
 * openSUSE 12.1+
 * Arch Linux since October 2012
 * Chakra Linux since October 2012
 * Parabola GNU/Linux since October 2012
 * NixOS since January 2013
 * Red Flag Linux 8+

needs to be installed firefox.

Ubuntu user? See here: [acpi_call_GUI](https://github.com/marcoDallas/acpi_call_GUI "acpi_call_GUI")
***

## To install:
after downloading the program, enter the directory acpi_call_GUI_systemd and launch `install.sh` . 
if you have the program in your home directory (for example typing `git clone https://github.com/marcoDallas/acpi_call_GUI_systemd.git`), you only need to type in terminal:

```
cd acpi_call_GUI_systemd 

sudo chmod +x install.sh 

sudo ./install.sh 
```
#### Attention
Make sure you have all the packages needed to recompile the kernel! For example gcc, kernel-headers, kernel-devel etc.
***
## To execute:

Search the program in yuor dash and click on the icon, or type in a terminal: 

```
sudo java -jar /usr/local/bin/acpi_call_GUI_systemd/acpi_call_GUI_systemd.jar
```

See here for a video guide: [Video tutorial acpi_call_GUI](https://www.youtube.com/watch?v=h33bvoR14x8 "Go to youtube")

(the installation commands are different because the video was made in ubuntu, but the usage of the program is the same)
***
## Note:

acpi_call module should be used with caution. I reccomend you to take a look to the readme of the acpi_call module: https://github.com/mkottman/acpi_call
***
## Screenshots:
#### acpi_call_GUI:
![Alt text](https://lh3.googleusercontent.com/G2y4wAd3FllQWEXfaMKBgKhYFB404NgYdrLotzUMET4=w614-h314-no "acpi_call_GUI")
***
#### Here you can read eventual outputs or errors:
![Alt text](https://lh5.googleusercontent.com/-i8Q-6UxOSuk/UY-sz6OPtMI/AAAAAAAAA_Q/uterDsLyy2Q/w636-h335-no/Schermata+del+2013-05-12.png "here you can read eventual outputs or errors")
***
#### Copy here the 'Working ACPI handle OFF' code relative to your computer:
![Alt text](https://lh6.googleusercontent.com/-FuXDqo1CP64/UY-s2FpM2YI/AAAAAAAAA_Y/SPhWcoDQ1Gk/w882-h504-no/Schermata+del+2013-05-08+02%253A49%253A19.png "copy here the 'Working ACPI handle OFF' code relative to your computer")
***
## Copyright:

  Copyright (C) 2013,2014: Marco Dalla Libera 
  
  acpi_call_GUI_systemd is free software; you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation; either version 3 of the License, or
  (at your option) any later version.
  
  acpi_call_GUI_systemd is distributed in the hope that it will be useful,
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
