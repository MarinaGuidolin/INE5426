
SHELL := /bin/bash

example0 = "./examples/example0.ccc"

example1 = "./examples/example1.ccc"

arrays = "./examples/arrays.ccc"

factorial = "./examples/factorial.ccc"

matrix = "./examples/matrix.ccc"

exemplo = example0


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




