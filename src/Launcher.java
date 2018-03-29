import parsing.FileToken;
import parsing.Token;
import utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Launcher {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            help();
        } else {
            List<File> files = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                File file = new File(args[i]);
                if (file.isDirectory()) {
                    System.out.println("Skipping directory \"" + file + "\"...");
                    continue;
                }
                if (!file.exists()) {
                    System.out.println("Skipping non existing file \"" + file + "\"...");
                    continue;
                }
                files.add(file);
            }

            List<FileToken> fileTokens = Utils.getTokens(files);
            int tokensCount = 0;
            for (FileToken ft : fileTokens) tokensCount += ft.getTokens().size();
            System.out.println("Found " + tokensCount + " token(s) in " + files.size() + " file(s).");
        }
    }

    private static void help() {
        System.err.println("Usage: java Launcher <files...>");
        System.exit(1);
    }
    
}
