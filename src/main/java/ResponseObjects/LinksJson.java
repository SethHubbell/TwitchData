package ResponseObjects;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class LinksJson {
    String self = null;
    String follows = null;
    String commercial = null;
    String stream_key = null;
    String chat = null;
    String features = null;
    String subscriptions = null;
    String editors = null;
    String teams = null;
    String videos = null;

    public LinksJson LinksJsonBuilder(JsonNode LinksJsonNode){
        if(LinksJsonNode.get("self") != null){
            setSelf(LinksJsonNode.get("self").asText());
        }
        if(LinksJsonNode.get("follows") != null){
            setFollows(LinksJsonNode.get("follows").asText());
        }
        if(LinksJsonNode.get("commercial") != null){
            setCommercial(LinksJsonNode.get("commercial").asText());
        }
        if(LinksJsonNode.get("stream_key") != null){
            setStream_key(LinksJsonNode.get("stream_key").asText());
        }
        if(LinksJsonNode.get("chat") != null){
            setChat(LinksJsonNode.get("chat").asText());
        }
        if(LinksJsonNode.get("features") != null){
            setFeatures(LinksJsonNode.get("features").asText());
        }
        if(LinksJsonNode.get("subscriptions") != null){
            setSubscriptions(LinksJsonNode.get("subscriptions").asText());
        }
        if(LinksJsonNode.get("editors") != null){
            setEditors(LinksJsonNode.get("editors").asText());
        }
        if(LinksJsonNode.get("teams") != null){
            setTeams(LinksJsonNode.get("teams").asText());
        }
        if(LinksJsonNode.get("videos") != null){
            setVideos(LinksJsonNode.get("videos").asText());
        }
        return this;
    }

    public void printLinksJson(){
        System.out.println("_links{");
        if(self != null){
            System.out.println("    self: " + self);
        }
        if(follows != null){
            System.out.println("    follows: " + follows);
        }
        if(commercial != null){
            System.out.println("    commercial: " + commercial);
        }
        if(stream_key != null){
            System.out.println("    stream_key: " + stream_key);
        }
        if(chat != null){
            System.out.println("    chat: " + chat);
        }
        if(features != null){
            System.out.println("    features: " + features);
        }
        if(subscriptions != null){
            System.out.println("    subscriptions: " + subscriptions);
        }
        if(editors != null){
            System.out.println("    editors: " + editors);
        }
        if(teams != null){
            System.out.println("    teams: " + teams);
        }
        if(videos != null) {
            System.out.println("    videos: " + videos);
        }
        System.out.println("}");
    }

}
