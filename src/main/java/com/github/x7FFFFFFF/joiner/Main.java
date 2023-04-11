package com.github.x7FFFFFFF.joiner;

import com.github.x7FFFFFFF.joiner.antlr4.SQLiteLexer;
import com.github.x7FFFFFFF.joiner.antlr4.SQLiteParser;
import com.github.x7FFFFFFF.joiner.antlr4.SQLiteParserListener;
import com.github.x7FFFFFFF.joiner.misc.ParserListenerInvocationHandler;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {

    public static void main(String[] args) {
        CharStream charStream = CharStreams.fromString("SELECT * FROM tbl1");
        SQLiteLexer lexer = new SQLiteLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SQLiteParser parser = new SQLiteParser(tokens);
        final SQLiteParser.ParseContext context = parser.parse();

        ParseTreeWalker walker = new ParseTreeWalker();
        SQLiteParserListener listener = ParserListenerInvocationHandler.create();

        walker.walk(listener, context);
    }
}
