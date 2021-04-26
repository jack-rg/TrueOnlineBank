all: build run clean

build:
	javac *.java

run:
	java BankATM

clean:
	rm *.class