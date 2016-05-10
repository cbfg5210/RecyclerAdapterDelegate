package com.fubaisum.sample;

import android.app.Activity;

import com.fubaisum.adapterdelegate.AbsDelegationAdapter;

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

        OneDelegate oneDelegate = new OneDelegate(activity);
        TwoDelegate twoDelegate = new TwoDelegate(activity);
        ThreeDelegate threeDelegate = new ThreeDelegate(activity);

        addDelegate(oneDelegate);
        addDelegate(twoDelegate);
        addDelegate(threeDelegate);
    }


}
