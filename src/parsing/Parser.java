package parsing;

import java.util.List;

class Parser implements Runnable {

    private List<FileToken> fileTokens;

    public Parser(List<FileToken> tokens) {
        this.fileTokens = tokens;
    }

    public void start() {
        Thread thread = new Thread(Thread.currentThread().getThreadGroup(), "parser-thread");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

}
