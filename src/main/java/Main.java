import static java.lang.System.out;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

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

			out.println("\n===> Starting Lexical Analysis");

			// Read the file, and start the lexer and parser.
			CharStream charStream = CharStreams.fromFileName(filePath);
			ConvCC20201Lexer lexer = new ConvCC20201Lexer(charStream);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ConvCC20201Parser parser = new ConvCC20201Parser(tokens);
			// Start the parser on the 'program' which is the initial grammar producer.

			out.println("===> Starting Syntactic Analysis");
			ParseTree tree = parser.program();

			// Creating the symbol table with the tokens.
			HashSet<String> lexemeSet = Utils.createLexemeSet(tokens);
			int totalErrors = parser.getNumberOfSyntaxErrors();

			out.println("===> Syntactic analysis finished with: " + totalErrors + (totalErrors == 1 ? " error." : " errors."));

			Utils.exportTokens(filePath, tokens, lexemeSet);
			Utils.exportSymbolTable(filePath, lexemeSet);

			TreeViewer viewer = new TreeViewer(Arrays.asList(
					parser.getRuleNames()),tree);
			viewer.open();

			if (totalErrors == 0) {
				out.println("Well done!");
			}

		} catch (IOException e) {
			out.println("Invalid file");
			e.printStackTrace();
		} catch (RecognitionException e) {
			out.println("Error while parsing the file " + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (Error e) {
			out.println(e.getMessage());
		}
	}
}
