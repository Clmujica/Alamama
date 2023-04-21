JFLAGS = -g
JC = javac
JVM= java
FILE=config.txt
OUTPUT = output.csv
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
Stage3.java \
Central.java \
AePlayWave.java \
Door.java \
MagneticSensor.java \
PIR_Detector.java \
Person.java \
Sensor.java \
Siren.java \
State.java \
SwitchState.java \
Window.java

MAIN = Stage3

default: classes


classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN) $(FILE)
clean:
	$(RM) *.class
