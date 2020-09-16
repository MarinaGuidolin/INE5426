import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {  
        try {
            if (args.length > 1) {
                throw new Error("Should have only one argument");
            }

            String filePath = args[0];
            // extract file extension and check if it's .ccc, and throw an error

            System.out.println("Starting Lexical Analysis");

            CharStream charStream = CharStreams.fromFileName(filePath);
            CC2020Lexer lexer = new CC2020Lexer(charStream);
            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
            CC2020Parser parser = new CC2020Parser(commonTokenStream);
            parser.program();
           
            System.out.println("Done");
        } catch (IOException e) {
            // handle the CharStream.fromFileName()
            e.printStackTrace();
        }
    }
}
