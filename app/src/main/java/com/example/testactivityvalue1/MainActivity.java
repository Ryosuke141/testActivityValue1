package com.example.testactivityvalue1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "YourPackageName.MESSAGE";

    private TextView textView;
    static final int RESULT_SUBACTIVITY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);

        final EditText editText= findViewById(R.id.edit_text);

        Button button = findViewById(R.id.button);
        button.setOnClickListener( v -> {
            Intent intent = new Intent(getApplication(), SubActivity.class);
            if(editText.getText() != null){
                String str = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, str);
            }
            startActivityForResult( intent, RESULT_SUBACTIVITY );

            editText.setText("");
        });
    }

    protected void onActivityResult( int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == RESULT_OK && requestCode == RESULT_SUBACTIVITY &&
                null != intent) {
            String res = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            textView.setText(res);
        }
    }
}