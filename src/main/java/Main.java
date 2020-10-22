import java.io.IOException;
import java.util.HashSet;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;

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

			// Read the file, and start the lexer and parser.
			CharStream charStream = CharStreams.fromFileName(filePath);
			ConvCC20201Lexer lexer = new ConvCC20201Lexer(charStream);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ConvCC20201Parser parser = new ConvCC20201Parser(tokens);

			// Start the parser on the 'program' which is the initial grammar producer.
			parser.program();

			// Creating the symbol table with the tokens.
			HashSet<String> lexemeSet = Utils.createLexemeSet(tokens);
			int totalErrors = parser.getNumberOfSyntaxErrors();

			System.out.println(
					"Lexical analysis finished with: " + totalErrors + (totalErrors == 1 ? " error." : " errors."));

			Utils.exportTokens(filePath, tokens, lexemeSet);
			Utils.exportSymbolTable(filePath, lexemeSet);

			if (totalErrors == 0) {
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
