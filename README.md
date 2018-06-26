

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
-------------------



RxBus
===========

Event bus based on RxJava and optimized for Android.

Usage
-------

We recommend obtaining the single instance of bus through injection or another appropriate mechanism.

Or get singleton like following:

```java
Bus bus = BusProvider.getInstance();
```

#### Subscribing

To subscribe to an event, declare and annotate a method with @Subscribe. The method should be public and take only a single parameter.

```java
@Subscribe
public void onEvent(SomeEvent event) {
    // TODO: Do something
}
```

You can also create subscription like following:

```java
CustomSubscriber<SomeEvent> customSubscriber = bus.obtainSubscriber(SomeEvent.class,
    new Consumer<SomeEvent>() {
        @Override
        public void accept(SomeEvent someEvent) throws Exception {
            // TODO: Do something
        }
    })
    .withFilter(new Predicate<SomeEvent>() {
        @Override
        public boolean test(SomeEvent someEvent) throws Exception {
            return "Specific message".equals(someEvent.message);
        }
    })
    .withScheduler(Schedulers.trampoline());
```

#### Register and unregister your observer

To receive events, a class instance needs to register with the bus.

```java
bus.register(this);
```

The customSubscriber also needs to register with the bus.

```java
bus.registerSubscriber(this, customSubscriber);
```

Remember to also call the unregister method when appropriate.
```java
bus.unregister(this);
```

#### Publishing

To publish a new event, call the post method:

```java
bus.post(new SomeEvent("Message"));
```

#### Add RxBus to your project

Gradle:

```java
implementation 'com.github.kapilmhr:RxBus:1.0'
```
    
