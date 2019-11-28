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
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Rota;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.RotaActivity;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.RotaViewModel;

public class RotaAdapter extends RecyclerView.Adapter<RotaAdapter.RotaViewHolder> {

    public static String TAG = "RotaAdapter";

    private List<Rota> listRota;
    private Context context;
    private RotaViewModel mViewmodel;


    public RotaAdapter(List<Rota> listRota, RotaViewModel viewmodel) {
        this.listRota = listRota;
        this.mViewmodel = viewmodel;
    }

    @Override
    public RotaAdapter.RotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_rota, parent, false);
        RotaAdapter.RotaViewHolder viewHolder = new RotaAdapter.RotaViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final RotaAdapter.RotaViewHolder holder, int position) {

        try {
            final Rota mItem = listRota.get(position);
            holder.nome.setText(mItem.nome);
            holder.colaborador.setText(mItem.colaborador.apelido);

            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, RotaActivity.class);
                    intent.putExtra(TAG, mItem);
                    context.startActivity(intent);
                }
            });

            holder.deleteRota.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewmodel.delete(mItem);
                    listRota.remove(mItem);
                    notifyDataSetChanged();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    @Override
    public int getItemCount() {
        return listRota.size();
    }

    public static class RotaViewHolder extends RecyclerView.ViewHolder {
        public TextView nome;
        public TextView colaborador;
        public ConstraintLayout constraintLayout;
        public AppCompatImageButton deleteRota;

        public RotaViewHolder(View itemView) {
            super(itemView);
            this.nome = (TextView) itemView.findViewById(R.id.nome_rota);
            this.colaborador = (TextView) itemView.findViewById(R.id.apelido_colaborador);
            this.constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.layout_rota);
            this.deleteRota = (AppCompatImageButton) itemView.findViewById(R.id.delete_rota_btn);
        }
    }
}
