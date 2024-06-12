package org.example;

import soot.*;
import soot.jimple.*;
import soot.options.Options;

import java.util.Arrays;

public class AutoGenerateGoto {
    public static void main(String[] args) {
        // 设置 Soot 的选项
        Options.v().set_prepend_classpath(true);
        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().set_src_prec(Options.src_prec_jimple);

        // 创建一个简单的方法
        SootMethod method = new SootMethod("myMethod", Arrays.asList(), VoidType.v());
        Body body = Jimple.v().newBody(method);
        method.setActiveBody(body);

        // 创建 Jimple 语句
        Local x = Jimple.v().newLocal("x", IntType.v());
        body.getLocals().add(x);

        AssignStmt assignStmt = Jimple.v().newAssignStmt(x, IntConstant.v(0));
        GotoStmt gotoStmt1 = Jimple.v().newGotoStmt(assignStmt);
        GotoStmt gotoStmt2 = Jimple.v().newGotoStmt(gotoStmt1);

        body.getUnits().add(assignStmt);
        body.getUnits().add(gotoStmt1);
        body.getUnits().add(gotoStmt2);

        // 打印生成的 Jimple 代码
        System.out.println(body.toString());
        // Perform analysis
        /*int gotoStatementCount = 0;
        for (Unit unit : body.getUnits()) {
                Stmt stmt = (Stmt) unit;
                if (stmt instanceof GotoStmt) {
                    gotoStatementCount++;
                }
            }
        System.out.println(gotoStatementCount);*/
    }
}
