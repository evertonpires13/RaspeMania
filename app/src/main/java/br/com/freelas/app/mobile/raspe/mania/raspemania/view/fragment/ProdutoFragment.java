package br.com.freelas.app.mobile.raspe.mania.raspemania.view.fragment;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Produto;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.old_activity.RotaActivity;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.adapter.ProdutoAdapter;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.old_viewmodel.ProdutoViewModel;

public class ProdutoFragment extends BaseFragment {

    private Context context = getContext();
    private ProdutoViewModel mViewModel;
    private RecyclerView recyclerView;

    private AppCompatButton mNovoBtn;


    public static ProdutoFragment newInstance() {
        return new ProdutoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_produto, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        context = getContext();
        mViewModel = ViewModelProviders.of(this).get(ProdutoViewModel.class);
        doBindings();
        mViewModel.getAll();

        mNovoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, RotaActivity.class));
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNovoBtn = view.findViewById(R.id.btn_novo);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    private void doBindings(){
        super.onStart();
        super.observeError(mViewModel);
        super.observeSucess(mViewModel);

        observeGetAll();
    }

    private void observeGetAll(){
        mViewModel.mList.observe(this, new Observer<List<Produto>>() {
            @Override
            public void onChanged(List<Produto> resultList) {
                prepareRecyclerView(resultList);
            }
        });
    }

    private void prepareRecyclerView(List<Produto> produtos){
        ProdutoAdapter adapter = new ProdutoAdapter(produtos);
        recyclerView.setAdapter(adapter);
    }
}
