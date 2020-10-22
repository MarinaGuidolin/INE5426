import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class Utils {

	/**
	 * Extract the file extension.
	 * @param filePath relative or absoulute file path
	 * @return return the file extension. e.g. file: `/examples/file.abc`, it returns `abc`
	 */
	static public String extractFileExtension(String filePath) {
		String extension = "";
		int i = filePath.lastIndexOf('.');
		if (i > 0) {
			extension = filePath.substring(i + 1);
		}
		return extension;
	}

	/**
	 * Extract the file name.
	 * @param filePath relative or absoulute file path
	 * @return return the file extension. e.g. file: `/examples/file.abc`, it returns `file`
	 */
	static public String extractFileName(String filePath) {
		String fileName = "";
		int i = filePath.lastIndexOf('/');
		int j = filePath.lastIndexOf('.');
		if (i > 0) {
			fileName = filePath.substring(i + 1, j);
		}
		return fileName;
	}

	/**
	 * Generate a list of tokens to be written.
	 * @param filePath the file path of the input program
	 * @param tokens the token stream from ANTLR
	 * @param lexemeSet a set with all lexemes of the program
	 * @throws IOException
	 */
	static public void exportTokens(String filePath, CommonTokenStream tokens,
			HashSet<String> lexemeSet) throws IOException {
		ArrayList<String> tokensList = new ArrayList<>();

		for (Token token : tokens.getTokens()) {
			int type = token.getType();
			String typeName = ConvCC20201Lexer.VOCABULARY.getSymbolicName(type);
			String lexeme = token.getText();

			int line = token.getLine();
			int startIndex = token.getStartIndex();
			int stopIndex = token.getStopIndex();
			int tokenIndex = token.getTokenIndex();

			String stringOfToken = "";

			if (lexemeSet.contains(lexeme)) {
				stringOfToken = String.format(
						"\nToken #%d: <key=%s, type=%s, lexeme=%s, line=%d, startIndex=%d, stopIndex=%d>\n", tokenIndex,
						lexeme, typeName, lexeme, line, startIndex, stopIndex);
			} else {
				stringOfToken = String.format("\nToken #%d: <type=%s, lexeme=%s, line=%d, startIndex=%d, stopIndex=%d>\n",
						tokenIndex, typeName, lexeme, line, startIndex, stopIndex);
			}

			tokensList.add(stringOfToken);
		}

		writeTokens(filePath, tokensList);
	}

	/**
	 * Write the tokens from the input file into a txt file located on "/Out/Tokens/" dir.
	 * @param filePath the file path of the input program
	 * @param tokens the set of lexemes of the file
	 * @throws IOException
	 */
	static public void writeTokens(String filePath, ArrayList<String> tokens) throws IOException {
		String fileName = extractFileName(filePath);
		String newFileName = fileName + ".txt";
		String directoryPath = createDirectory("/Out/Tokens/", filePath);
		String newFilePath = directoryPath + newFileName;
		File newFile = new File(newFilePath);
		FileWriter writer = new FileWriter(newFile);

		writer.write("============> Tokens from the file " + fileName + ".ccc\n\n");

		for (String token : tokens) {
			writer.write(token);
		}

		System.out.println("Os tokens do arquivo " + fileName + ".ccc se encontram no arquivo: " + newFilePath);

		writer.close();
	}

	/**
	 * Export a symbol table from the input program into a txt file located on "/Out/SymbolTable/" dir.
	 * @param filePath the file path of the input program
	 * @param lexemeSet the set of lexemes of the file
	 * @throws IOException
	 */
	static public void exportSymbolTable(String filePath, HashSet<String> lexemeSet)
			throws IOException {
		String fileName = extractFileName(filePath);
		String newFileName = fileName + ".txt";
		String directoryPath = createDirectory("/Out/SymbolTable/", filePath);
		String newFilePath = directoryPath + newFileName;
		File newFile = new File(newFilePath);
		FileWriter writer = new FileWriter(newFile);

		writer.write("============> Symbol Table from the file " + fileName + ".ccc\n\n");

		for (String lexeme : lexemeSet) {
			String s = String.format("Entry: <key=%s, %s>\n", lexeme, "lexeme = " + lexeme);
			writer.write(s);
		}

		System.out
				.println("A tabela de símbolos do arquivo " + fileName + ".ccc se encontra no arquivo: " + newFilePath);

		writer.close();
	}

	/**
	 * Create a new directory
	 * @param name name of the directory
	 * @param filePath file path of the directory
	 * @return the directory path
	 * @throws IOException
	 */
	static public String createDirectory(String name, String filePath) throws IOException {
		File file = new File(".");
		String fullPath = file.getCanonicalPath();
		String directoryPath = fullPath + name;
		File dir = new File(directoryPath);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		return directoryPath;
	}

	/**
	 * Using the token stream from ANTLR creates a HashSet formed by lexemes
	 * @param tokens A token stream from ANTLR
	 * @return a set with all lexemes
	 */
	public static HashSet<String> createLexemeSet(CommonTokenStream tokens) {
		HashSet<String> lexemeSet = new HashSet<String>();

		for (Token token : tokens.getTokens()) {
			int type = token.getType();
			String typeName = ConvCC20201Lexer.VOCABULARY.getSymbolicName(type);

			if (typeName.equals("IDENT")) {
				lexemeSet.add(token.getText());
			}
		}

		return lexemeSet;
	}
}
