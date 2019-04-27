import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MyKek extends CPPBaseVisitor<Elem>  {
    public static boolean DEBUG = false;

    private Map<String, Elem> variables = new HashMap<String, Elem>();

    @Override
    public Elem visitSimpledeclaration(CPPParser.SimpledeclarationContext ctx) {

        String type = ctx.getChild(0).getText();
        if( DEBUG ){
            for(int i = 0 ; i < ctx.children.size(); i++){
                String tmp = ctx.getChild(i).getText();
                System.out.println(tmp);
            }
            System.out.println("#########################");
        }
        return super.visitSimpledeclaration(ctx);
    }

    // a = 2;
    @Override
    public Elem visitAssignmentexpression(CPPParser.AssignmentexpressionContext ctx) {
        if( DEBUG ){
            if( ctx.getChildCount() > 0){
                System.out.println("visitAssignmentexpression");
                for(int i = 0 ; i < ctx.children.size(); i++){
                    String tmp = ctx.getChild(i).getText();
                    System.out.println(tmp);
                }
                if( ctx.getChildCount() == 3 && ctx.getChild(1).getText().equals("=")){
                    System.out.println("вот и присваивание подъехало");
                }
                System.out.println("#########################");
            }
        }
        if( ctx.getChildCount() == 3 && ctx.getChild(1).getText().equals("=")){
            String str = ctx.getChild(0).getText();
            if(this.variables.get(str) != null){
                Elem elem = super.visit(ctx.getChild(2));
                this.variables.put(str, elem);
            }else {
                System.out.println("переменная '"+ str + "' не определена, присваивание невозможно!");

            }
        }

        return super.visitAssignmentexpression(ctx);
    }

    //int a =
    @Override
    public Elem visitInitdeclarator(CPPParser.InitdeclaratorContext ctx) {
        String value = ")))";
        if( DEBUG ){
            for(int i = 0 ; i < ctx.children.size(); i++){
                String tmp = ctx.getChild(i).getText();
                System.out.println(tmp);
            }
            System.out.println("#########################");
        }
        Elem elem = null;
        if(ctx.getChildCount() > 1){
             elem = super.visit(ctx.getChild(1));
             value = elem.getText();
        }
        String name = ctx.getChild(0).getText();
        if(this.variables.get(name) == null){
            // такой переменной еще не было
            Elem tmp = new Elem(elem.getTypeLexeme(), elem.getText());
            this.variables.put(name, tmp);
        }else{
            System.out.println("переменная '" + name + "' уже объявлена");
        }
        return super.visitInitdeclarator(ctx);
    }


    // main print...
    @Override
    public Elem visitPostfixexpression(CPPParser.PostfixexpressionContext ctx) {
        if( DEBUG ) {
            System.out.println("visitPostfixexpression");
            for (int i = 0; i < ctx.children.size(); i++) {
                String tmp = ctx.getChild(i).getText();
                System.out.println(tmp);
            }
            System.out.println("#########################");
        }
        if( ctx.getChildCount() > 1 && ctx.getChild(0).getText().equals("printf")){
            Elem elem = super.visit(ctx.getChild(2));
            System.out.println("printf = " + elem.getText() + "//////////////////////////////////////////////");
        }

        return super.visitPostfixexpression(ctx);
    }

    // переменная из мапы
    @Override
    public Elem visitUnqualifiedid(CPPParser.UnqualifiedidContext ctx) {
        if( DEBUG ) {
            System.out.println("visitUnqualifiedid");
            for (int i = 0; i < ctx.children.size(); i++) {
                String tmp = ctx.getChild(i).getText();
                System.out.println(tmp);
            }
            System.out.println("#########################");
        }
        if( ctx.getChildCount() == 1){
            if( this.variables.get(ctx.getChild(0).getText()) != null){
                return this.variables.get(ctx.getChild(0).getText());
            }
        }

        return super.visitUnqualifiedid(ctx);
    }

    // + -
    @Override
    public Elem visitAdditiveexpression(CPPParser.AdditiveexpressionContext ctx) {
        if( DEBUG ) {
            for (int i = 0; i < ctx.children.size(); i++) {
                String tmp = ctx.getChild(i).getText();
                System.out.println(tmp);
            }
            System.out.println("#########################");
        }

        // Если ребенок один, то значит тут просто 1 число
        if(ctx.getChildCount() == 1){
            return super.visit(ctx.getChild(0));
        }else{
            Elem first = super.visit(ctx.getChild(0));
            Elem second = super.visit(ctx.getChild(2));
            switch (ctx.getChild(1).getText()){
                case "+":{
                    return first.add(second);
                }
                case "-":{
                    return first.sub(second);
                }
            }
            System.out.println();
        }
        return super.visitAdditiveexpression(ctx);

    }

    // * /
    @Override
    public Elem visitMultiplicativeexpression(CPPParser.MultiplicativeexpressionContext ctx) {
        if( DEBUG ){
            for(int i = 0 ; i < ctx.children.size(); i++){
                String tmp = ctx.getChild(i).getText();
                System.out.println(tmp);
            }
            System.out.println("#########################");
        }

        // Если ребенок один, то значит тут просто 1 число
        if(ctx.getChildCount() == 1){
            return super.visit(ctx.getChild(0));
        }else{
            Elem first = super.visit(ctx.getChild(0));
            Elem second = super.visit(ctx.getChild(2));
            switch (ctx.getChild(1).getText()){
                case "*":{
                    break;
                }
                case "/":{
                    break;
                }
            }
            System.out.println();
        }

        return super.visitMultiplicativeexpression(ctx);
    }

    // последний пункт в дереве, при числе и
    @Override
    public Elem visitLiteral(CPPParser.LiteralContext ctx) {
        if( DEBUG ){
            for(int i = 0 ; i < ctx.children.size(); i++){
                String tmp = ctx.getChild(i).getText();
                System.out.println(tmp);
            }
            System.out.println("#########################");
        }
        String value = ctx.getChild(0).getText();
        // Ищем вхождение точки в строке, если оно есть то это дабл, иначе это инт
        if( value.indexOf(".") > 0){
            return new Elem(TypeLexem.DOUBLE, value);
        }else{
            return new Elem(TypeLexem.INT, value);
        }
//        return super.visitLiteral(ctx);
    }
}
