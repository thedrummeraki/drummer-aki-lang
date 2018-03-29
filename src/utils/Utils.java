package utils;

import parsing.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    
    private Utils() {}

    public static List<FileToken> getTokens(List<File> files) {
        List<FileToken> tokens = new ArrayList<>();
        for (File file : files) {
            List<Token> tokensFromFile = getTokensFrom(file);
            tokens.add(new FileToken(file, tokensFromFile));
        }
        return tokens;
    }

    private static List<Token> getTokensFrom(File file) {
        List<Token> tokens = new ArrayList<>();
        if (file != null && file.exists() && file.isFile()) {
            List<String> fileContents = readLines(file);
            tokens.addAll(Token.tokensFromLines(fileContents));
        }
        return tokens;
    }

    public static List<String> readLines(File file) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
