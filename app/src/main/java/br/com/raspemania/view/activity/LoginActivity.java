package br.com.raspemania.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import br.com.raspemania.R;
import br.com.raspemania.helper.ErrorHelper;
import br.com.raspemania.viewmodel.ColaboradorViewModel;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private EditText mEmailField;
    private EditText mPasswordField;
    private FirebaseAuth mAuth;

    private ColaboradorViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailField = findViewById(R.id.fieldEmail);
        mPasswordField = findViewById(R.id.fieldPassword);

        findViewById(R.id.emailSignInButton).setOnClickListener(this);
        findViewById(R.id.cadastrarNovoText).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mViewModel = ViewModelProviders.of(this).get(ColaboradorViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPasswordField.getText().clear();
        mEmailField.getText().clear();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        doLogin(currentUser);
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            doLogin(user);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException().getCause());
                            ErrorHelper.errorLogin("GERAL", LoginActivity.this, mEmailField, mPasswordField);
                        }
                        if (!task.isSuccessful()) {
                            ErrorHelper.errorLogin(((FirebaseAuthException) task.getException()).getErrorCode(), LoginActivity.this, mEmailField, mPasswordField);
                        }
                        hideProgressDialog();
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }
    private void doLogin(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            //TODO tratar retorno de erro
            startActivity(new Intent(this, MainActivity.class));


            //mViewModel.getByEmail(user.getEmail());
            /*if(mColaborador.status == ConstantHelper.INATIVO) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                Toast.makeText(this, "Usuário está inativo", Toast.LENGTH_LONG).show();
            } else {
                startActivity(new Intent(this, MainActivity.class));
            }*/
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.emailSignInButton) {
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.cadastrarNovoText) {
            startActivity(new Intent(this, CadastroUsuarioActivity.class));
        }
    }
}