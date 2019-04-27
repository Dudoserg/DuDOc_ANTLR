


import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        String str = "void main(){" +
                "   int a = 2 + 7;" +
                "   cout << a;" +
                "   a = 3;" +
                "   cout << a;" +
                "   g = 3;" +
                "   " +
                "   int b = 0;" +
                "   if (b < 1) {" +
                "       cout << 100 * 2;" +
                "       a = 4;" +
                "   } else {" +
                "       cout << 200 / 3;" +
                "   }" +
                "   if (a == 4) {" +
                "       cout << 111;" +
                "   }" +
                "}";

        CPPLexer lexer = new CPPLexer(CharStreams.fromString(str));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CPPParser parser = new CPPParser(tokens);
        ParseTree tree = parser.translationunit();
        new MyKek().visit(tree);
    }
}
