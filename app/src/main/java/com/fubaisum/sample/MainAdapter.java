package com.fubaisum.sample;

import android.app.Activity;

import com.fubaisum.adapterdelegate.AbsDelegationAdapter;
import com.fubaisum.sample.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sum on 5/10/16.
 */
public class MainAdapter extends AbsDelegationAdapter<Item> {

    private Activity activity;

    public MainAdapter(Activity activity, List<Item> items) {
        this.activity = activity;
        this.items = items != null ? items : new ArrayList<Item>();

        ContentDelegate contentDelegate = new ContentDelegate(activity);
        ComplexDelegate complexDelegate = new ComplexDelegate(activity);
        ImageDelegate imageDelegate = new ImageDelegate(activity);

        addDelegate(contentDelegate);
        addDelegate(complexDelegate);
        addDelegate(imageDelegate);
    }


}
