package org.h3rmesk1t.SnakeYaml;

import org.yaml.snakeyaml.Yaml;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/1 1:11 上午
 */
public class SerDemo {

    public static void main(String[] args) {

        Demo name = new Demo();
        name.setName("h3rmesk1t");
        Yaml yaml = new Yaml();
        String yamlString = yaml.dump(name);
        System.out.println(yamlString);
    }
}
