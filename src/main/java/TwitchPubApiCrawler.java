import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import ResponseObjects.StreamJson;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.input.KeyCode;
import org.apache.http.HttpStatus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TwitchPubApiCrawler {
    public static void main(String[] args) throws InterruptedException, IOException {
        Console console = System.console();
        String streamer = console.readLine("Enter a streamer: ");
        int sleeptime = Integer.parseInt(console.readLine("Enter a sleeptime in seconds: ")) * 1000;
        TwitchCSVWriter writer = new TwitchCSVWriter();
        //TODO fix date format for writing to file
        SimpleDateFormat formatedDate = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        writer.writeHeaders("logs/" + streamer + "-" + (formatedDate.format(currentDate)));

        RESTRequestHelper requestHelper = new RESTRequestHelper();
        Client client = ClientBuilder.newClient();
        WebTarget resource = client.target("https://api.twitch.tv/kraken/streams/" + streamer);
        Response response;
        boolean status_ok = true;

        while (status_ok){
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
        writer.closeCSVWriter();
        System.out.println("Stream offline! goodbye.");
    }

    public static List<Object> buildRow(StreamJson stream){
        List<Object> row = new ArrayList<>();
        row.add(stream.getViewer_count());
        row.add(stream.getChannelJson().getFollowers());
        row.add(stream.getChannelJson().getTotal_views());
        row.add(stream.getGame());
        row.add(new SimpleDateFormat("HH:mm:ss"));
        return row;
    }
}
