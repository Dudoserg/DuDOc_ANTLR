


import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        String str = "void main(){" + "\n" +
                "   int a = 2 + 7;" + "\n" +
                "   printf(a);" + "\n" +
                "   a = 3-1;" + "\n" +
                "   printf(a);" + "\n" +
                "   a = 3 + g;" + "\n" +
                "   int b = 2;" + "\n" +
                "   printf(b);" + "\n" +
                "   if (b <= 1) {" + "\n" +
                "       printf('true if');" + "\n" +
                "       printf(b);" + "\n" +
                "   } else {" + "\n" +
                "       printf('false if');" + "\n" +
                "   }" + "\n" +
                "}";
        System.out.println(str);
        CPPLexer lexer = new CPPLexer(CharStreams.fromString(str));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CPPParser parser = new CPPParser(tokens);
        ParseTree tree = parser.translationunit();
        new MyKek().visit(tree);
    }
}
