import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
	public static void main(String[] args) throws IOException {
		final String VALID_FILE_EXTENSION = "ccc";

		try {
			if (args.length > 1) {
				throw new Error("Should have only one argument");
			}

			String filePath = args[0];
			if (!Utils.extractFileExtension(filePath).equals(VALID_FILE_EXTENSION)) {
				throw new Error("Invalid file extension. It should be a .ccc file!");
			}

			System.out.println("Starting Lexical Analysis");

			CharStream charStream = CharStreams.fromFileName(filePath);
			CC2020Lexer lexer = new CC2020Lexer(charStream);

			CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
			CC2020Parser parser = new CC2020Parser(commonTokenStream);

			ParseTree tree = parser.program();
			
			ParseTreeWalker walker = new ParseTreeWalker();
			
			ExporterSymbolTable symb = new ExporterSymbolTable();
			
			walker.walk(symb, tree);
				
			int errorTotal = parser.getNumberOfSyntaxErrors();
			
			System.out.println(
					"Lexical analysis finished with: " + errorTotal + (errorTotal == 1 ? " error." : " errors"));

			Utils.exportTokens(filePath, commonTokenStream);
			
			Utils.exportSymbolTable(filePath, symb.getSymbolTable(), symb.getKeysName());
			
			System.out.println(symb.getSymbolTable().get(Integer.valueOf(1)).getValues());

			if (errorTotal == 0) {
				System.out.println("Well done!");
			}

		} catch (IOException e) {
			System.out.println("Invalid file");
			e.printStackTrace();
		} catch (RecognitionException e) {
			System.out.println("Error while parsing the file " + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (Error e) {
			System.out.println(e.getMessage());
		}
	}
}
