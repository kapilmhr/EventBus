# EventBus
A simple demo on how event bus can be used.


EventBus in 3 steps
-------------------
1. Define events:

    ```java  
    public static class MessageEvent { /* Additional fields if needed */ }
    ```

2. Prepare subscribers:
    Declare and annotate your subscribing method, optionally specify a [thread mode](http://greenrobot.org/eventbus/documentation/delivery-threads-threadmode/):  

    ```java
    @Subscribe(threadMode = ThreadMode.MAIN)  
    public void onMessageEvent(MessageEvent event) {/* Do something */};
    ```
    Register and unregister your subscriber. For example on Android, activities and fragments should usually register according to their life cycle:

   ```java
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
 
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    ```

3. Post events:

   ```java
    EventBus.getDefault().post(new MessageEvent());
    ```
    