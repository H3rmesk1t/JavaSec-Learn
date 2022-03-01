package URLClassLoaderDemo;

import java.lang.reflect.Method;

import java.lang.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Base64;

/**
 * @Author: H3rmesk1t
 * @Data: 2021/11/29 5:05 下午
 */
public class DefineClassLoader {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
        defineClass.setAccessible(true);
        byte[] code = Base64.getDecoder().decode("yv66vgAAADQAHAoABgAPCgAQABEIABIKABAAEwcAFAcAFQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAApFeGNlcHRpb25zBwAWAQAKU291cmNlRmlsZQEACUV2aWwuamF2YQwABwAIBwAXDAAYABkBACtvcGVuIC1hIC9TeXN0ZW0vQXBwbGljYXRpb25zL0NhbGN1bGF0b3IuYXBwDAAaABsBAARFdmlsAQAQamF2YS9sYW5nL09iamVjdAEAE2phdmEvaW8vSU9FeGNlcHRpb24BABFqYXZhL2xhbmcvUnVudGltZQEACmdldFJ1bnRpbWUBABUoKUxqYXZhL2xhbmcvUnVudGltZTsBAARleGVjAQAnKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL1Byb2Nlc3M7ACEABQAGAAAAAAABAAEABwAIAAIACQAAAC4AAgABAAAADiq3AAG4AAISA7YABFexAAAAAQAKAAAADgADAAAABwAEAAgADQAJAAsAAAAEAAEADAABAA0AAAACAA4");
        Class Evil = (Class)defineClass.invoke(ClassLoader.getSystemClassLoader(), "Evil", code, 0, code.length);
        Evil.newInstance();
    }
}
