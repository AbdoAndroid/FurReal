package com.example.fur_real.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fur_real.R;
import com.example.fur_real.activities.ItemActivity;
import com.example.fur_real.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    List<Item> items;
    Context context;

    public ItemAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView= LayoutInflater.from(context).inflate(R.layout.item_card,parent,false);
        return new ItemViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.name.setText(item.get_name());
        holder.price.setText(item.getPrice()+"$");
        holder.image.setImageResource(item.getImage());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class  ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;
        ImageView image;
        private final Context context;
        public ItemViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_item_name);
            price=itemView.findViewById(R.id.tv_item_price);
            image=itemView.findViewById(R.id.iv_item_image);
            context=itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle=new Bundle();
                    bundle.putString("name",items.get(getAdapterPosition()).get_name());
                    bundle.putInt("image",items.get(getAdapterPosition()).getImage());
                    bundle.putString("price",items.get(getAdapterPosition()).getPrice());
                    Intent i=new Intent(context, ItemActivity.class);
                    i.putExtras(bundle);
                    context.startActivity(i);

                }
            });

        }


    }


}

