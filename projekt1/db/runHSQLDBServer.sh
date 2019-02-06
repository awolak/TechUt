#!/bin/bash

java -cp ~/repo/TechUt/zad02/db/hsql-2.4.0.jar org.hsqldb.server.Server --database.0 mem:mydb --dbname.0 workdb

