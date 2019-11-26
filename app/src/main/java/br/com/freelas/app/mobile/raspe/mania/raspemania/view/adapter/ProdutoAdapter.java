package br.com.freelas.app.mobile.raspe.mania.raspemania.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.freelas.app.mobile.raspe.mania.raspemania.R;
import br.com.freelas.app.mobile.raspe.mania.raspemania.model.entidade.Produto;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>{

    private List<Produto> listProduto;
    private Context context;;

    public ProdutoAdapter(List<Produto> listProduto) {
        this.listProduto = listProduto;
    }
    @Override
    public ProdutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_produto, parent, false);
        ProdutoViewHolder viewHolder = new ProdutoViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProdutoViewHolder holder, int position) {
        final Produto mItem = listProduto.get(position);
        holder.nomeProduto.setText(mItem.nome);
        holder.valorProduto.setText(Long.toString(mItem.valor));
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(context, RotaActivity.class);
                //context.startActivity(intent);
                Toast.makeText(view.getContext(),"click on item: "+ mItem.nome ,Toast.LENGTH_LONG).show();
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
        return listProduto.size();
    }

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeProduto;
        public TextView valorProduto;
        public ConstraintLayout constraintLayout;

        public ProdutoViewHolder(View itemView) {
            super(itemView);
            this.nomeProduto = (TextView) itemView.findViewById(R.id.nome_produto);
            this.valorProduto = (TextView) itemView.findViewById(R.id.valor_produto);
            this.constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.layout_produto);
        }
    }
}