package org.h3rmesk1t.Jython;

import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.python.util.jythonTest;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/16 10:37 上午
 */
public class Demo {

    public static void execfileFunc() throws Exception {

        String filePath = jythonTest.class.getClassLoader().getResource("test.py").getPath();
        System.out.println(filePath);
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile(filePath);
    }

    public static void execFunc() throws Exception {

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("import os\nos.system(\"open -a Calculator\")\n");
    }

    public static void evalFunc() throws Exception {

        PythonInterpreter interpreter = new PythonInterpreter();
        PyObject pyObject= interpreter.eval("1+1");
        System.out.println(pyObject);
    }

    public static void selfFunc() throws Exception {

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("import os\ndef add(m, n):\n   return m+n\n");
        PyFunction function = (PyFunction) interpreter.get("add");
        PyObject object = function.__call__(new PyObject[]{new PyString("Hello, "), new PyString("h3rmesk1t!")});
        System.out.println(object);
    }

    public static void main(String[] args) throws Exception {

        // execfileFunc();
        execFunc();
        evalFunc();
        selfFunc();
    }
}
