package src.GroSpace;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@SuppressWarnings("unchecked")
public class Request
{
    private JSONObject data;
    private final URL url;
    
    public Request()
    {
        //Setting up JSONObject
        data = new JSONObject();
        data.put("layFlat", true);
        data.put( "includeScripts", false );
        data.put( "includeImages", true );
        data.put( "boxesPerItemSetMax", 1 );
        
        //Setting up URL
        URL temp;
        try
        {
            temp = new URL("https://api.paccurate.io/");
        }
        catch ( MalformedURLException e )
        {
            temp = null;
            e.printStackTrace();
        }
        url = temp;
    }
    
    public void addData(int roomHeight, int roomWidth, int roomLength, int unitHeight, int unitWidth, int unitLength)
    {
        //Make room
        JSONObject room = new JSONObject();
        
        JSONObject roomSize = new JSONObject();
        roomSize.put( "x", roomHeight);
        roomSize.put( "y", roomWidth );
        roomSize.put( "z", roomLength );
        room.put( "dimensions", roomSize );
        
        //JSONArray obstructors = new JSONArray();
        //obstructors.add( item );
                
        JSONArray rooms = new JSONArray();
        rooms.add( room );
        data.put( "boxTypes", rooms );
        
        //Make modules
        JSONObject module = new JSONObject();
        module.put( "weight", 0 );
        module.put( "color", "green" );
        module.put( "quantity", 100);
        JSONObject moduleSize = new JSONObject();
        moduleSize.put( "x", unitHeight );
        moduleSize.put( "y", unitWidth );
        moduleSize.put( "z", unitLength );
        module.put( "dimensions", moduleSize );
        
        JSONArray modules = new JSONArray();
        modules.add( module );
        data.put( "itemSets", modules );
    }
    
    public JSONObject getData()
    {
        return data;
    }
    
    public JSONObject sendRequest()
    {
        byte[] out = data.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        
        HttpURLConnection con = null;
        try
        {
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod( "POST" );
            con.setDoOutput( true );
            con.setFixedLengthStreamingMode(length);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.connect();
            OutputStream os = con.getOutputStream();
            os.write(out);
            int code = con.getResponseCode();
            if(code == 200)
            {
                InputStream is = con.getInputStream();
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(is, "UTF-8"));
                os.close();
                is.close();
                con.disconnect();
                return jsonObject;
            }
            else
            {
                System.out.println(code);
                return null;
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
            return null;
        }
        catch ( ParseException e )
        {
            e.printStackTrace();
            return null;
        }
    }
}