package ru.itschool.textquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    EditText name;
    private Character manager;
    private Story story;
    private TextView storyText, statisticsText;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        manager = new Character(name);
        story = new Story(manager);
        storyText = findViewById(R.id.storyText);
        radioGroup = findViewById(R.id.radioGroup);
        statisticsText = findViewById(R.id.statisticsText);
        nextSituation();
    }

    void nextSituation() {
        setTitle(story.currentSituation.subject);
        manager.A += story.currentSituation.dA;
        manager.K += story.currentSituation.dK;
        manager.R += story.currentSituation.dR;
        statisticsText.setText("A = " + manager.A + "; K = " + manager.K + "; R = " + manager.R + ".");
        storyText.setText(story.currentSituation.text);
        radioGroup.removeAllViews();
        for (int i = 0; i < story.getDirectionsLength(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(String.valueOf(i + 1));
            radioGroup.addView(radioButton);
        }
    }

    public void onClick(View view) {
        if (story.isEnd()) {
            Intent intent = new Intent(this, TheEndActivity.class);
            startActivity(intent);
            return;
        }
        int checkedRadioButton = -1;
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            checkedRadioButton = radioButton.isChecked() ? i : checkedRadioButton;
        }
        if (checkedRadioButton == -1) {
            Toast.makeText(this, "Вы не выбрали ни один возможный вариант!", Toast.LENGTH_LONG).show();
            return;
        }
        story.go(checkedRadioButton);
        nextSituation();
    }
}