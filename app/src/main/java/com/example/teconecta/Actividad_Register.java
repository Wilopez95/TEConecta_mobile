package com.example.teconecta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Actividad_Register extends AppCompatActivity {
    private EditText name;
    private EditText passport;
    private EditText email;

    private Button button;

    private MainController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad__register);


        mc =  MainController.getInstance();
        mc.setActiveContex(this);

        name = findViewById(R.id.name);
        passport = findViewById(R.id.passport);
        email = findViewById(R.id.email);

        button= findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkfields()){
                    //Registra
                    Toast.makeText(getApplicationContext(), "¡Su registro a la actividad se realizao con exito!", Toast.LENGTH_LONG).show();
                    String fkact = mc.getSelectecActivity().getID();
                    mc.registerAssistance(fkact,name.getText().toString(),email.getText().toString(),passport.getText().toString(),"Activo");
                    Intent intent = new Intent(getApplicationContext(), Actividad_Detalle.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "¡Datos incorrectos!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }



    private boolean checkfields(){
        if(name.getText().toString().isEmpty()|| passport.getText().toString().isEmpty() || email.getText().toString().isEmpty()){
            return false;
        }else {
            return true;
        }
    }
}
