package ResponseObjects;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ChannelJson {
    boolean mature;
    String status = null;
    String broadcaster_language = null;
    String display_name = null;
    String game = null;
    String language = null;
    int channel_id;
    Date created_at = null;
    Date updated_at = null;
    int delay;
    String logo = null;
    String banner = null;
    String video_banner = null;
    String background = null;
    String profile_banner = null;
    String profile_banner_background_color = null;
    boolean partner;
    String url = null;
    int total_views;
    int followers;
    LinksJson _links = new LinksJson();

    public ChannelJson ChannelJsonBuilder(JsonNode channelJsonNode){
        try {
            if(channelJsonNode.get("mature") != null){
                setMature(channelJsonNode.get("mature").asBoolean());
            }
            if(channelJsonNode.get("status") != null){
                setStatus(channelJsonNode.get("status").asText());
            }
            if(channelJsonNode.get("broadcaster_language") != null){
                setBroadcaster_language(channelJsonNode.get("broadcaster_language").asText());
            }
            if(channelJsonNode.get("display_name") != null){
                setDisplay_name(channelJsonNode.get("display_name").asText());
            }
            if(channelJsonNode.get("game") != null){
                setGame(channelJsonNode.get("game").asText());
            }
            if(channelJsonNode.get("language") != null){
                setLanguage(channelJsonNode.get("language").asText());
            }
            if(channelJsonNode.get("_id") != null){
                setChannel_id(channelJsonNode.get("_id").asInt());
            }
            if(channelJsonNode.get("created_at") != null){
                setCreated_at(new SimpleDateFormat("yyyy-dd-MM'T'HH:mm:ss'Z'").parse(channelJsonNode.get("created_at").asText()));
            }
            if(channelJsonNode.get("updated_at") != null){
                setUpdated_at(new SimpleDateFormat("yyyy-dd-MM'T'HH:mm:ss'Z'").parse(channelJsonNode.get("updated_at").asText()));
            }
            if(channelJsonNode.get("delay") != null){
                setDelay(channelJsonNode.get("delay").asInt());
            }
            if(channelJsonNode.get("logo") != null){
                setLogo(channelJsonNode.get("logo").asText());
            }
            if(channelJsonNode.get("banner") != null){
                setBanner(channelJsonNode.get("banner").asText());
            }
            if(channelJsonNode.get("video_banner") != null){
                setVideo_banner(channelJsonNode.get("video_banner").asText());
            }
            if(channelJsonNode.get("background") != null){
                setBackground(channelJsonNode.get("background").asText());
            }
            if(channelJsonNode.get("profile_banner") != null){
                setProfile_banner(channelJsonNode.get("profile_banner").asText());
            }
            if(channelJsonNode.get("profile_banner_background_color") != null){
                setProfile_banner_background_color(channelJsonNode.get("profile_banner_background_color").asText());
            }
            if(channelJsonNode.get("partner") != null){
                setPartner(channelJsonNode.get("partner").asBoolean());
            }
            if(channelJsonNode.get("url") != null){
                setUrl(channelJsonNode.get("url").asText());
            }
            if(channelJsonNode.get("views") != null){
                setTotal_views(channelJsonNode.get("views").asInt());
            }
            if(channelJsonNode.get("followers") != null){
                setFollowers(channelJsonNode.get("followers").asInt());
            }
            if(channelJsonNode.get("_links") != null){
                set_links(_links.LinksJsonBuilder(channelJsonNode.get("_links")));
            }
        }catch (ParseException pe) {
            System.out.println("A date was impropperly formated");
            pe.printStackTrace();
        }
        return this;
    }

    public void printChannelJson(){
        System.out.println("status: " + status);
        System.out.println("mature: " + mature);
        System.out.println("display_name: " + display_name);
        System.out.println("game: " + game);
        System.out.println("broadcaster_language: " + broadcaster_language);
        System.out.println("language: " + language);
        System.out.println("channel_id: " + channel_id);
        System.out.println("created_at: " + created_at);
        System.out.println("updated_at: " + updated_at);
        System.out.println("delay: " + delay);
        System.out.println("logo: " + logo);
        System.out.println("banner: " + banner);
        System.out.println("video_banner: " + video_banner);
        System.out.println("background: " + background);
        System.out.println("profile_banner: " + profile_banner);
        System.out.println("profile_banner_background_color: " + profile_banner_background_color);
        System.out.println("partner: " + partner);
        System.out.println("url: " + url);
        System.out.println("total_views: " + total_views);
        System.out.println("followers: " + followers);
        _links.printLinksJson();
    }
}
