#!/usr/bin/env bash

start() {
    docker pull registry.gitlab.com/truelaurel/encore:master
    docker-compose -p encore -f docker-compose.yml up -d
}

stop() {
    docker-compose -p encore -f docker-compose.yml down
}

($1)

