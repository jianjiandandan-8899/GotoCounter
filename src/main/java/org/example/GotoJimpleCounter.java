package org.example;

import soot.*;
import soot.jimple.GotoStmt;

import soot.options.Options;


import java.util.Arrays;


public class GotoJimpleCounter {
    public static void main(String[] args) {

        //根据需要解析的class文件设置classpath
        String classpath = System.getProperty("java.class.path");
        System.out.println("-----" + classpath);
        String cp = classpath.replace("/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar","");
        Scene.v().setSootClassPath(cp + ":./src/main/java/org/example/jimple/org/example/jimpleClass:target/classes:/Users/jane/.sdkman/candidates/java/current/jre/lib/rt.jar:/Users/jane/.sdkman/candidates/java/current/jre/lib/jce.jar");

        // 设置 Soot 的选项
        Options.v().set_prepend_classpath(true);
        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().set_process_dir(Arrays.asList("/Users/jane/Desktop/git-project/counter/src/main/java/org/example/sootOutput"));
        Options.v().set_keep_line_number(true);
        Options.v().set_src_prec(Options.src_prec_jimple);



        // 解析 Jimple 文件
        Scene.v().loadNecessaryClasses();

        // 遍历所有类
        for (SootClass sootClass : Scene.v().getApplicationClasses()) {
            for (SootMethod method : sootClass.getMethods()) {
                if (!method.isConcrete()) {
                    continue;
                }

                // 获取方法的 Jimple 体
                Body body = method.retrieveActiveBody();

                // 计数 goto 语句
                int gotoCount = 0;
                for (Unit unit : body.getUnits()) {
                    if (unit instanceof GotoStmt) {
                        gotoCount++;
                    }
                }

                System.out.println("Class: " + sootClass.getName() + ", Method: " + method.getName() + ", Goto statements: " + gotoCount);
            }
        }
    }

}
