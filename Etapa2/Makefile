JC = javac
JVM = java
JDOC = javadoc
JFLAGS = -g

CLASSES = \
	Ball.class \
	MyWorld.class \
	PhysicsLab.class \
	PhysicsElement.class \
	Spring.class

SOURCES = \
	Ball.java \
	MyWorld.java \
	PhysicsLab.java \
	PhysicsElement.java \
	Spring.java

MAIN = PhysicsLab

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $<

all: $(CLASSES)

doc: $(SOURCES)
	$(JDOC) -autor $(SOURCES)

run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	rm -rf *.class

mrproper:
	make clean
	rm -rf res*.csv res*.png

test:
	make clean
	make
	$(JVM) PhysicsLab 0.01 20 0.01 > resultados_e2.csv
	python2 plot2d.py -s resultados_e2.csv