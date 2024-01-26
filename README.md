acpi_call_GUI_systemd
=====
***
This repository is now archived. As of 2024 this software should no longer be needed, so I have decided not to maintain it anymore.
***
This is a java program that provides a graphic interface that allows the user to:

 * Install the acpi_call kernel module (kernel would be recompiled automatically after each update);
 
 * Deactivate discrete GPU after unloading its driver (supports radeon, amdgpu and nouveau);
 
 * Automatically deactivate discrete GPU on every boot.

***
This version is compatible whit all linux distros that use systemd, like:
 * Ubuntu since version 16.04
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

needs to be installed Firefox: it will try to open the default web browser but if it fails
it will fall back and launch Firefox.

Ubuntu user? See here: [acpi_call_GUI](https://github.com/marcoDallas/acpi_call_GUI "acpi_call_GUI")
***

## To install:
after downloading the program, enter the directory acpi_call_GUI_systemd and launch `install.sh` . 
if you have downloaded the program in your home directory, for example by typing:

```
git clone https://github.com/marcoDallas/acpi_call_GUI_systemd.git ~/
```
then you only need to type in a terminal:

```
cd ~/acpi_call_GUI_systemd 

chmod +x install.sh 

./install.sh 
```
#### Attention - prerequisites:
Make sure you have all the packages needed to recompile the kernel! For example `gcc`, `kernel-headers`, `make` etc.
(`build-essential` and `kernel-headers` should be enough if you're using Ubuntu)

Please note that 'pkexec' is used to grant super-user access, so make sure that `polkit` is installed 
in your system.

Also Java needs to be installed, at least version 8: `openjdk-8-jre` or Oracle Java 8.
***
## To execute:

Search the program in yuor dash and click on the icon, or type in a terminal: 

```
java -jar /usr/local/bin/acpi_call_GUI_systemd/acpi_call_GUI_systemd.jar
```
It will ask for the administrator password when necessary, it may also ask for it multiple times.
Also since version 2.0 a reboot is required after installation to properly unload the discrete GPU driver.

See here for a (legacy) video guide: [Video tutorial acpi_call_GUI](https://www.youtube.com/watch?v=h33bvoR14x8 "Go to youtube")

(the installation commands are different because the video was made in ubuntu, but the usage of the program is the same)
***
## Note:
Since version 2.0 this program uses my own fork of acpi_call, which have kernel 3.17+ compatibilty and other small fixes.

acpi_call module should be used with caution. I reccomend you to take a look to the readme of the acpi_call module: https://github.com/mkottman/acpi_call
***
## To uninstall:
If you deleted the acpi_call_GUI_systemd folder in your home directory re-type the following command in a terminal:
```
git clone https://github.com/marcoDallas/acpi_call_GUI_systemd.git ~/
```
if you haven't deleted the folder - or if you just used the command above - type now the following commands:
```
cd ~/acpi_call_GUI_systemd

chmod +x uninstall.sh

sudo ./uninstall.sh
```
Finally you can delete the acpi_call_GUI_systemd folder from your home directory.
***
## Screenshots:
#### acpi_call_GUI:
![Image](https://lh3.googleusercontent.com/5Pyo03ty4cfRaggaf36HWJO8uujS8stTGPmNdCTguvciTBVRt6BukHFsaKnNTsGRuhdcIvPpz2SJoB83WohdD7fW7xELv2ZgWzU3ovohDGbHCv0wmq_fE2HaHMdFcfLNmiARH27rzKGfQZ6nJbN4E3YN1STpeO6JHZuSs6cmwFZzFJGBklTHm8_TcuJS953GW5MGOO2jRSEN56eNl1qzqi4-sXglOIUv8ixX4uXwrKgRTQVNhOggtq31Bu13CiPYycarDffsD4asvClf-SvX2UaOnRKuffrrBu0XpCj5kJpThisEDfVZntmQgKIH2QtoJbEaW7_3JNZEZIxCnWggFI1CNVuA31sZwTzei2ru5vG75dY2g1rr2uzYTn9fDB7RnVlVSUjVIRaoaXrQxzM1rIWa9V-3gRZYeI89gUOD2zNjbUllVil7Hr2uqGxefjsmk8bU-kkrOW7ezcYcIKAmfRqe1gNJpN_4kP47pkwoxtfT18IG5JVUH6sApGp1HSIKfS6GEANGsMNAy0dgwg9CvYuQu34hurQCY0AwyflGUZCDcOepEpUuvokVMDbz8xq4ZK_CdG1iJCnSI-Z_WXEPdQJrpNOThEiY0PjBmH2AtyoT9qESDinQ=w630-h330-no "acpi_call_GUI")
***
#### Since version 1.4 you can try to automatically find a deactivation code!
![Image](https://lh6.googleusercontent.com/-xudmJqs6jKA/VIGiHnrHR_I/AAAAAAAAJfA/PVUCJYQcuVE/w644-h347-no/Schermata.png "Since version 1.4 you can try to automatically find a deactivation code!")
***
#### Here you can read eventual outputs or errors:
![Image](https://lh5.googleusercontent.com/-i8Q-6UxOSuk/UY-sz6OPtMI/AAAAAAAAA_Q/uterDsLyy2Q/w636-h335-no/Schermata+del+2013-05-12.png "here you can read eventual outputs or errors")
***
#### Copy here the 'Working ACPI handle OFF' code relative to your computer:
![Image](https://lh6.googleusercontent.com/-FuXDqo1CP64/UY-s2FpM2YI/AAAAAAAAA_Y/SPhWcoDQ1Gk/w882-h504-no/Schermata+del+2013-05-08+02%253A49%253A19.png "copy here the 'Working ACPI handle OFF' code relative to your computer")
***
## Copyright:

  Copyright (C) 2013-2017: Marco Dalla Libera 
  
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
  
  Copyright (c) 2010: Michal Kottman for acpi_call module, which is used by this program.
