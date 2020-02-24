package com.anioncode.viewmodelexample.ApiConnect;

import android.content.Context;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.OkHttpClient;

public class ReturnResponse {
    private ArrayList<ModelNews>  dataApi;

    public ArrayList<ModelNews> getDataApi() {
        return dataApi;
    }

    public void setDataApi(ArrayList<ModelNews> dataApi) {
        this.dataApi = dataApi;
    }

    public void  requestInit(){
        OkHttpClient client = new OkHttpClient();
        dataApi=new ArrayList<>();
        Request request = new Request.Builder()
                .url("https://free-football-soccer-videos.p.rapidapi.com/")
                .get()
                .addHeader("x-rapidapi-host", "free-football-soccer-videos.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "4877c410b9mshe7fe9517ac14094p1cd13ejsn6a566299c797")
                .build();
         newsData(request,client);

    }
    void newsData(Request request, OkHttpClient client) {

        try {

            Response response = client.newCall(request).execute();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String responseData = response.body().string();
                    //System.out.println(responseData);
                    String title = "";
                    String thumbnail = "";
                    String date = "";

                    try {
                        JSONArray json = new JSONArray(responseData);
                        for (int i = 0; i < json.length(); i++) {
                            JSONObject jsons = json.getJSONObject(i);
                            title = jsons.getString("title");
                            thumbnail = jsons.getString("thumbnail");
                            date = jsons.getString("date");

                            dataApi.add(new ModelNews(title, thumbnail, date));
                            System.out.println("HEJKA TU JESTEM"+dataApi.size());

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("HEJKA CRANK FUCK");
                    }finally {
                        response.close();
                        System.out.println("HEJKA TU JESTEM nupek"+dataApi.size());

                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("HEJKA TU JESTEM koniec"+dataApi.size());

    }
}
