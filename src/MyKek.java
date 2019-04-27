


public class MyKek extends CPPBaseVisitor<Elem>  {

    @Override
    public Elem visitSimpledeclaration(CPPParser.SimpledeclarationContext ctx) {

        String type = ctx.getChild(0).getText();
        for(int i = 0 ; i < ctx.children.size(); i++){
            String tmp = ctx.getChild(i).getText();
            System.out.println(tmp);
        }
        System.out.println("=========================");
        return super.visitSimpledeclaration(ctx);
    }

    @Override
    public Elem visitInitdeclarator(CPPParser.InitdeclaratorContext ctx) {
        String value = ")))";
        for(int i = 0 ; i < ctx.children.size(); i++){
            String tmp = ctx.getChild(i).getText();
            System.out.println(tmp);
        }
        System.out.println("=========================");

        if(ctx.getChildCount() > 1){
            Elem elem = super.visit(ctx.getChild(1));
            value = elem.getText();
        }

        String name = ctx.getChild(0).getText();


        return super.visitInitdeclarator(ctx);
    }

    @Override
    public Elem visitAdditiveexpression(CPPParser.AdditiveexpressionContext ctx) {
        for(int i = 0 ; i < ctx.children.size(); i++){
            String tmp = ctx.getChild(i).getText();
            System.out.println(tmp);
        }
        System.out.println("=========================");

        // Если ребенок один, то значит тут просто 1 число
        if(ctx.getChildCount() == 1){
            return super.visit(ctx.getChild(0));
        }else{
            Elem first = super.visit(ctx.getChild(0));
            Elem second = super.visit(ctx.getChild(2));
            switch (ctx.getChild(1).getText()){
                case "+":{
                    break;
                }
                case "-":{
                    break;
                }
            }
            System.out.println();
        }
        return super.visitAdditiveexpression(ctx);

    }

    // правый элемент при сложении
    @Override
    public Elem visitMultiplicativeexpression(CPPParser.MultiplicativeexpressionContext ctx) {
        for(int i = 0 ; i < ctx.children.size(); i++){
            String tmp = ctx.getChild(i).getText();
            System.out.println(tmp);
        }
        System.out.println("=========================");

        // Если ребенок один, то значит тут просто 1 число
        if(ctx.getChildCount() == 1){
            return super.visit(ctx.getChild(0));
        }else{
            Elem first = super.visit(ctx.getChild(0));
            Elem second = super.visit(ctx.getChild(0));
            System.out.println();
        }

        return super.visitMultiplicativeexpression(ctx);
    }

    // последний пункт в дереве, при числе и
    @Override
    public Elem visitLiteral(CPPParser.LiteralContext ctx) {
        for(int i = 0 ; i < ctx.children.size(); i++){
            String tmp = ctx.getChild(i).getText();
            System.out.println(tmp);
        }
        System.out.println("=========================");
        String value = ctx.getChild(0).getText();
        return new Elem(TypeLexem.INT, value);
//        return super.visitLiteral(ctx);
    }
}
