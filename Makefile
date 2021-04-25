all: build run clean

build:
	javac *.java

run:
	java Bank

clean:
	rm *.class