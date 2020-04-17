package bot;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class APIWrangler {
    public static final String BASE_URL = "https://api.brawlstars.com/v1/";

    public static JSONObject getJSONResponse(String uri) {
        try {
            CloseableHttpClient endpt = HttpClients.createDefault();
            HttpGet get = new HttpGet(uri);
            get.addHeader("Accept", "application/json");
            get.addHeader("authorization", "Bearer " + BotConfig.BRAWLSTARS_API_TOKEN);

            CloseableHttpResponse response = endpt.execute(get);
            HttpEntity entity = response.getEntity();
            JSONObject jsonResponse = new JSONObject(new JSONTokener(entity.getContent()));
            EntityUtils.consume(entity);

            return jsonResponse;
        } catch (Exception e){
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public static JSONObject getJSONClub(String clubId){
        if(!clubId.substring(0, 1).equals("#")){
            clubId = "#" + clubId;
        }
        clubId = URLEncoder.encode(clubId, StandardCharsets.UTF_8);

        return getJSONResponse(BASE_URL + "clubs/" + clubId);
    }

    public static JSONObject getJSONPlayer(String playerId){
        if(!playerId.substring(0, 1).equals("#")){
            playerId = "#" + playerId;
        }
        playerId = URLEncoder.encode(playerId, StandardCharsets.UTF_8);

        return getJSONResponse(BASE_URL + "players/" + playerId);
    }



    public static int getRequiredTrophies(String clubId){
        JSONObject clubinfo = getJSONClub(clubId);
        return clubinfo.getInt("requiredTrophies");
    }

    public static int getClubTrophies(String clubId){
        JSONObject clubinfo = getJSONClub(clubId);
        return clubinfo.getInt("trophies");
    }

    public static int getPlayerTrophies(String playerId){
        JSONObject playerinfo = getJSONPlayer(playerId);
        return playerinfo.getInt("trophies");
    }
}
