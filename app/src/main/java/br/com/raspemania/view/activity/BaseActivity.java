package br.com.raspemania.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import br.com.raspemania.R;
import br.com.raspemania.helper.ConstantHelper;
import br.com.raspemania.viewmodel.BaseViewModel;

    public class BaseActivity extends AppCompatActivity {

        String[] mStatus = { ConstantHelper.ATIVO_STR, ConstantHelper.INATIVO_STR};
        String[] mPerfil = { ConstantHelper.PERFIL_ADM_STR, ConstantHelper.PERFIL_COLABORADOR_STR };

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

        public void spinnerStatus(Spinner spinner) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), R.layout.item_spinner_default, mStatus);
            spinner.setAdapter(adapter);
        }

        public void spinnerPerfil(Spinner spinner) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), R.layout.item_spinner_default, mPerfil);
            spinner.setAdapter(adapter);
        }

        public int getStatusSpinner(String itemSelected) {
            if(itemSelected.equalsIgnoreCase(ConstantHelper.ATIVO_STR)){
                return ConstantHelper.ATIVO;
            } else {
                return ConstantHelper.INATIVO;
            }
        }

        public int getPerfilSpinner(String itemSelected) {
            if(itemSelected.equalsIgnoreCase(ConstantHelper.PERFIL_ADM_STR)){
                return ConstantHelper.PERFIL_ADM;
            } else {
                return ConstantHelper.PERFIL_COLABORADOR;
            }
        }

        public int setSpinner(long itemSelected) {
            if(itemSelected == ConstantHelper.ATIVO || itemSelected == ConstantHelper.PERFIL_ADM){
                return 0;
            } else {
                return 1;
            }
        }

    }
