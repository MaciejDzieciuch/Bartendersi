#!/bin/bash
docker run -p 0.0.0.0:3308:3306 --name bartenders -e MYSQL_ROOT_PASSWORD=zaq1@WSX -e MYSQL_DATABASE=bartendersDB -d mysql
