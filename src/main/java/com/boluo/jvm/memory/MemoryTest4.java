package com.boluo.jvm.memory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 方法区溢出
 */
public class MemoryTest4 {

    public static void main(String[] args) {

        for (; ;) {

            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MemoryTest4.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor)(obj, method, args1, proxy) ->
                    proxy.invokeSuper(obj, args1)
            );

            System.out.println("hello 菠萝");
            enhancer.create();
        }
    }
}
