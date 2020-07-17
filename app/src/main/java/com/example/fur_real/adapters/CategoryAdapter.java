package com.example.fur_real.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fur_real.R;
import com.example.fur_real.activities.CategoryActivity;
import com.example.fur_real.model.Category;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<Category> categories;
    Context context;

    public CategoryAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder=null;
        View convertView=LayoutInflater.from(context).inflate(R.layout.category_card,parent,false);
        return new ViewHolder(convertView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.name.setText(category.getName());
        holder.image.setImageResource(category.getImagePath());
    }
    @Override
    public int getItemCount() { return categories.size(); }
}

class  ViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    ImageView image;
    public ViewHolder(@NonNull final View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_cat_name);
        image=itemView.findViewById(R.id.iv_cat_image);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getAdapterPosition()==0){
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), CategoryActivity.class));
                }
            }
        });
    }
}
