JFLAGS = -g
JC = javac
JVM= java
FILE=config.txt
OUTPUT = output.csv
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
Stage1.java \
Door.java \
MagneticSensor.java \
Sensor.java \
State.java \
SwitchState.java \
Window.java

MAIN = Stage1.java

default: classes run


classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN) $(FILE) > $(OUTPUT)
clean:
	$(RM) *.class


