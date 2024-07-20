#!/bin/bash

echo "Updating system..."
sudo yum update -y

echo "Installing dependencies..."
sudo yum install -y yum-utils device-mapper-persistent-data lvm2

echo "Installing Minikube..."
curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
chmod +x minikube
sudo mv minikube /usr/local/bin/

echo "Installing kubectl..."
curl -LO "https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl"
chmod +x kubectl
sudo mv kubectl /usr/local/bin/

echo "Starting Minikube..."
minikube start --driver=docker

echo "Checking Minikube and kubectl versions..."
minikube version
kubectl version --client

echo "Minikube installation complete!"