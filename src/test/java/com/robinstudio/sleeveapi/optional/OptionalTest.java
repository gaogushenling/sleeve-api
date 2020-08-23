package com.robinstudio.sleeveapi.optional;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * 通过单元测试可以方便调试Java代码
 * 理解 Optional 用法
 * lambda参数设计类型包括：
 * consumer 有返回值
 * supplier 无返回值
 * runnable 既无输入，也无输出
 * function
 * predicate 返回布尔值
 * 类比 javascript 回调函数
 * 链式表达式
 */

public class OptionalTest {
    @Test
    public void testOptional() {
        Optional<String> s1 = Optional.empty();
        Optional<String> s2 = Optional.of("abc");
        Optional<String> s3 = Optional.ofNullable(null);

        s2.ifPresent(t -> {
            System.out.println(t);
        });

        // 写法同上
        s2.ifPresent(System.out::println); // 如果 s2 不为空，执行语句

        s2.orElse("def"); // 如果s2不为空，取get()值，如果为空，取orElse中的值

        s2.orElseThrow(RuntimeException::new); // s2为空，抛出异常

        s2.map(t-> t+ "ddd").ifPresent(System.out::println);  // map 仍然返回 Optional
        // s2.filter(Predicate);

    }
}
