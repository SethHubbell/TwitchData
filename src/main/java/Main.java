import java.io.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Console console = System.console();
        String streamer = console.readLine("Enter a streamer: ");
        int sleeptime = Integer.parseInt(console.readLine("Enter a sleeptime in seconds: ")) * 1000;
        new TwitchPubAPICrawler(streamer, sleeptime);
    }
}
