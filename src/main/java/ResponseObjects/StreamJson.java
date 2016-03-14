package ResponseObjects;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class StreamJson {
    long stream_id;
    String game;
    int viewer_count;
    Date stream_start;
    int video_height;
    Float average_fps;
    int delay;
    boolean Beta_is_playlist;
    ChannelJson channelJson = new ChannelJson();
    PreviewJson previewJson = new PreviewJson();
    LinksJson linksJson = new LinksJson();

    public StreamJson(JsonNode responseMap){
        StreamJsonBuilder(responseMap);
    }

    public StreamJson StreamJsonBuilder(JsonNode responseMap){
        try {
            setStream_id(responseMap.get("_id").asLong());
            setGame(responseMap.get("game").asText());
            setViewer_count(responseMap.get("viewers").asInt());
            setStream_start(new SimpleDateFormat("yyyy-dd-MM'T'HH:mm:ss'Z'").parse(responseMap.get("created_at").asText()));
            setVideo_height(responseMap.get("video_height").asInt());
            setAverage_fps(responseMap.get("average_fps").floatValue());
            setDelay(responseMap.get("delay").asInt());
            setBeta_is_playlist(responseMap.get("is_playlist").asBoolean());
            setChannelJson(channelJson.ChannelJsonBuilder(responseMap.get("channel")));
            setPreviewJson(previewJson.PreviewJsonBuilder(responseMap.get("preview")));
            setLinksJson(linksJson.LinksJsonBuilder(responseMap.get("_links")));

        } catch (ParseException pe){
            System.out.println("A date was improperly formatted");
            pe.printStackTrace();
        }
        return this;
    }

    public void printStreamJson(){
        System.out.println("StreamReturn{");
        System.out.println("_id: " + stream_id);
        System.out.println("game: " + game);
        System.out.println("viewer_count: " + viewer_count);
        System.out.println("created_at: " + stream_start);
        System.out.println("video_height: " + video_height);
        System.out.println("average_fps: " + average_fps);
        System.out.println("delay: " + delay);
        System.out.println("Beta_is_playlist: " + Beta_is_playlist);
        linksJson.printLinksJson();
        previewJson.printPreviewJson();
        channelJson.printChannelJson();
        System.out.println("}");
    }
}
