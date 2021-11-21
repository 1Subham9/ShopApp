package com.ahir.testapp.data;

import android.util.Log;

import com.ahir.testapp.controller.AppController;
import com.ahir.testapp.model.ListItem;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    ArrayList<ListItem> listItemArrayList = new ArrayList<>();
     private String url = "https://fakestoreapi.com/products";

//     Below we are returning the list of items
     public ArrayList<ListItem> getListItem(final ItemListAsyncResponse callBack){

         //Below we have code for jsonArray data
//        queue = AppController.getInstance(this.getApplicationContext())
//                .getRequestQueue();
//      Above if we pass argument in the instance
//        queue = Volley.newRequestQueue(this); {without using singleton class}


         JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url
                 , null, response -> {

             for(int i=0;i<response.length();i++){
                 try {

                     //                    Doing in object form
                     JSONObject jsonObject = response.getJSONObject(i);

//                     Initiating model constructor
                     ListItem listItem = new ListItem(jsonObject.getString("title"),
                             jsonObject.getString("description"),
                             jsonObject.getInt("price"),
                             jsonObject.getString("category"),
                             jsonObject.getString("image"));


//                     add items to list items
//                     Each time we loop through we add a new item to the list
                     listItemArrayList.add(listItem);

//                      For Outputting ArrayList
//                     Log.d("Repo","onCreate :"+jsonObject.getInt("id"));
//                     Log.d("Repo","onCreate :"+jsonObject.getString("title"));

//                     Log.d("Repo","on Create "+response.getJSONArray(i)); for array type

                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
             if(null != callBack) callBack.processFinished(listItemArrayList);

         }, error -> {
             Log.d("JSON", " Failed to get data");
         });

//        queue.add(jsonArrayRequest); if we pass context through instances

         AppController.getInstance().addToRequestQueue(jsonArrayRequest);

         return listItemArrayList;
     }
}
