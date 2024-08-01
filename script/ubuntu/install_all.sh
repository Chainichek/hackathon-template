#!/bin/bash

set -e

# Установка Network Manager
echo "Установка Network Manager..."
sudo apt-get update
sudo apt-get install -y network-manager

# Проверка успешного завершения установки Network Manager
if [ $? -ne 0 ]; then
  echo "Ошибка при установке Network Manager"
  exit 1
fi

# Запуск скрипта для установки Docker
echo "Запуск скрипта для установки Docker..."
./docker.sh

# Проверка успешного завершения скрипта для установки Docker
if [ $? -ne 0 ]; then
  echo "Ошибка при выполнении скрипта для установки Docker"
  exit 1
fi

# Запуск скрипта для установки MicroK8s
echo "Запуск скрипта для установки MicroK8s..."
./microk8s.sh

# Проверка успешного завершения скрипта для установки MicroK8s
if [ $? -ne 0 ]; then
  echo "Ошибка при выполнении скрипта для установки MicroK8s"
  exit 1
fi

echo "Все скрипты выполнены успешно"
