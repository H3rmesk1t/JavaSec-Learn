package org.h3rmesk1t.BeanShell;

import bsh.Interpreter;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/16 12:18 上午
 */
public class Demo {

    public static void main(String[] args) throws Exception {

        String payload = "exec(exp)";
        String func = "exec(Object cmd) {java.lang.Runtime.getRuntime().exec(cmd);}";
        Interpreter interpreter = new Interpreter();
        interpreter.eval(func);
        interpreter.set("exp", "open -a Calculator");
        interpreter.eval(payload);
    }
}
