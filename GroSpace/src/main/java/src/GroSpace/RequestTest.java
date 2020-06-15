package src.GroSpace;

import org.json.simple.JSONObject;

public class RequestTest
{
    public static void main(String[] args)
    {
        Request r = new Request();
        r.addData( 5, 5, 5, 1, 1, 1 );
        System.out.println(r.getData());
        JSONObject obj = r.sendRequest(); 
        System.out.println(obj.get( "svgs" ));
    }
}
