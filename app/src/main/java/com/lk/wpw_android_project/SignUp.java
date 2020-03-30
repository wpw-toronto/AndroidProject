
package com.lk.wpw_android_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.lk.wpw_android_project.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtPassword,edtConfirmPassword,edtEmail;
    Button btnSignUp;
    FirebaseAuth firebaseAuth;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtEmail = (MaterialEditText)findViewById(R.id.edtEmail);
        edtConfirmPassword = (MaterialEditText)findViewById(R.id.edtConfirmPassword);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        firebaseAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);

                final String email = edtEmail.getText().toString();
                final String password = edtPassword.getText().toString();
                final String confirmPassword = edtConfirmPassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignUp.this, "Please enter the email.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignUp.this, "Please enter the password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length() <= 6){
                    Toast.makeText(SignUp.this, "Password must be >= 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!confirmPassword.equals(password)){
                    Toast.makeText(SignUp.this,"Password Not matching",Toast.LENGTH_SHORT).show();
                    return;
                }

                mDialog.setMessage("Please waiting.....");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            mDialog.dismiss();

                            firebaseAuth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                uid = task.getResult().getUser().getUid();
                                                User user = new User (edtEmail.getText().toString(),edtPassword.getText().toString());
                                                table_user.child(uid).setValue(user);
                                                Toast.makeText(SignUp.this, "Sign up successfully!", Toast.LENGTH_SHORT).show();
                                                Intent mainPage = new Intent(SignUp.this,Home.class);
                                                startActivity(mainPage);
                                                finish();
                                            }else{
                                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
