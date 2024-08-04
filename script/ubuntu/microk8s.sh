#!/bin/bash

echo "Updating system..."
sudo apt-get update
sudo apt-get upgrade -y


echo "Installing microk8s..."
sudo snap install microk8s --classic --channel=1.30

echo "Joining microk8s group..."
sudo usermod -a -G microk8s $USER
mkdir -p ~/.kube
chmod 0700 ~/.kube

echo "Awaiting microk8s to get ready..."
microk8s status --wait-ready

echo "Setting up add-ons..."
microk8s enable dns
microk8s enable ingress

echo "Setting alias for microk8s kubectl..."
echo "alias kubectl='microk8s kubectl'" >> ~/.bashrc
source ~/.bashrc

echo "Microk8s installation complete!"
while true; do
    read -p "Do you want to start microk8s? (y/n) " yn
    case $yn in
        [Yy]* ) microk8s start; break;;
        [Nn]* ) exit;;
        * ) echo "Please answer yes or no.";;
    esac
done
