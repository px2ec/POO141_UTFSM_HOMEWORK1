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

test:
	make clean
	make
	$(JVM) PhysicsLab 0.1 40 0.01 > resultados.csv
	python2 ../utils/plot2d.py resultados.csv