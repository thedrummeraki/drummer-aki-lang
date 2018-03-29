package parsing;

import java.io.File;
import java.util.List;

public class FileToken {

    private File file;
    private List<Token> tokens;

    public FileToken(File file, List<Token> tokens) {
        if (file == null || tokens == null) {
            throw new IllegalArgumentException("File or Tokens list cannot be null");
        }
        this.file = file;
        this.tokens = tokens;
    }

    public File getFile() {
        return file;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        return "[File(\"" + getFile() + "\") - " + getTokens().size() + " token(s)]";
    }
}
