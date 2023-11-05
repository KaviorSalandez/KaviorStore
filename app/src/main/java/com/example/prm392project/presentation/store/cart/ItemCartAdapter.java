package com.example.prm392project.presentation.store.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm392project.R;
import com.example.prm392project.control.SharePreferenceManager;
import com.example.prm392project.model.ItemCart;
import com.example.prm392project.model.Product;
import com.example.prm392project.presentation.store.home.AboutDetailFragment;

import java.util.List;

@SuppressLint("NotifyDataSetChanged")
public class ItemCartAdapter extends RecyclerView.Adapter<ItemCartAdapter.ViewHolder> {

    private Context context;
    private List<ItemCart> poArrayList;
    private OnClickListener onClickListener;

    public ItemCartAdapter(Context context, List<ItemCart> poArrayList) {
        this.context = context;
        this.poArrayList = poArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View productView = inflater.inflate(R.layout.item_produtc_cart, parent, false);
        return new ViewHolder(productView);
    }

    @SuppressLint({"RecyclerView", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemCart p = poArrayList.get(position);
        Glide.with(context).load(p.getImg()).into(holder.poImage);
        holder.poName.setText(p.getName());
        holder.poPrice.setText(String.valueOf(p.getPrice()));
        holder.poNumber.setText(String.valueOf(p.getQuantity()));

    }

    public interface OnClickListener {
        //void onClick(int position, ProductOrder po);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return poArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView poImage;
        private final TextView poName;
        private final TextView poPrice;
        private EditText poNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poImage = itemView.findViewById(R.id.po_image);
            poName = itemView.findViewById(R.id.po_name);
            poPrice = itemView.findViewById(R.id.po_price);
            poNumber = itemView.findViewById(R.id.po_count);

        }
    }
}