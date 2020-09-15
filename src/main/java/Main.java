import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        try {
            System.out.println("input");
            ANTLRInputStream input = new ANTLRInputStream(System.in);
            MyLexer lexer = new MyLexer(input);
           

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
