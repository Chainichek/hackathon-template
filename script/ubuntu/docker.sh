#!/bin/bash

sudo "Deleting old Docker versions if exists..."
sudo apt-get remove docker docker-engine docker.io containerd runc

echo "Updating system..."
sudo apt-get update
echo "Installing dependencies..."
sudo apt-get install -y \
      apt-transport-https\
      ca-certificates \
      curl \
      gnupg \
      lsb-release

echo "Adding official Docker's GPG key.."
sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg

echo "Configuring repo..."
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

echo "Installing Docker..."
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

echo "Starting and enabling Docker service..."
sudo systemctl start docker
sudo systemctl enable docker

echo "Checking Docker version..."
docker --version

echo "Docker installation complete!"
