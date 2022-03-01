package org.h3rmesk1t.SnakeYaml;

import org.yaml.snakeyaml.Yaml;

/**
 * @Author: H3rmesk1t
 * @Data: 2022/3/1 1:20 上午
 */
public class UnserDemo {

    public static void main(String[] agrs) {

        String yamlString = "!!org.h3rmesk1t.SnakeYaml.Demo {name: h3rmesk1t}";
        Yaml yaml = new Yaml();
        Demo name = yaml.load(yamlString);
        System.out.println(name + " : " + name.getName());
    }
}
