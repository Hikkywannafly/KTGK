package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notesapp.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesActivity extends AppCompatActivity {

    EditText editText_tittle,editText_notes, editText_pass, editText_pass2;

    Button button;
    Notes notes;
    boolean is_old_note=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        editText_tittle=findViewById(R.id.edit_text_user);
        editText_notes=findViewById(R.id.edit_text_email);
        button = findViewById(R.id.button);
        editText_pass=findViewById(R.id.edit_text_pass);
        editText_pass2 = findViewById(R.id.edit_text_pass_2);
        is_old_note=true;

        notes=new Notes();
        try {
            notes= (Notes) getIntent().getSerializableExtra("old_note");
            editText_tittle.setText(notes.getUser());
            editText_notes.setText(notes.getEmail());

        }catch (Exception e){
            e.printStackTrace();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tittle=editText_tittle.getText().toString();
                String description=editText_notes.getText().toString();
                String pass = editText_pass.getText().toString();
                String pass2 = editText_pass2.getText().toString();

                if (description.isEmpty()){
                    Toast.makeText(NotesActivity.this, "Vui lòng nhập nội dung !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat format=new SimpleDateFormat("EEE,d MMM yyyy HH : mm a");
                Date date=new Date();

                if (is_old_note){
                    notes=new Notes();
                }
                if (pass.equals(pass2)){
                    notes.setPass(pass);
                }else {
                    Toast.makeText(NotesActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }

                notes.setUser(tittle);
                notes.setEmail(description);
                notes.setPass(pass2);
                notes.setDate(format.format(date));

                Intent intent= new Intent();
                intent.putExtra("note",notes);
                setResult(Activity.RESULT_OK,intent);
                finish();




            }
        });
    }
}