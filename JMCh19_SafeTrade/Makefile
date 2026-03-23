# Usage: just type 'make' to create a covereage report
# Open coveraege_report/index.html in browser to see results
#
# Runs code covereage using MyTests.java as the main()
SHELL := /bin/bash

# EDIT JAVA_FILES AND CLASSFILES_COVERAGE beloe for lab
# All the java files that need compiling 
JAVA_FILES := Brokerage.java Login.java LoginWindow.java PriceComparator.java  \
			  SafeTrade.java Stock.java StockExchange.java TradeOrder.java Trader.java \
			  TraderWindow.java MyTests.java \
			  MyTests_Brokerage.java MyTests_PriceComparator.java MyTests_Stock.java \
			  MyTests_StockExchange.java MyTests_TradeOrder.java
# list of the files to run coverage over - add or delete as desired
CLASSFILES_COVERAGE = Brokerage.class PriceComparator.class \
					  Stock.class StockExchange.class 

# Detect OS type
UNAME_S := $(shell uname -s)
ifeq ($(OS),Windows_NT)
	# Windows (cmd/PowerShell)
	CP_SEP := ;
else
	# Unix-like (Linux/macOS)
	CP_SEP := :
endif

# Look for lib in current dir or parent dir
LIBDIR := $(firstword $(wildcard lib) $(wildcard ../lib))

# Default target
all: run report 

compile: $(JAVA_FILES)
	javac -cp ".${CP_SEP}$(LIBDIR)/*" $(JAVA_FILES)

run: compile
	java -javaagent:$(LIBDIR)/jacocoagent.jar=destfile=coverage.exec -cp ".${CP_SEP}$(LIBDIR)/*" MyTests

report: prepare_classfiles
	java -jar $(LIBDIR)/jacococli.jar report coverage.exec \
		--classfiles $(CLASSDIR) \
		--sourcefiles . \
		--html coverage_report

# Copy only the relevant class files into CLASSDIR
CLASSDIR := classfiles
prepare_classfiles: compile
	mkdir -p $(CLASSDIR)
	cp $(CLASSFILES_COVERAGE) $(CLASSDIR)/

clean:
	rm -Rf coverage_report coverage.exec *.class handin.zip $(CLASSDIR)

