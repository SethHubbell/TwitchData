import ResponseObjects.StreamJson;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TwitchPubAPICrawler{
    TwitchCSVWriter writer = new TwitchCSVWriter();
    RESTRequestHelper requestHelper = new RESTRequestHelper();
    Client client = ClientBuilder.newClient();
    boolean status_ok = true;
    boolean stop = false;

    public TwitchPubAPICrawler() throws IOException, InterruptedException {
        Console console = System.console();
        String streamer = console.readLine("Enter a streamer: ");
        int sleeptime = Integer.parseInt(console.readLine("Enter a sleeptime in seconds: ")) * 1000;
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");

        if(streamExists(streamer)){
            File file = new File("logs/" + streamer + "-" + (formattedDate.format(new Date())) + ".csv");
            if(file.exists()){
                writer.setWriteHeaders(false);
            } else {
                writer.setWriteHeaders(true);
            }
            writer.initializeCSVPrinter(file);
            startCrawler(streamer, sleeptime);
            closeCrawler();
        } else {
            System.out.println("Stream \"" + streamer + "\" doesn't exist!");
        }
    }

    public void startCrawler(String streamer, int sleeptime) throws IOException, InterruptedException {
        Thread scanningThread = constructScanningThread();
        WebTarget resource = client.target("https://api.twitch.tv/kraken/streams/" + streamer);
        Response response;
        scanningThread.start();
        while (status_ok && !stop){
            requestHelper.printHeartBeat();
            response = requestHelper.restRequest("GET",resource);
            String responseString = response.readEntity(String.class);
            JsonNode responseMap  = new ObjectMapper().readTree(new StringReader(responseString)).get("stream");
            if(!responseMap.isNull()){
                if(response.getStatus() != HttpStatus.SC_OK){
                    status_ok = false;
                }
                StreamJson stream = new StreamJson(responseMap);
                writer.writeRow(buildRow(stream));
//                stream.printStreamJson();
                Thread.sleep(sleeptime);
            } else {
                status_ok = false;
            }
        }
        scanningThread.join();
    }

    public Thread constructScanningThread(){
        return new Thread(() -> {
            Scanner scan = new Scanner(System.in);
            String input = null;
            input = scan.nextLine();
            if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q")) {
                setStop(true);
            }
            try {
                System.in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setStop(boolean newVal){
        stop = newVal;
    }

    public void closeCrawler() throws IOException{
        writer.closeCSVWriter();
        System.out.println("Stream offline! goodbye.");
    }

    public List<Object> buildRow(StreamJson stream){
        List<Object> row = new ArrayList<>();
        row.add(stream.getViewer_count());
        row.add(stream.getChannelJson().getFollowers());
        row.add(stream.getChannelJson().getTotal_views());
        row.add(stream.getGame());
        row.add(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        return row;
    }

    public boolean streamExists(String streamer){
        WebTarget resource = client.target("https://api.twitch.tv/kraken/streams/" + streamer);
        Response response = requestHelper.restRequest("GET",resource);
        return response.getStatus() == HttpStatus.SC_OK;
    }
}
