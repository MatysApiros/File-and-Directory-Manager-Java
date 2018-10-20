all:			Client.class Server.class \
			Manager.class AdditionInterface.class

Manager.class:		Manager.java AdditionInterface.class
			@javac Manager.java

AdditionInterface.class:	AdditionInterface.java
			@javac AdditionInterface.java

Client.class:	Client.java
			@javac Client.java

Server.class:	Server.java
			@javac Server.java

clean:
			@rm -f *.class *~

