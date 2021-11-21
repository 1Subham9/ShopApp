package com.ahir.testapp.data;

import com.ahir.testapp.model.ListItem;

import java.util.ArrayList;

public interface ItemListAsyncResponse {
    void processFinished(ArrayList<ListItem> listItemArrayList);
}
