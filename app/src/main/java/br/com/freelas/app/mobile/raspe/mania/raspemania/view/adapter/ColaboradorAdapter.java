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
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Colaborador;
import br.com.freelas.app.mobile.raspe.mania.raspemania.view.activity.ColaboradorActivity;
import br.com.freelas.app.mobile.raspe.mania.raspemania.viewmodel.ColaboradorViewModel;

public class ColaboradorAdapter extends RecyclerView.Adapter<ColaboradorAdapter.ColaboradorViewHolder> {

    public static String TAG = "ColaboradorAdapter";

    private List<Colaborador> listColaborador;
    private Context context;
    private ColaboradorViewModel mViewmodel;


    public ColaboradorAdapter(List<Colaborador> listColaborador, ColaboradorViewModel viewmodel) {
        this.listColaborador = listColaborador;
        this.mViewmodel = viewmodel;
    }

    @Override
    public ColaboradorAdapter.ColaboradorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_colaborador, parent, false);
        ColaboradorAdapter.ColaboradorViewHolder viewHolder = new ColaboradorAdapter.ColaboradorViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ColaboradorAdapter.ColaboradorViewHolder holder, int position) {

        final Colaborador mItem = listColaborador.get(position);
        holder.nome.setText(mItem.nome);
        holder.apelido.setText(mItem.apelido);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ColaboradorActivity.class);
                intent.putExtra(TAG, mItem);
                context.startActivity(intent);
            }
        });

        holder.deleteColaborador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewmodel.delete(mItem);
                listColaborador.remove(mItem);
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
        return listColaborador.size();
    }

    public static class ColaboradorViewHolder extends RecyclerView.ViewHolder {
        public TextView nome;
        public TextView apelido;
        public ConstraintLayout constraintLayout;
        public AppCompatImageButton deleteColaborador;

        public ColaboradorViewHolder(View itemView) {
            super(itemView);
            this.nome = (TextView) itemView.findViewById(R.id.nome);
            this.apelido = (TextView) itemView.findViewById(R.id.apelido);
            this.constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.layout_colaborador);
            this.deleteColaborador = (AppCompatImageButton) itemView.findViewById(R.id.delete_colaborador_btn);
        }
    }
}
