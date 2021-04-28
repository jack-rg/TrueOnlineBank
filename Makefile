all: build run clean

build:
	javac *.java

run:
	java BankATM

clean:
	rm ./GUI/*.class
	rm ./Interfaces/*.class
	rm ./Objects/*.class
	rm ./Types/*.class
	rm ./Util/*.class
	rm *.class