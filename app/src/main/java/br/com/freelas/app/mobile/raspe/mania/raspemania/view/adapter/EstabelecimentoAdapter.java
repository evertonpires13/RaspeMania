package br.com.freelas.app.mobile.raspe.mania.raspemania.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Estabelecimento;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.EstabelecimentoActivity;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.EstabelecimentoViewModel;

public class EstabelecimentoAdapter extends RecyclerView.Adapter<EstabelecimentoAdapter.EstabelecimentoViewHolder> {


    public static String TAG = "EstabelecimentoAdapter";

    private List<Estabelecimento> listEstabelecimento;
    private Context context;
    private EstabelecimentoViewModel mViewmodel;


    public EstabelecimentoAdapter(List<Estabelecimento> listEstabelecimento, EstabelecimentoViewModel viewmodel) {
        this.listEstabelecimento = listEstabelecimento;
        this.mViewmodel = viewmodel;
    }

    @Override
    public EstabelecimentoAdapter.EstabelecimentoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_estabelecimento, parent, false);
        EstabelecimentoAdapter.EstabelecimentoViewHolder viewHolder = new EstabelecimentoAdapter.EstabelecimentoViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final EstabelecimentoAdapter.EstabelecimentoViewHolder holder, int position) {

        final Estabelecimento mItem = listEstabelecimento.get(position);
        holder.codigo.setText(mItem.codigo);
        holder.rota.setText(mItem.rota.nome);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EstabelecimentoActivity.class);
                intent.putExtra(TAG, mItem);
                context.startActivity(intent);
            }
        });

        holder.deleteEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewmodel.delete(mItem);
                listEstabelecimento.remove(mItem);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    @Override
    public int getItemCount() {
        return listEstabelecimento.size();
    }

    public static class EstabelecimentoViewHolder extends RecyclerView.ViewHolder {
        public TextView codigo;
        public TextView rota;
        public ConstraintLayout constraintLayout;
        public AppCompatImageButton deleteEstabelecimento;

        public EstabelecimentoViewHolder(View itemView) {
            super(itemView);
            this.codigo = (TextView) itemView.findViewById(R.id.codigo_estabelecimento);
            this.rota = (TextView) itemView.findViewById(R.id.nome_rota);
            this.constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.layout_estabelecimento);
            this.deleteEstabelecimento = (AppCompatImageButton) itemView.findViewById(R.id.delete_estabelecimento_btn);
        }
    }
}
