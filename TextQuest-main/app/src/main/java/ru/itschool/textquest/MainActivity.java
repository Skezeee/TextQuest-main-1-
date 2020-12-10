package ru.itschool.textquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
    }

    public void onClick(View view) {
        startActivity(new Intent(this, GameActivity.class));
        Intent intent = new Intent(getBaseContext(), GameActivity.class);
        intent.putExtra("name", name.getText().toString());
        startActivity(intent);
    }
}