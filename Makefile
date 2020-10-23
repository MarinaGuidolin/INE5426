SHELL := /bin/bash

exemplo0 = "./examples/example0.ccc"

exemplo1 = "./examples/example1.ccc"

arrays = "./examples/arrays.ccc"

factorial = "./examples/factorial.ccc"

matrix = "./examples/matrix.ccc"

filepath = "./examples/example0.ccc"

sort = "./examples/sort.ccc"

exemplo = filepath


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
	mvn exec:java -Dexec.args=$($(exemplo))





