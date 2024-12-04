package kurs.project.izielectro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListCartAdapter extends ArrayAdapter<ListCart> {
    public ListCartAdapter(@NonNull Context context, ArrayList<ListCart> dataArrayList) {
        super(context, R.layout.cart_list, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListCart listCart =getItem(position);
        if(view==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.cart_list,parent,false);
        }
        ImageView listImage=view.findViewById(R.id.cartImage);
        TextView listName=view.findViewById(R.id.cartName);
        TextView listTime=view.findViewById(R.id.cartPrice);
        EditText listCount=view.findViewById(R.id.Quantity);
        int i= parent.getResources().getIdentifier(listCart.photo, "drawable", getContext().getPackageName());;
        listImage.setImageResource(i);
        listCount.setText(String.valueOf(listCart.quantity));
        listName.setText(listCart.title);
        String price= listCart.price+" руб.";
        listTime.setText(price);
        return view;
    }
}
