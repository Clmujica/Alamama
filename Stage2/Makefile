JFLAGS = -g
JC = javac
JVM= java
FILE=config.txt
OUTPUT = output.csv
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
Stage2.java \
Central.java \
Door.java \
MagneticSensor.java \
Sensor.java \
State.java \
SwitchState.java \
Siren.java \
Window.java

MAIN = Stage2

default: classes run


classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN) $(FILE) > $(OUTPUT)
clean:
	$(RM) *.class
