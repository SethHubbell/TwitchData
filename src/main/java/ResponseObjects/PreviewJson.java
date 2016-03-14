package ResponseObjects;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class PreviewJson {
    String small = null;
    String medium = null;
    String large = null;
    String template = null;

    public PreviewJson PreviewJsonBuilder(JsonNode previewJsonNode){
        if(previewJsonNode.get("small") != null){
            setSmall(previewJsonNode.get("small").asText());
        }
        if(previewJsonNode.get("medium") != null){
            setMedium(previewJsonNode.get("medium").asText());
        }
        if(previewJsonNode.get("large") != null){
            setLarge(previewJsonNode.get("large").asText());
        }
        if(previewJsonNode.get("template") != null){
            setTemplate(previewJsonNode.get("template").asText());
        }
        return this;
    }

    public void printPreviewJson(){
        System.out.println("preview{");
        if(small != null){
            System.out.println("    small: " + small);
        }
        if(medium != null){
            System.out.println("    medium: " + medium);
        }
        if(large != null){
            System.out.println("    large: " + large);
        }
        if(template != null){
            System.out.println("    template: " + template);
        }
        System.out.println("}");
    }
}
