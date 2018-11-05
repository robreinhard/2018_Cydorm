package com.cydorm.cydorm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.support.v4.app.DialogFragment;

import java.util.Calendar;
import java.util.Collections;
import java.util.Map;

public class AddChoreActivity extends AppCompatActivity {

    EditText choreText;
    EditText dateText;
    Button submitBtn;
    int yearC;
    int monthC;
    int dateC;
    StompConnection sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chore);

        this.sc = new StompConnection("8B8CD50EF9319D75C54BB3489A8810D3");


        choreText = (EditText) findViewById(R.id.chore_name_input);
        dateText = (EditText) findViewById(R.id.chore_date_picker);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        submitBtn = (Button)findViewById(R.id.chore_submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String json = String.format( "{ \"cItem\" : \"%s\", " +
                                "\"date\":\"%d\", \"month\":\"%d\", \"year\":\"%d\", " +
                                "\"studentID\":\"%s\" }", choreText.getText(),
                        dateC, monthC, yearC, "mjboyd");

                sc.makeRequest("/addChore", json);
                finish();
            }
        });

    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            return  dialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            setEdit(year, month, day);
        }

        private void setEdit(int year, int month, int day) {
            EditText et =
                    (EditText)getActivity().findViewById(R.id.chore_date_picker);
            et.setText(String.format("%d-%d-%d", year, month, day));
            ((AddChoreActivity)getActivity()).yearC = year;
            ((AddChoreActivity)getActivity()).monthC = month;
            ((AddChoreActivity)getActivity()).dateC = day;

        }
    }

}
