import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RESTRequestHelper {
    String heartBeat = "|--------------";
    public Response restRequest(String method, WebTarget resource){
        switch (method){
            case "GET":
                return resource.request().accept(MediaType.APPLICATION_JSON).get(Response.class);
            case "POST":
                return resource.request().accept(MediaType.APPLICATION_JSON).get(Response.class);
            case "PUT":
                return resource.request().accept(MediaType.APPLICATION_JSON).get(Response.class);
            case "DELETE":
                return resource.request().accept(MediaType.APPLICATION_JSON).get(Response.class);
            default:
                throw new RuntimeException("Invalid http request method: " + method);
        }
    }

    public void printHeartBeat(){
        System.out.println(heartBeat);
        updateHeartBeat();
    }

    public void updateHeartBeat(){
        int indexOfBeat = heartBeat.indexOf("|");
        if(indexOfBeat == heartBeat.length()-1){
            heartBeat = "|--------------";
        } else {
            heartBeat = heartBeat.replace("|-","-|");
        }
    }
}
