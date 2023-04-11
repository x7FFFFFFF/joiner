package com.github.x7FFFFFFF.joiner.misc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import com.github.x7FFFFFFF.joiner.antlr4.SQLiteParserListener;

public class ParserListenerInvocationHandler implements InvocationHandler {
    private int tab;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final String name = method.getName();
        System.out.println(getTab(name)+ "method = " + name + "args = " + Arrays.toString(args));
        return null;
    }

    private String getTab(String method) {
        final String start = method.substring(0, 4);
        return switch (start) {
            case "ente" -> "\t".repeat(++tab);
            case "exit" ->  "\t".repeat(--tab);
            default ->  "\t".repeat(tab);
        };
    }


    public static SQLiteParserListener create() {
        return (SQLiteParserListener) Proxy.newProxyInstance(
                ParserListenerInvocationHandler.class.getClassLoader(),
                new Class[]{SQLiteParserListener.class},
                new ParserListenerInvocationHandler());
    }
}
