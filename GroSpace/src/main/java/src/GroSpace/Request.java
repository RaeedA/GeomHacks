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
        data.put( "imageFormat", "PNG" );
        data.put( "reservedSpace", 0.3 );
        //data.put()
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
        
        /*JSONObject temp;
        JSONArray obstructors = new JSONArray();
        JSONObject obstructor = new JSONObject();
        JSONObject dimensions = new JSONObject();
        dimensions.put( "x", roomHeight );
        dimensions.put( "y", roomWidth );
        dimensions.put( "z", 5);
        obstructor.put( "weight", 0);
        obstructor.put( "color", "red" );
        JSONObject origin = new JSONObject();
        origin.put( "x", 0 );
        origin.put( "y", 0 );
        
        int distance = 8;
        int size = (roomLength-distance*2);
        
        //Put down aisles
        while(size >= 0)
        {
            if(size >= 10+unitLength*2)
            {
                origin.put( "z", distance-5 );
                temp = new JSONObject(obstructor);
                temp.put( "origin", new JSONObject(origin) );
                temp.put( "dimensions", dimensions );
                obstructors.add( new JSONObject(temp) );
                
                origin.put( "z", roomLength-distance );
                temp = new JSONObject(obstructor);
                temp.put( "origin", new JSONObject(origin) );
                temp.put( "dimensions", dimensions );
                obstructors.add( new JSONObject(temp) );
                
                distance+=10+unitLength*2;
            }
            else if(size > unitLength*2)
            {
                size = size-unitLength*2;
                size = size/2;
                
                origin.put( "z", distance-5 );
                temp = new JSONObject(obstructor);
                temp.put( "origin", new JSONObject(origin) );
                dimensions.put( "z", size );
                temp.put( "dimensions", new JSONObject(dimensions) );
                obstructors.add( new JSONObject(temp) );
                
                origin.put( "z", roomLength-distance );
                temp = new JSONObject(obstructor);
                temp.put( "origin", new JSONObject(origin) );
                dimensions.put( "z", size );
                temp.put( "dimensions", new JSONObject(dimensions) );
                obstructors.add( new JSONObject(temp) );
                
                break;
            }
            else if(size == unitLength*2)
            {
                break;
            }
            else
            {
                origin.put( "z", distance-5 );
                temp = new JSONObject(obstructor);
                temp.put( "origin", new JSONObject(origin) );
                dimensions.put( "z", size );
                temp.put( "dimensions", new JSONObject(dimensions) );
                obstructors.add( new JSONObject(temp) );
                
                break;
            }
            size = (roomLength-distance*2);
        }
        dimensions.put( "z", 5 );
        
        room.put( "items", obstructors );*/
        
        JSONArray rooms = new JSONArray();
        rooms.add( room );
        data.put( "boxTypes", rooms );
        
        //Make modules
        JSONObject module = new JSONObject();
        module.put( "weight", 0 );
        module.put( "color", "green" );
        module.put( "quantity", 10000);
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