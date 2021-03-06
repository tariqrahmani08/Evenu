package com.example.tonytea.evenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class CreateEventActivity extends AppCompatActivity {

    private EditText mEventTitleView;
    private EditText mEventLocation;
    private EditText mDate;
    private EditText mTime;
    private EditText mDescription;
    private EditText mKeywords;

    public DatabaseReference databaseReference;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);

        mEventTitleView = (EditText) findViewById(R.id.event_title);
        mEventLocation = (EditText) findViewById(R.id.event_location);
        mDate = (EditText) findViewById(R.id.event_date);
        mTime = (EditText) findViewById(R.id.event_time);
        mDescription = (EditText) findViewById(R.id.event_description);
        mKeywords = (EditText) findViewById(R.id.event_keywords);

        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    // view points to the button
    public void createEventAttempt (View view){

        String eventTitle = mEventTitleView.getText().toString();
        String eventLocation = mEventLocation.getText().toString();
        String eventDate = mDate.getText().toString();
        String eventTime = mTime.getText().toString();
        String eventDescription = mDescription.getText().toString();
        String eventKeywords = mKeywords.getText().toString();

        if(eventTitle.isEmpty()){
            Toast.makeText(this, "Please enter an event_list name", Toast.LENGTH_SHORT).show();
            return;
        }

        else if(eventLocation.isEmpty()){
            Toast.makeText(this, "Please enter an event_list location", Toast.LENGTH_SHORT).show();
            return;
        }

        else if(eventDate.isEmpty()){
            Toast.makeText(this, "Please enter a valid date", Toast.LENGTH_SHORT).show();
            return;
        }

        else if(eventTime.isEmpty()){
            Toast.makeText(this, "Please enter a time", Toast.LENGTH_SHORT).show();
            return;
        }

        else if(eventDescription.isEmpty()){
            Toast.makeText(this, "Please enter an event_list description", Toast.LENGTH_SHORT).show();
            return;
        }

        else if(eventKeywords.isEmpty()){
            Toast.makeText(this, "Please enter an event_list keywords", Toast.LENGTH_SHORT).show();
            return;
        }

        else{

            String id = databaseReference.child("events").push().getKey();

            Event newEvent = new Event(eventTitle, eventLocation, eventDate,
                    eventTime, eventDescription, eventKeywords, id);

            databaseReference.child("events").child(id).setValue(newEvent);
        }


    }

}
