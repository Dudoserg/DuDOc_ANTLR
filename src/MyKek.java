import javafx.fxml.Initializable;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MyKek extends CPPBaseVisitor<Elem>    {

    boolean flagMulti = false;

    public static boolean DEBUG = false;

    private Map<String, Elem> variables = new HashMap<String, Elem>();
    private Map<String, Elem> functions = new HashMap<String, Elem>();



    @Override
    public Elem visitTranslationunit(CPPParser.TranslationunitContext ctx) {
        functions.put("printf", new Elem(TypeLexem.VOID,"printf"));

        return super.visitTranslationunit(ctx);
    }

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

    // if else
    @Override
    public Elem visitSelectionstatement(CPPParser.SelectionstatementContext ctx) {
        if( DEBUG ) {
            System.out.println("visitSelectionstatement");
            for (int i = 0; i < ctx.children.size(); i++) {
                String tmp = ctx.getChild(i).getText();
                System.out.println(tmp);
            }
            System.out.println("#########################");
        }
        int count = ctx.getChildCount();

        try {
            if(count == 5){
                // if
                Elem condition = super.visit(ctx.getChild(2)); // посещаем условие
            }else if(count == 7){
                //if else
                Elem condition = super.visit(ctx.getChild(2)); // посещаем условие
                System.out.println();
                if( condition.getText().equals("1")){
                    // true
                    super.visit(ctx.getChild(4));
                    System.out.println();
                }else{
                    //false
                    super.visit(ctx.getChild(6));
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("В if творится какая то грязь");
            System.exit(1);
        }
        // Чтобы мы не обработали заново true false, делаем так
        return new Elem(TypeLexem.VOID,"");
        //return super.visitSelectionstatement(ctx);
    }

    // >= <= > <
    @Override
    public Elem visitRelationalexpression(CPPParser.RelationalexpressionContext ctx) {
        if( DEBUG ) {
            System.out.println("visitRelationalexpression");
            for (int i = 0; i < ctx.children.size(); i++) {
                String tmp = ctx.getChild(i).getText();
                System.out.println(tmp);
            }
            System.out.println("#########################");
        }

        // 3 если сравнение
        if( ctx.getChildCount() == 3){
            Elem trueElem = new Elem(TypeLexem.INT, "1");
            Elem falseElem = new Elem(TypeLexem.INT, "0");
            Elem first = super.visit(ctx.getChild(0));
            Elem second = super.visit(ctx.getChild(2));
            if (ctx.getChild(1).getText().equals(">=")) {
                if (first.moreEqual(second)) {
                    return trueElem;
                } else {
                    return falseElem;
                }
            } else if (ctx.getChild(1).getText().equals("<=")) {
                if (first.lessEqual(second)) {
                    return trueElem;
                } else {
                    return falseElem;
                }
            }else if (ctx.getChild(1).getText().equals(">")) {
                if (first.more(second)) {
                    return trueElem;
                } else {
                    return falseElem;
                }
            }else if (ctx.getChild(1).getText().equals("<")) {
                if (first.less(second)) {
                    return trueElem;
                } else {
                    return falseElem;
                }
            }
        }

        return super.visitRelationalexpression(ctx);
    }

    // ==
    @Override
    public Elem visitEqualityexpression(CPPParser.EqualityexpressionContext ctx) {
        if( DEBUG ) {
            System.out.println("visitEqualityexpression");
            for (int i = 0; i < ctx.children.size(); i++) {
                String tmp = ctx.getChild(i).getText();
                System.out.println(tmp);
            }
            System.out.println("#########################");
        }
        // 3 если сравнение
        if( ctx.getChildCount() == 3){
            Elem trueElem = new Elem(TypeLexem.INT, "1");
            Elem falseElem = new Elem(TypeLexem.INT, "0");
            Elem first = super.visit(ctx.getChild(0));
            Elem second = super.visit(ctx.getChild(2));
            if (ctx.getChild(1).getText().equals("==")) {
                if (first.equal(second)) {
                    return trueElem;
                } else {
                    return falseElem;
                }
            } else if (ctx.getChild(1).getText().equals("!=")) {
                if (first.notEqual(second)) {
                    return trueElem;
                } else {
                    return falseElem;
                }
            }
        }
        return super.visitEqualityexpression(ctx);
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

    // main printf...
    @Override
    public Elem visitPostfixexpression(CPPParser.PostfixexpressionContext ctx) {
        if( ctx.getChildCount() == 4)
            flagMulti = false;
        if( DEBUG ) {
            System.out.println("visitPostfixexpression");
            for (int i = 0; i < ctx.children.size(); i++) {
                String tmp = ctx.getChild(i).getText();
                System.out.println(tmp);
            }
            System.out.println("#########################");
        }
        if( ctx.getChildCount() > 1 && ctx.getChild(0).getText().equals("printf")){
            String str = ctx.getChild(2).getText();
            Elem elem = null;
            if( str.charAt(0) == '\'' && str.charAt(str.length()-1) == '\'' ){
                elem = new Elem(TypeLexem.INT, str.substring(1,str.length()-1));
            }else{
                elem = super.visit(ctx.getChild(2));
            }
            if( elem != null)
//                System.out.println("printf = " + elem.getText() + "//////////////////////////////////////////////");
                System.out.println( elem.getText() );
            else
                System.out.println("переменная '" + str + "' не объявлена ранее");
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
            else {
                if(flagMulti && this.functions.get(ctx.getChild(0).getText()) == null ){
                    throw new NullPointerException("Переменная ранее не определена");
                }

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
        flagMulti = true;
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
                    return first.star(second);

                }
                case "/":{
                    return first.division(second);

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
