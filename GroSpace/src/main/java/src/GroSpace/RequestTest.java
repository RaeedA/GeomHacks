package src.GroSpace;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RequestTest
{
    public static void main(String[] args)
    {
        Request r = new Request();
        r.addData( 10, 25, 100, 2, 4, 3 );
        System.out.println(r.getData());
        JSONObject obj = r.sendRequest(); 
        System.out.println(( (JSONObject)( (JSONArray)obj.get( "images" ) ).get( 0 ) ).get("data"));
    }
}
