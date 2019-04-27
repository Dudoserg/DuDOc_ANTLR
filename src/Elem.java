import java.lang.reflect.Type;

public class Elem {
    private TypeLexem typeLexeme;
    private String text;

    public Elem(TypeLexem typeLexeme, String text) {
        this.typeLexeme = typeLexeme;
        this.text = text;
    }

    public Elem add(Elem elem){
        int first = Integer.parseInt(this.text);
        int second = Integer.parseInt(elem.getText());
//        TypeLexem typeLexem =
    }


    public TypeLexem getTypeLexeme() {
        return typeLexeme;
    }

    public void setTypeLexeme(TypeLexem typeLexeme) {
        this.typeLexeme = typeLexeme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
