package br.com.raspemania.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.raspemania.R;
import br.com.raspemania.model.entidade.Leitura;
import br.com.raspemania.viewmodel.LeituraViewModel;

public class LeituraAdapter extends RecyclerView.Adapter<LeituraAdapter.LeituraViewHolder>{

    public static String TAG = "LeituraAdapter";

    private List<Leitura> listLeitura;
    private Context context;
    private LeituraViewModel mViewmodel;

    public LeituraAdapter(List<Leitura> listLeitura, LeituraViewModel viewmodel) {
        this.listLeitura = listLeitura;
        this.mViewmodel = viewmodel;
    }
    @Override
    public LeituraAdapter.LeituraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_leitura, parent, false);
        LeituraAdapter.LeituraViewHolder viewHolder = new LeituraAdapter.LeituraViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final LeituraAdapter.LeituraViewHolder holder, int position) {

        final Leitura mItem = listLeitura.get(position);
        holder.codigoEstabelecimento.setText(mItem.local.codigo);
        holder.dataCadastro.setText(mItem.dataUltimaAtualizacao.toString());

        holder.deleteLeitura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewmodel.delete(mItem);
                listLeitura.remove(mItem);
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
        return listLeitura.size();
    }

    public static class LeituraViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView codigoEstabelecimento;
        public AppCompatTextView dataCadastro;
        public AppCompatImageButton deleteLeitura;

        public LeituraViewHolder(View itemView) {
            super(itemView);
            this.codigoEstabelecimento = (AppCompatTextView) itemView.findViewById(R.id.codigoEstabelecimento);
            this.dataCadastro = (AppCompatTextView) itemView.findViewById(R.id.dataCadastro);
            this.deleteLeitura = (AppCompatImageButton) itemView.findViewById(R.id.deleteLeitura);
        }
    }
}