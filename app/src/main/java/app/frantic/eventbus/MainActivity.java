package app.frantic.eventbus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn);

        FragmentManager manager = getSupportFragmentManager();
        FirstFragment firstFragment = new FirstFragment();
        manager.beginTransaction()
                .replace(R.id.container, firstFragment)
                .commit();

        SecondFragment secondFragment = new SecondFragment();
        manager.beginTransaction()
                .replace(R.id.container1, secondFragment)
                .commit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ButtonFirstEvent("BFMV"));
            }
        });
    }


    public static class ButtonFirstEvent{
        String message;

        public ButtonFirstEvent(String message) {
            this.message = message;
        }
    }

}
