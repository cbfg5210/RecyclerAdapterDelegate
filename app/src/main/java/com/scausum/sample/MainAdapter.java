package com.scausum.sample;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.scausum.adapterdelegate.DelegationAdapter;
import com.scausum.adapterdelegate.OnDelegateClickListener;
import com.scausum.sample.model.ComplexItem;
import com.scausum.sample.model.ContentItem;
import com.scausum.sample.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sum on 5/10/16.
 */
public class MainAdapter extends DelegationAdapter<Item> {

    private Activity activity;

    public MainAdapter(Activity activity, List<Item> items) {
        this.activity = activity;
        this.items = items != null ? items : new ArrayList<Item>();

        ContentDelegate contentDelegate = new ContentDelegate(activity);
        ImageDelegate imageDelegate = new ImageDelegate(activity);
        ComplexDelegate complexDelegate = new ComplexDelegate(activity);

        addDelegate(contentDelegate);
        addDelegate(imageDelegate);
        addDelegate(complexDelegate);

        contentDelegate.setOnDelegateClickListener(contentViewClickListener);
        complexDelegate.setOnDelegateClickListener(complexViewClickListener);
    }

    private OnDelegateClickListener contentViewClickListener = new OnDelegateClickListener() {

        @Override
        public void onClick(View child, int position) {
            if (position < 0 || position >= getItemCount()) {
                return;
            }
            ContentItem item = (ContentItem) items.get(position);
            switch (child.getId()) {
                case R.id.tv_main_item_content: {
                    Toast.makeText(activity, item.content, Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    };

    private OnDelegateClickListener complexViewClickListener = new OnDelegateClickListener() {

        @Override
        public void onClick(View child, int position) {
            if (position < 0 || position >= getItemCount()) {
                return;
            }
            ComplexItem item = (ComplexItem) items.get(position);
            switch (child.getId()) {
                case R.id.tv_main_item_content: {
                    Toast.makeText(activity, item.content, Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    };
}
