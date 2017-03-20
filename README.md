# RecyclerAdapterDelegate
#### A handy library to support multiple item view types for RecyclerView.

Based on <https://github.com/sockeqwe/AdapterDelegates.git/>

# Add repository 
```gradle
repositories {
    maven { url 'https://dl.bintray.com/scausum/maven' }
}
```
# Add dependency
```gradle
    compile 'com.scausum.adapterdelegate:recycler-adapter-delegate:0.4.0'
```
# Screenshot
![Image](https://github.com/fubaisum/RecyclerAdapterDelegate/blob/master/art/main.png)
# Usage
#### Create delegates
```java
public class ContentDelegate extends AdapterDelegate<Item> {

    public ContentDelegate(Activity activity) {
        super(activity);
    }

    @Override
    public boolean isForViewType(@NonNull Item item) {
        return item instanceof ContentItem;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        View itemView = layoutInflater.inflate(R.layout.layout_content, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull Item item, @NonNull List<Object> payloads) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        onDelegateClickListener.setViewHolder(viewHolder);
        ContentItem contentItem = (ContentItem) item;
        viewHolder.tvContent.setOnClickListener(onDelegateClickListener);
        viewHolder.tvContent.setText(contentItem.content != null ? contentItem.content : "Hello World!!!");

    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);

            tvContent = (TextView) itemView.findViewById(R.id.tv_main_item_content);
        }
    }

}

```
#### Create adapter
```java
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
```
#### Set adapter
```java
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
# Thanks
* <https://github.com/sockeqwe/AdapterDelegates.git/>

# License
```
Copyright 2016 Jiajie Shen.

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
