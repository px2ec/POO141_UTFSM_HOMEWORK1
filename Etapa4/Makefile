JC = javac
JVM = java
JDOC = javadoc
JFLAGS = -g

CLASSES = \
	Ball.class \
	MyWorld.class \
	PhysicsLab.class \
	PhysicsElement.class \
	Spring.class \
	FixedHook.class \
	SpringAttachable.class

SOURCES = \
	Ball.java \
	MyWorld.java \
	PhysicsLab.java \
	PhysicsElement.java \
	Spring.java \
	FixedHook.java \
	SpringAttachable.java

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
	$(JVM) PhysicsLab 0.01 40 0.01 > resultados_e4.csv
	python2 plot2d.py -s resultados_e4.csv