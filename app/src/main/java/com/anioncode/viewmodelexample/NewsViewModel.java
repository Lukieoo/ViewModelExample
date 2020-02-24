package com.anioncode.viewmodelexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anioncode.viewmodelexample.ApiConnect.ModelNews;
import com.anioncode.viewmodelexample.ApiConnect.ReturnResponse;

import java.util.ArrayList;
import java.util.List;

import static com.anioncode.viewmodelexample.MainActivity.swipeRefreshLayoutNews;

public class NewsViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<ModelNews>> ItemList;
    private ArrayList<ModelNews> datas = new ArrayList<>();

    //we will call this method to get the data
    public LiveData<List<ModelNews>> getHeroes() {
        //if the list is null
        if (ItemList == null) {
            ItemList = new MutableLiveData<List<ModelNews>>();
            //we will load it asynchronously from server in this method
            loadHeroes();
        }

        //finally we will return the list
        return ItemList;
    }

    public void addItem(ModelNews modelNews) {
        //if the list is null
        datas.add(modelNews);
        //finally we will return the list

    }

    //This method is using Retrofit to get the JSON data from URL
    public void loadHeroes() {


                swipeRefreshLayoutNews.setRefreshing(false);

                ReturnResponse response = new ReturnResponse();

                response.requestInit();

                datas = response.getDataApi();




    }
}