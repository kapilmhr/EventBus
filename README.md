
# EventBus
EventBus is an open-source library for Android and Java using the publisher/subscriber pattern for loose coupling. EventBus enables central communication to decoupled classes with just a few lines of code – simplifying the code, removing dependencies, and speeding up app development.
EventBus-Android-Publish-Subscribe

#### Benefits of using EventBus:
- simplifies the communication between components
- decouples event senders and receivers
- performs well with UI artifacts (e.g.  Activities, Fragments) and background threads
- avoids complex and error-prone dependencies and life cycle issues
- is fast; specifically optimized for high performance
- is tiny (<50k jar)
- is proven in practice by apps with 100,000,000+ installs
- has advanced features like delivery threads, subscriber priorities, etc.

#### Further EventBus Features
- <b>Convenient Annotation based API: Convenient Annotation based API:</b> Simply put the @Subscribe annotation to your subscriber methods. Because of a build time indexing of annotations, EventBus does not need to do annotation reflection during your app’s run time.
- <b>Android main thread delivery:</b> When interacting with the UI, EventBus can deliver events in the main thread regardless how an event was posted.
- <b>Background thread delivery:</b> If your subscriber does long running tasks, EventBus can also use background threads to avoid UI blocking.
- <b>Event & Subscriber inheritance:</b> In EventBus, the object oriented paradigm apply to event and subscriber classes. Let’s say event class A is the superclass of B. Posted events of type B will also be posted to subscribers interested in A. Similarly the inheritance of subscriber classes are considered.
- <b>Jump start:</b>You can get started immediately – without the need to configure anything using a default EventBus instance available from anywhere in your code.
- <b>Configurable:</b> To tweak EventBus to your requirements, you can adjust its behavior using the builder pattern.

# EventBus
A simple demo on how event bus can be used.


#### Add EventBus to your project

Gradle:

```java
implementation 'org.greenrobot:eventbus:3.1.1'
```

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


#### Add RxBus to your project

Gradle:

```java
implementation 'com.github.kapilmhr:RxBus:1.0'
```

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


    
