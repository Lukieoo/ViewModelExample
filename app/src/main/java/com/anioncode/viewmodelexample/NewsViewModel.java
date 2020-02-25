package com.anioncode.viewmodelexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {


    //this is the data that we will fetch asynchronously
    public   MutableLiveData<List<ModelNews>> ItemList;
    private ArrayList<ModelNews> datas = new ArrayList<>();

    //we will call this method to get the data
    public LiveData<List<ModelNews>> getHeroes() {
        //if the list is null
        if (ItemList == null) {
            ItemList = new MutableLiveData<List<ModelNews>>();
            //we will load it asynchronously from server in this method
           // loadHeroes();
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
    public void SetItem(List<ModelNews> datas2) {
        //if the list is null

        ItemList.setValue(datas2);
        //finally we will return the list

    }
}