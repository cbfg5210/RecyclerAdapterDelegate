# RecyclerAdapterDelegate
#### A handy library for using RecyclerView.

Modified from: <https://github.com/sockeqwe/AdapterDelegates.git/>

### Add repository 
```
repositories {
    maven {
        url 'https://dl.bintray.com/fubaisum/maven/'
    }
}
```
### Add dependency
```
    compile 'com.fubaisum.adapterdelegate:recycler-adapter-delegate:2.0.4'
```
### Usage
#### Create delegates
```
public class ContentDelegate extends AbsAdapterDelegate<Item> {

    public ContentDelegate(Activity activity) {
        super(activity, R.layout.layout_content);
    }

    @Override
    protected boolean isForViewType(Item item) {//判断当前Item，是否由ContentDelegate展示
        return item instanceof ContentItem;
    }

    @Override
    protected RecyclerViewHolder onCreateViewHolder(View itemView) {//创建ViewHolder
        return new RecyclerViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(RecyclerViewHolder holder, Item item) {

        ContentItem contentItem = (ContentItem) item;

        TextView tvContent = holder.getView(R.id.tv_main_item_content);
        tvContent.setText(contentItem.content != null ? contentItem.content : "Hello World!!!");
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

