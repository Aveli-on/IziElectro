package kurs.project.izielectro;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import kurs.project.izielectro.databinding.ActivityAdminOrderBinding;

public class AdminOrderActivity extends AppCompatActivity {
ActivityAdminOrderBinding binding;
ArrayList<ListOrder> dataArrayList=new ArrayList<>();
ListOrderAdminAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityAdminOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DatabaseHelper db=new DatabaseHelper(this);
        dataArrayList=db.getOrders();
        adapter=new ListOrderAdminAdapter(this,dataArrayList);
        binding.listView.setAdapter(adapter);

    }
}