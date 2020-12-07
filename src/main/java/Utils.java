import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import ExpressionTree.ExpressionTree;
import Scope.ScopeNode;
import SymbolTable.SymbolTable;

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
				String format = String.format(
						"\nToken #%d: <key=%s, type=%s, lexeme=%s, line=%d, startIndex=%d, stopIndex=%d>\n", tokenIndex,
						lexeme, typeName, lexeme, line, startIndex, stopIndex);
				stringOfToken = format;
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

		Log.info("INFO", "The tokens of the " + fileName + ".ccc file can be found in the " + newFilePath + " file");

		writer.close();
	}

	/**
	 * Write the expression trees into a txt file located on "/Out/ExpressionTrees/" dir.
	 * @param trees the list of expression trees to be exported
	 */
	static public void exportExpressionTrees(List<ExpressionTree> trees) {
		String newFileName = "ExpTrees.txt";
		String directoryPath = "";
		try {
			directoryPath = createDirectory("/Out/ExpressionTrees/", ".");
			String newFilePath = directoryPath + newFileName;
			File newFile = new File(newFilePath);
			FileWriter writer = new FileWriter(newFile);
			for (ExpressionTree t : trees) {
				String s = t.getRoot().toString();
				writer.write(s + "\n");
			}

			Log.info("INFO", "The expression trees can be found in the " + newFilePath + " file");

			writer.close();
		} catch (IOException e) {
			Log.error("ERROR", "Invalid file");
			e.printStackTrace();
		}
	}

	/**
	 * Write the intermediary code into a txt file located on "/Out/CI/" dir.
	 * @param code the intermediary code generated
	 */
	static public void exportIntermediaryCode(String code) {
		String newFileName = "CI.txt";
		String directoryPath = "";
		try {
			directoryPath = createDirectory("/Out/CI/", ".");
			String newFilePath = directoryPath + newFileName;
			File newFile = new File(newFilePath);
			FileWriter writer = new FileWriter(newFile);
			writer.write(code);

			Log.info("INFO", "The intermediary code can be found in the " + newFilePath + " file");

			writer.close();
		} catch (IOException e) {
			Log.error("ERROR", "Invalid file");
			e.printStackTrace();
		}
	}

	/**
	 * Write the scopes and symbol tables into a txt file located on "/Out/Scopes/" dir.
	 * @param scopes all scopes generated by the analysis
	 */
	static public void exportScopes(List<ScopeNode> scopes) {
		String newFileName = "Scopes.txt";
		String directoryPath = "";
		try {
			directoryPath = createDirectory("/Out/Scopes/", ".");
			String newFilePath = directoryPath + newFileName;
			File newFile = new File(newFilePath);
			FileWriter writer = new FileWriter(newFile);
			for (ScopeNode s: scopes) {
				writer.write(s.toString());
			}

			Log.info("INFO", "The scopes can be found in the " + newFilePath + " file");

			writer.close();
		} catch (IOException e) {
			Log.error("ERROR", "Invalid file");
			e.printStackTrace();
		}
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
