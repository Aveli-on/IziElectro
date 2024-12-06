package kurs.project.izielectro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListOrderAdminAdapter extends ArrayAdapter<ListOrder> {
    public ListOrderAdminAdapter(@NonNull Context context, ArrayList<ListOrder> dataArrayList) {
        super(context,R.layout.oreder_admin_list ,dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListOrder listOrder =getItem(position);
        if(view==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.oreder_admin_list,parent,false);
        }
        ImageView orderImage=view.findViewById(R.id.orderImage);
        TextView orderName=view.findViewById(R.id.orderName);
        TextView orderQuantity=view.findViewById(R.id.orderQuantity);
        TextView orderfio=view.findViewById(R.id.orderClient);
        int i= parent.getResources().getIdentifier(listOrder.photo, "drawable", getContext().getPackageName());;
        orderImage.setImageResource(i);
        orderName.setText(listOrder.title);
        orderfio.setText(listOrder.fio);
        String orderQuant= listOrder.quantity+" шт.";
        orderQuantity.setText(orderQuant);
        return view;
    }
}
