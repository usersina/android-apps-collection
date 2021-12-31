package com.example.firebasegps.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasegps.MainActivity;
import com.example.firebasegps.R;
import com.example.firebasegps.models.Product;
import com.example.firebasegps.services.FirestoreDBService;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    public static final String TAG = "ProductAdapter";
    private FirestoreDBService firestoreDBService;

    Context context;
    ArrayList<Product> list;

    public ProductAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        firestoreDBService = new FirestoreDBService();
        View v = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = list.get(position);
        holder.nameTv.setText(product.getName());
        holder.priceTv.setText("Costs: " + Double.toString(product.getPrice()));

        holder.editBtn.setOnClickListener(v -> {
            // -- Inflate the rootView to create the AlertDialog
            View view = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.input_dialog, null);
            AlertDialog alertDialog = new AlertDialog.Builder(v.getRootView().getContext()).create();
            alertDialog.setTitle("Edit a product");

            final EditText nameEt = (EditText) view.findViewById(R.id.name_et);
            final EditText priceEt = (EditText) view.findViewById(R.id.price_et);

            nameEt.setText(list.get(position).getName());
            priceEt.setText(String.valueOf(list.get(position).getPrice()));

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Edit product", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        String name = nameEt.getText().toString();
                        double price = Double.valueOf(priceEt.getText().toString());
                        if (name.isEmpty() || priceEt.getText().toString().isEmpty()) { return ; }

                        firestoreDBService.updateProduct(list.get(position).getId(), name, price);
                    } catch (Exception e) {
                        Log.d(TAG, "Cannot save the product, something is wrong with the input!");
                    }
                }
            });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.setView(view);
            alertDialog.show();
        });

        holder.deleteBtn.setOnClickListener(v -> {
            Log.d(TAG, "MyViewHolder: Deleting the product with id: " + list.get(position).getId());
            firestoreDBService.deleteProduct(list.get(position).getId());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        int SELECTED_INDEX;

        TextView nameTv, priceTv;
        ImageButton editBtn, deleteBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.name_tv);
            priceTv = itemView.findViewById(R.id.price_tv);
            
            editBtn = itemView.findViewById(R.id.edit_btn);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
        }
    }
}
