package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import okhttp3.*;

import java.io.IOException;


public class Controller {
    String url = "http://172.20.11.100/api/z2YrJsBIMyPZlHWprsFmIjlfI2WaR9kxTHA6XVaI/groups/1/action";
    String json;
    private String res = "";

    @FXML
    public void onStartButtonClick(ActionEvent event) {
        System.out.println("start");
        try {
            for (int i = 0; i <= 360; i += 10) {
                System.out.println(i);
                String bri = String.valueOf((int)(127*Math.sin(Math.toRadians(i))+127));
                json = "{\"on\":true,\"hue\":14988,\"bri\":" + bri + ",\"sat\":141}";
                postTest();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void onStopButtonClick(ActionEvent event) {
        System.out.println("stop");
        json = "{\"on\":true,\"hue\":14988,\"bri\":254,\"sat\":141}";
        postTest();

    }

    public void postTest() {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();


        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                res = response.body().string();
                System.out.println(res);
            }
        });
    }


}
