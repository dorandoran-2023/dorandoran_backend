#!/usr/bin/env bash

if [ -d /home/ubuntu/app/ ]; then
    sudo rm -rf /home/ubuntu/backend/
fi
sudo mkdir -vp /home/ubuntu/backend/
sudo chown -R ubuntu:ubuntu /home/ubuntu/backend
sudo chmod -R ugo+rwx /home/ubuntu/backend
