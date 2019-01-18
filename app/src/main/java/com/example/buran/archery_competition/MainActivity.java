package com.example.buran.archery_competition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.buran.archery_competition.Participants.ParticipantsActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnParticipants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnParticipants = (Button) findViewById(R.id.btn_participant);
        btnParticipants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParticipantsActivity.class);
                startActivity(intent);
            }
        });
    }
}
