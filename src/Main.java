


import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
//        String str = "void main(){" + "\n" +
//                "   int a = 2 + 7;" + "\n" +
//                "   printf('a=');" + "\n" +
//                "   printf(a);" + "\n" +
//                "   a = 3-1;" + "\n" +
//                "   printf('1+2+3+4+5+6+7+8+9=');" + "\n" +
//                "   printf(1+2+3+4+5+6+7+8+9);" + "\n" +
//                "   int b = 2;" + "\n" +
//                "   printf('b=');" + "\n" +
//                "   printf(b);" + "\n" +
//                "   if (b != 2) {" + "\n" +
//                "       printf('true if');" + "\n" +
//                "       printf(b);" + "\n" +
//                "   } else {" + "\n" +
//                "       printf('false if');" + "\n" +
//                "   }" + "\n" +
//                "}";

        String str = "void main(){\n" +
                "   int b = 2 ;\n" +
                "   int c = 3;\n" +
                "   int result = 13;\n" +
                "   if (b == 2) {\n" +
                "      if (c == 4) {\n" +
                "        result = 1;\n" +
                "      } else {\n" +
                "        result = 10 / 2;\n" +
                "      } \n" +
                "   } else {\n" +
                "       \n" +
                "   }\n" +
                "   \tprintf(result);\n" +

                "}";

        System.out.println(str);
        CPPLexer lexer = new CPPLexer(CharStreams.fromString(str));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CPPParser parser = new CPPParser(tokens);
        ParseTree tree = parser.translationunit();
        new MyKek().visit(tree);
    }
}
