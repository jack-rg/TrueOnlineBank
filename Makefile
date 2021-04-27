all: build run clean

build:
	javac *.java

run:
	java BankATM

clean:
	rm ./GUI/*.class
	rm ./Objects/*.class
	rm ./Util/*.class
	rm *.class