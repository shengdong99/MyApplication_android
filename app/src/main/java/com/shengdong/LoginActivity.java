package com.shengdong;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shengdong.recyclerviewtest.Model.HelperClass;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private Button btn_iniciar;
    private String parentDbNameadmin = "admin";
    private String parentDbName = "users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_iniciar = findViewById(R.id.btn_iniciar);

        mAuth = FirebaseAuth.getInstance();

        email.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);
    }

        private TextWatcher loginTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailInput = email.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();

                btn_iniciar.setEnabled(!emailInput.isEmpty() && !passwordInput.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };


    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();



    }

    public void Iniciar(View view){
        String emails = email.getText().toString();
        String passwords = password.getText().toString();


        mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                             FirebaseUser user = mAuth.getCurrentUser();
                            //AllowAccessToAccount(emails, passwords);


                           Toast.makeText(getApplicationContext(), "Authentication correcta.",
                                    Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                            startActivity(i);


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                });

    }



    public void irRegistrar(View view){
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    /*private void AllowAccessToAccount(final String emails, final String passwords)
    {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                    HelperClass usersData = dataSnapshot.child(parentDbName).child("emails").getValue(HelperClass.class);
                        if(usersData.getEmail().equals(emails))
                        {
                        if (usersData.getPassword().equals(passwords))
                        {
                            if (parentDbName.equals("admin"))
                            {
                                Toast.makeText(LoginActivity.this, "Welcome Admin, you are logged in Successfully...", Toast.LENGTH_SHORT).show();


                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                            else if (parentDbName.equals("users"))
                            {
                                Toast.makeText(LoginActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();


                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                //Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                            }
                        }
                        else
                        {

                            Toast.makeText(LoginActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/


   /* public void checkUser(View view) {
        String userUsername = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("name").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    email.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userPassword)) {
                        email.setError(null);
                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        //String emailFromDB = snapshot.child(userUsername).getValue(String.class);
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("name", nameFromDB);
                        //intent.putExtra("email", emailFromDB);
                        intent.putExtra("password", passwordFromDB);
                        Toast.makeText(getApplicationContext(), "Authentication correcta.",
                                Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }else
                            email.setError(null);
                            String passwordFromDB2 = snapshot.child(userUsername).child("password").getValue(String.class);
                            if (passwordFromDB2.equals(userPassword)) {
                                email.setError(null);
                                String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                                //String emailFromDB = snapshot.child(userUsername).getValue(String.class);
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.putExtra("name", nameFromDB);
                                //intent.putExtra("email", emailFromDB);
                                intent.putExtra("password", passwordFromDB2);
                                Toast.makeText(getApplicationContext(), "Authentication correcta.",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                    }

                    else {
                        password.setError("Invalid Credentials");
                        password.requestFocus();
                    }
                } else {
                    email.setError("User does not exist");
                    email.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }*/

}