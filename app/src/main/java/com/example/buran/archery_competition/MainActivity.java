package com.example.buran.archery_competition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.buran.archery_competition.BowClass.BowClassActivity;
import com.example.buran.archery_competition.Participants.ParticipantsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnParticipants;
    private Button btnBowClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnParticipants = (Button) findViewById(R.id.btn_participant);
        btnParticipants.setOnClickListener(this);

        btnBowClass = (Button) findViewById(R.id.btn_bow_class);
        btnBowClass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_participant:
                Intent intentParticipants = new Intent(MainActivity.this, ParticipantsActivity.class);
                startActivity(intentParticipants);
                break;
            case R.id.btn_bow_class:
                Intent intentBowClass = new Intent(MainActivity.this, BowClassActivity.class);
                startActivity(intentBowClass);

        }

    }
}
