package com.shengdong;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shengdong.recyclerviewtest.Model.Products;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText passwordConfirm;
    private Button btn_registrar2;
    FirebaseDatabase database;
    DatabaseReference reference;

    Products user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password =(EditText)  findViewById(R.id.password);
        passwordConfirm =(EditText)  findViewById(R.id.passwordConfirm);
        btn_registrar2 = (Button) findViewById(R.id.btn_registrar2);



        name.addTextChangedListener(loginTextWatcher);
        email.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);
        passwordConfirm.addTextChangedListener(loginTextWatcher);


        // initializing our object
        // class variable.
        user = new Products();

    }
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String emailInput = email.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();
            String passwordConfirmInput = passwordConfirm.getText().toString().trim();
            String nameInput = name.getText().toString().trim();


            btn_registrar2.setEnabled(!nameInput.isEmpty() && !emailInput.isEmpty() && !passwordInput.isEmpty() && !passwordConfirmInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);


    }

   public void registrarUsuario(View view){


        if(password.getText().toString().equals(passwordConfirm.getText().toString())){

            mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            String id = mAuth.getCurrentUser().getUid();
                            Map<String, Object> map = new HashMap<>();
                            map.put("name", name);
                            map.put("email", email);
                            map.put("password", password);




                            if (task.isSuccessful()) {

                                database = FirebaseDatabase.getInstance();
                                reference = database.getReference("users");

                                String nameInput = name.getText().toString();
                                String emailInput = email.getText().toString();
                                String passwordInput = password.getText().toString();

                                HelperClass helperClass = new HelperClass(nameInput, emailInput, passwordInput);
                                reference.child(nameInput).setValue(helperClass);

                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getApplicationContext(), "Usuario creado", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(i);
                                //updateUI(user);


                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
        }else{
            Toast.makeText(this, "La contraseña no coinciden",Toast.LENGTH_SHORT).show();
        }





    }


    /*public void ValidateLogin(String email, String name, TextView password){
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(email).exists())){
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("email", email);
                    userdataMap.put("name", name);
                    userdataMap.put("password", password);


                    RootRef.child("Users").child(email).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(i);
                                    }
                                    else{
                                        Toast.makeText(RegisterActivity.this, "La contraseña no coinciden",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/

    public void irIniciar(View view){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}