package kurs.project.izielectro;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import kurs.project.izielectro.databinding.ActivityCartUserBinding;

public class CartUserActivity extends AppCompatActivity {
    ActivityCartUserBinding binding;
    ListCartAdapter listAdapter;
    ArrayList<ListCart> dataArrayList=new ArrayList<>();
    int idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityCartUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=this.getIntent();
        if (intent!=null) {
            idUser=intent.getIntExtra("id_user",0);
        }
        DatabaseHelper db=new DatabaseHelper(this);
        dataArrayList= db.getCart(idUser);
        listAdapter=new ListCartAdapter(this,dataArrayList);
        binding.listView.setAdapter(listAdapter);
        binding.listView.setClickable(true);
    }
}