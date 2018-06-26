package app.frantic.eventbuswithrx;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import app.frantic.mylibrary.Bus;
import app.frantic.mylibrary.BusProvider;
import app.frantic.mylibrary.Subscribe;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    TextView textView;
    Button btn;
    Bus bus;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bus = BusProvider.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_first, container, false);
        textView = view.findViewById(R.id.tv_text);
        btn = view.findViewById(R.id.btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bus.post(new ButtonEvent("Message from First Fragment"));
            }
        });


    }

    @Subscribe
    public void onEvent(MainActivity.ButtonFirstEvent event) {
        // TODO: Do something
        textView.setText(event.message);
    }

    @Override
    public void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        bus.unregister(this);
    }
}
