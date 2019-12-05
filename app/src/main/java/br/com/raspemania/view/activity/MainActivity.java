package br.com.raspemania.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import br.com.raspemania.R;
import br.com.raspemania.helper.ConstantHelper;
import br.com.raspemania.helper.SharedPrefHelper;
import br.com.raspemania.model.entidade.Colaborador;


public class MainActivity extends BaseActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private FirebaseAuth mAuth;
    private Colaborador mColaborador;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideProgressDialog();

        mAuth = FirebaseAuth.getInstance();

        mColaborador = SharedPrefHelper.getSharedOBJECT(this, ConstantHelper.COLABORADOR_PREF, Colaborador.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.nova_leitura);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), LeituraActivity.class));
                //Snackbar.make(view, "Nova leitura", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);

        carregaMenu();

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_colaborador, R.id.nav_estabelecimento,
                R.id.nav_leitura, R.id.nav_relatorio, R.id.nav_rota, R.id.nav_produto)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(mNavigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            mAuth.signOut();
            SharedPrefHelper.clearShared(this);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void carregaMenu(){

        if(mColaborador.perfil == ConstantHelper.PERFIL_ADM){

            Menu menuNav= mNavigationView.getMenu();

            MenuItem relatorioMenu = menuNav.findItem(R.id.nav_relatorio);
            relatorioMenu.setVisible(true);

            MenuItem colaboradorMenu = menuNav.findItem(R.id.nav_colaborador);
            colaboradorMenu.setVisible(true);

            MenuItem estabelecimentoMenu = menuNav.findItem(R.id.nav_estabelecimento);
            estabelecimentoMenu.setVisible(true);

            MenuItem produtoMenu = menuNav.findItem(R.id.nav_produto);
            produtoMenu.setVisible(true);

            MenuItem rotaMenu = menuNav.findItem(R.id.nav_rota);
            rotaMenu.setVisible(true);
        }
    }
}
