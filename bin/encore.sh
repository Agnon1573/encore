#!/usr/bin/env bash

start() {
    docker pull huiwang/encore:latest
    docker-compose -p encore -f docker-compose.yml up -d
}

stop() {
    docker-compose -p encore -f docker-compose.yml down
}

($1)

