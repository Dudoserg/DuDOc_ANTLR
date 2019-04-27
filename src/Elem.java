import java.lang.reflect.Type;

public class Elem {
    private TypeLexem typeLexeme;
    private String text;

    public Elem(TypeLexem typeLexeme, String text) {
        this.typeLexeme = typeLexeme;
        this.text = text;
    }

    public boolean equal(Elem elem){
        TypeLexem first_type = this.getTypeLexeme();
        TypeLexem second_type = elem.getTypeLexeme();

        if( first_type == TypeLexem.INT){
            int first = Integer.parseInt(this.text);
            if(second_type == TypeLexem.INT){
                // INT INT
                int second = Integer.parseInt(elem.getText());
                return first == second;
            }else {
                // INT DOUBLE
                double second = Double.parseDouble(elem.getText());
                return first == second;
            }

        }else {
            double first = Double.parseDouble(this.text);
            if(second_type == TypeLexem.INT){
                // DOUBLE INT
                int second = Integer.parseInt(elem.getText());
                return first == second;
            }else {
                // DOUBLE DOUBLE
                double second = Double.parseDouble(elem.getText());
                return first == second;
            }
        }
    }

    public boolean notEqual(Elem elem){
        TypeLexem first_type = this.getTypeLexeme();
        TypeLexem second_type = elem.getTypeLexeme();

        if( first_type == TypeLexem.INT){
            int first = Integer.parseInt(this.text);
            if(second_type == TypeLexem.INT){
                // INT INT
                int second = Integer.parseInt(elem.getText());
                return first != second;
            }else {
                // INT DOUBLE
                double second = Double.parseDouble(elem.getText());
                return first != second;
            }

        }else {
            double first = Double.parseDouble(this.text);
            if(second_type == TypeLexem.INT){
                // DOUBLE INT
                int second = Integer.parseInt(elem.getText());
                return first != second;
            }else {
                // DOUBLE DOUBLE
                double second = Double.parseDouble(elem.getText());
                return first != second;
            }
        }
    }

    public boolean moreEqual(Elem elem){
        TypeLexem first_type = this.getTypeLexeme();
        TypeLexem second_type = elem.getTypeLexeme();

        if( first_type == TypeLexem.INT){
            int first = Integer.parseInt(this.text);
            if(second_type == TypeLexem.INT){
                // INT INT
                int second = Integer.parseInt(elem.getText());
                return first >= second;
            }else {
                // INT DOUBLE
                double second = Double.parseDouble(elem.getText());
                return first >= second;
            }

        }else {
            double first = Double.parseDouble(this.text);
            if(second_type == TypeLexem.INT){
                // DOUBLE INT
                int second = Integer.parseInt(elem.getText());
                return first >= second;
            }else {
                // DOUBLE DOUBLE
                double second = Double.parseDouble(elem.getText());
                return first >= second;
            }
        }
    }

    public boolean lessEqual(Elem elem){
        TypeLexem first_type = this.getTypeLexeme();
        TypeLexem second_type = elem.getTypeLexeme();

        if( first_type == TypeLexem.INT){
            int first = Integer.parseInt(this.text);
            if(second_type == TypeLexem.INT){
                // INT INT
                int second = Integer.parseInt(elem.getText());
                return first <= second;
            }else {
                // INT DOUBLE
                double second = Double.parseDouble(elem.getText());
                return first <= second;
            }

        }else {
            double first = Double.parseDouble(this.text);
            if(second_type == TypeLexem.INT){
                // DOUBLE INT
                int second = Integer.parseInt(elem.getText());
                return first <= second;
            }else {
                // DOUBLE DOUBLE
                double second = Double.parseDouble(elem.getText());
                return first <= second;
            }
        }
    }

    public boolean more(Elem elem){
        TypeLexem first_type = this.getTypeLexeme();
        TypeLexem second_type = elem.getTypeLexeme();

        if( first_type == TypeLexem.INT){
            int first = Integer.parseInt(this.text);
            if(second_type == TypeLexem.INT){
                // INT INT
                int second = Integer.parseInt(elem.getText());
                return first > second;
            }else {
                // INT DOUBLE
                double second = Double.parseDouble(elem.getText());
                return first > second;
            }

        }else {
            double first = Double.parseDouble(this.text);
            if(second_type == TypeLexem.INT){
                // DOUBLE INT
                int second = Integer.parseInt(elem.getText());
                return first > second;
            }else {
                // DOUBLE DOUBLE
                double second = Double.parseDouble(elem.getText());
                return first > second;
            }
        }
    }

    public boolean less(Elem elem){
        TypeLexem first_type = this.getTypeLexeme();
        TypeLexem second_type = elem.getTypeLexeme();

        if( first_type == TypeLexem.INT){
            int first = Integer.parseInt(this.text);
            if(second_type == TypeLexem.INT){
                // INT INT
                int second = Integer.parseInt(elem.getText());
                return first < second;
            }else {
                // INT DOUBLE
                double second = Double.parseDouble(elem.getText());
                return first < second;
            }

        }else {
            double first = Double.parseDouble(this.text);
            if(second_type == TypeLexem.INT){
                // DOUBLE INT
                int second = Integer.parseInt(elem.getText());
                return first < second;
            }else {
                // DOUBLE DOUBLE
                double second = Double.parseDouble(elem.getText());
                return first < second;
            }
        }
    }

////////////////////////////////////////////////////////////////////
    public Elem add(Elem elem){
        return this.add_sub(elem,1);
    }
    public Elem sub(Elem elem){
        return this.add_sub(elem,-1);
    }

    private Elem add_sub(Elem elem, int sign){
        TypeLexem first_type = this.getTypeLexeme();
        TypeLexem second_type = elem.getTypeLexeme();

        if( first_type == TypeLexem.INT){
            int first = Integer.parseInt(this.text);
            if(second_type == TypeLexem.INT){
                // INT INT
                int second = Integer.parseInt(elem.getText());
                return new Elem(TypeLexem.INT,  String.valueOf(first + second * sign));
            }else {
                // INT DOUBLE
                double second = Double.parseDouble(elem.getText());
                return new Elem(TypeLexem.DOUBLE, String.valueOf(first + second * sign));
            }

        }else {
            double first = Double.parseDouble(this.text);
            if(second_type == TypeLexem.INT){
                // DOUBLE INT
                int second = Integer.parseInt(elem.getText());
                return new Elem(TypeLexem.DOUBLE,  String.valueOf(first + second * sign));
            }else {
                // DOUBLE DOUBLE
                double second = Double.parseDouble(elem.getText());
                return new Elem(TypeLexem.DOUBLE,  String.valueOf(first + second * sign));
            }
        }
    }

///////////////////////////////////////////////////////////////////////////
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
