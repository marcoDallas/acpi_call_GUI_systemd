#!/bin/sh
# Copyright (C) 2013-2017 Marco Dalla Libera
# part of acpi_call_GUI_systemd

#blacklists GPU drivers
echo "disabling GPU drivers..."
pkexec cp $url/noGPUDriver.conf /etc/modprobe.d/ #root
