
SHELL := /bin/bash

file = "./examples/example1.ccc"

.PHONY: clean compile package exec


all: clean package exec

install:
	apt-get update
	sudo apt install default-jdk
	sudo apt-get install maven
	
clean:
	mvn clean

compile:
	mvn compile

package:
	mvn package

exec:
	mvn exec:java -Dexec.args=$(file)
