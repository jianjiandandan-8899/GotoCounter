package org.example;

import jas.CP;
import soot.*;
import soot.jimple.*;
import soot.options.Options;


public class GotoStatementCounter {
    public static void main(String[] args) {
        String className = "classname for counting go to";
        String classpath = System.getProperty("java.class.path");
        System.out.println("-----" + classpath);
        String cp = classpath.replace("/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar","");
        Scene.v().setSootClassPath(cp+ ":./target/classes/org/example:/Users/jane/.sdkman/candidates/java/current/jre/lib/rt.jar:/Users/jane/.sdkman/candidates/java/current/jre/lib/jce.jar");
        //Options.v().set_prepend_classpath(true);
        // Set up Soot
        Options.v().setPhaseOption("jb", "use-original-names:true");
        SootClass sootClass = Scene.v().loadClassAndSupport(className);
        sootClass.setApplicationClass();
        Scene.v().loadNecessaryClasses();

        int gotoForMethod = 0;

        // Perform analysis
        int gotoStatementCount = 0;
        System.out.println("====================== Class: "+sootClass.getName() + "======================");
        for (SootMethod method : sootClass.getMethods()) {
            Body body = method.retrieveActiveBody();
            gotoForMethod = 0;
            for (Unit unit : body.getUnits()) {
                Stmt stmt = (Stmt) unit;
                if (stmt instanceof GotoStmt || stmt instanceof IfStmt ||
                        stmt instanceof TableSwitchStmt || stmt instanceof LookupSwitchStmt) {
                    gotoForMethod++;
                    gotoStatementCount++;
                    System.out.println("Found goto statement in method " + method.getName() +" : " + unit);
                }

            }
            System.out.println("Method: " + method.getName() + ", Goto number: " + gotoForMethod);

        }

        // Print the result
        System.out.println("Number of Goto statements: " + gotoStatementCount);
    }

}
