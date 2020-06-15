package src.GroSpace;

import org.json.simple.JSONObject;

public class RequestInitializer {
    Request r;
    public RequestInitializer(int length, int width, int height) {
        Request r = new Request();
        r.addData(height, width, length, 2, 4, 3);
        JSONObject APIResponse = r.sendRequest();
    }
}