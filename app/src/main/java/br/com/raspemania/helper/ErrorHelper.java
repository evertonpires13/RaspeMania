package br.com.raspemania.helper;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class ErrorHelper {

    public static void errorLogin(String error, Context context, EditText mEmailField, EditText mPasswordField) {

        String errorCode = error;

        switch (errorCode) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(context, "The custom token format is incorrect. Please check the documentation.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(context, "The custom token corresponds to a different audience.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(context, "The supplied auth credential is malformed or has expired.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                mEmailField.setError("The email address is badly formatted.");
                mEmailField.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                mPasswordField.setError("password is incorrect ");
                mPasswordField.requestFocus();
                mPasswordField.setText("");
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(context, "The supplied credentials do not correspond to the previously signed in user.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                Toast.makeText(context, "context operation is sensitive and requires recent authentication. Log in again before retrying context request.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(context, "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with context email address.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                mEmailField.setError("The email address is already in use by another account.");
                mEmailField.requestFocus();
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                Toast.makeText(context, "context credential is already associated with a different user account.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_DISABLED":
                Toast.makeText(context, "The user account has been disabled by an administrator.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                Toast.makeText(context, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(context, "There is no user record corresponding to context identifier. The user may have been deleted.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_USER_TOKEN":
                Toast.makeText(context, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                Toast.makeText(context, "context operation is not allowed. You must enable context service in the console.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_WEAK_PASSWORD":
                mPasswordField.setError("The password is invalid it must 6 characters at least");
                mPasswordField.requestFocus();
                break;

            case "GERAL":
                Toast.makeText(context, "Ocorreu um erro inesperado. Favor entrar em contato com raspemania@gmail.com", Toast.LENGTH_LONG).show();
                break;

            case "INVALID_USER":
                Toast.makeText(context, "Usuário está inativo", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_GET_USER":
                Toast.makeText(context, "Usuário está inativo", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CADASTRO_USER":
                Toast.makeText(context, "Erro ao cadastrar usuário!", Toast.LENGTH_LONG).show();
                break;

            default: Toast.makeText(context, "Ocorreu um erro inesperado. Favor entrar em contato com raspemania@gmail.com", Toast.LENGTH_LONG).show();
        }
    }
}
