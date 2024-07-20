#!/bin/bash

echo "Updating system..."
sudo yum update -y

echo "Installing dependencies..."
sudo yum install -y yum-utils

echo "Adding Docker repository..."
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

echo "Installing Docker..."
sudo yum install -y docker-ce docker-ce-cli containerd.io

echo "Starting and enabling Docker service..."
sudo systemctl start docker
sudo systemctl enable docker

echo "Checking Docker version..."
docker --version

echo "Docker installation complete!"