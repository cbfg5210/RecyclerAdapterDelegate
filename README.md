# RecyclerAdapterDelegate
#### A handy library to support multiple item view types for RecyclerView.

Modified from <https://github.com/sockeqwe/AdapterDelegates.git/>

# Add repository 
```
repositories {
    maven {
        url 'https://dl.bintray.com/fubaisum/maven/'
    }
}
```
# Add dependency
```
    compile 'com.fubaisum.adapterdelegate:recycler-adapter-delegate:2.2.2'
```
# Screenshot
![Image](https://github.com/fubaisum/RecyclerAdapterDelegate/blob/master/art/main.png)
# Usage
#### Create delegates
```
public class ImageDelegate extends AbsAdapterDelegate<Item, ImageDelegate.ViewHolder> {

    public ImageDelegate(Activity activity) {
        super(activity, R.layout.layout_image);
    }

    @Override
    public boolean isForViewType(@NonNull Item item) {
        return item instanceof ImageItem;
    }

    @Override
    protected ViewHolder onCreateViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull Item item, @NonNull ViewHolder holder) {
        ImageItem imageItem = (ImageItem) item;

        holder.ivIcon.setImageResource(imageItem.imageRes != 0 ? imageItem.imageRes : R.mipmap.ic_launcher);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            ivIcon = (ImageView) itemView.findViewById(R.id.iv_main_item_icon);
        }
    }

}

```
#### Create adapter
```
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
```
#### Set adapter
```
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        List<Item> items = new ArrayList<>();
        items.add(new ContentItem());
        items.add(new ImageItem());
        items.add(new ComplexItem());
        items.add(new ComplexItem());
        items.add(new ImageItem());
        items.add(new ContentItem());

        mainAdapter = new MainAdapter(this, items);
        recyclerView.setAdapter(mainAdapter);
```
#### Set onDelegateClickListener
Set the onDelegateClickListener can easily handle the view(child of RecyclerView itemView) click event in DelegationAdapter.

# License
```
Copyright 2016 fubaisum.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
