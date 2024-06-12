# GotoCounter
## Jimple Goto Statement Counter
This project is a tool for counting the number of goto statements in Jimple files. Jimple is an intermediate representation language used for representing Java bytecode, often used for static analysis and optimization.
## Features
- Convert class files to Jimple: Convert Java class files to Jimple files, allowing for analysis and counting of goto statements.
* Counting goto statements: Traverse the statements in the Jimple file and count the number of goto statements.
+ Output results: Print the calculated number of goto statements to the console
## Usage
1. Convert class files to Jimple:
   - Place the target Java class files in the GotoCounter/src/main/java/org/example/jimple/org/example/jimpleClass directory.
   - Navigate to the src directory of the project in your command-line interface
   - Run the conversion command to convert the class files to Jimple. 
`java -cp ./soot-4.5.0-jar-with-dependencies.jar soot.Main -cp ./soot-4.5.0-jar-with-dependencies.jar -pp -process-dir ./jimple/org/example/jimpleClass -f J`
2. Count goto statements:
   - Navigate to the directory where the GotoJimpleCounter1.java file is located
   - Compile the GotoJimpleCounter1.java file using the Java compiler
   - Run the GotoJimpleCounter1 class file to count the goto statements
   - The program will parse the converted Jimple files and calculate the number of goto statements.
   - The result will be displayed on the console
## System Requirements
- Java 8 or higher
- Compiler and build tool (such as Maven or Gradle) for your operating system

  
