package br.com.raspemania.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import br.com.raspemania.R;
import br.com.raspemania.viewmodel.BaseViewModel;

    public class BaseActivity extends AppCompatActivity {

        protected void observeError(BaseViewModel viewModel){
            viewModel.error.observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @VisibleForTesting
        public ProgressDialog mProgressDialog;

        public void showProgressDialog() {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage(getString(R.string.loading));
                mProgressDialog.setIndeterminate(true);
            }

            mProgressDialog.show();
        }

        public void hideProgressDialog() {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }

        public void hideKeyboard(View view) {
            final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }

        @Override
        public void onStop() {
            super.onStop();
            hideProgressDialog();
        }
    }
