#!/bin/bash

echo "--- Budowanie obrazu Dockera ---"
docker build -t ebiznes-lab2 .

docker stop ebiznes-lab2 2>/dev/null
docker rm ebiznes-lab2 2>/dev/null

echo "--- Uruchamianie kontenera ---"
docker run -d -p 9000:9000 --name ebiznes-container ebiznes-lab2

echo "--- Start NGROK ---"
ngrok http 9000