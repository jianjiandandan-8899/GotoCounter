package org.example;

import soot.*;
import soot.jimple.GotoStmt;
import soot.jimple.IfStmt;
import soot.jimple.LookupSwitchStmt;
import soot.jimple.TableSwitchStmt;
import soot.options.Options;
import soot.util.Chain;

import java.util.Arrays;


public class GotoJimpleCounter1 {
    public static void main(String[] args) {

        //根据需要解析的class文件设置classpath
        String classpath = System.getProperty("java.class.path");
        System.out.println("-----" + classpath);
        String cp = classpath.replace("/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar","");
        Scene.v().setSootClassPath(cp + ":./src/main/java/org/example/jimple/org/example/jimpleClass:target/classes:/Users/jane/.sdkman/candidates/java/current/jre/lib/rt.jar:/Users/jane/.sdkman/candidates/java/current/jre/lib/jce.jar");

        // 设置 Soot 的选项
        Options.v().set_prepend_classpath(true);
        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().set_process_dir(Arrays.asList("your jimple file class"));
        Options.v().set_keep_line_number(true);
        Options.v().set_src_prec(Options.src_prec_jimple);



        // 解析 Jimple 文件
        Scene.v().loadNecessaryClasses();

        // 获取应用程序类
        Chain<SootClass> applicationClasses = Scene.v().getApplicationClasses();

        int totalGotoCount = 0;
        int gotoCountForClass = 0;
        int gotoCountForMethod=0;

        // 遍历所有的类
        for (SootClass sootClass : applicationClasses) {
            System.out.println("===========================Class: " + sootClass.getName() +"===========================");
            gotoCountForClass = 0;

            // 遍历类中的所有方法
            for (SootMethod method : sootClass.getMethods()) {
                gotoCountForMethod =0;
                if (!method.isConcrete()) {
                    continue;
                }
                // 获取方法的活动体
                Body body = method.retrieveActiveBody();
                UnitPatchingChain units = body.getUnits();

                // 遍历方法体中的所有语句
                for (Unit unit : units) {
                    if (unit instanceof GotoStmt || unit instanceof IfStmt ||
                            unit instanceof TableSwitchStmt || unit instanceof LookupSwitchStmt) {
                        gotoCountForMethod++;
                        gotoCountForClass++;
                        System.out.println("    Found goto statement in method " + method.getName() +" : " + unit);
                    }
                }

                System.out.println("Method: " + method.getName() + ", Goto number: " + gotoCountForMethod);
            }
            System.out.println("Class: " + sootClass.getName() + ", total Goto number: " + gotoCountForClass +"\n");

        }

        totalGotoCount += gotoCountForClass;
        // 输出总的goto结果
        System.out.println("Total number of goto statements: " + totalGotoCount);


    }

}
