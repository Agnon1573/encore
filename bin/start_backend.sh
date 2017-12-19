#!/usr/bin/env bash
docker pull registry.gitlab.com/truelaurel/encore:master
docker-compose -p encore -f docker-compose.yml up -d