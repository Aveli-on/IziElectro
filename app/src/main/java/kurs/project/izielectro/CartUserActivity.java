package kurs.project.izielectro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

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
        binding.orderButton.setOnClickListener(view -> {
            for (int i = 0; i < dataArrayList.size(); i++) {
                //db.updateQuantity(dataArrayList.get(i).idProduct,dataArrayList.get(i).quantity);
                }
            Toast.makeText(CartUserActivity.this, "Вы успешно оформили заказ", Toast.LENGTH_SHORT).show();
            finish();
        });
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(CartUserActivity.this, userActivityDatailed.class);
                intent.putExtra("id",dataArrayList.get(i).idProduct);
                intent.putExtra("title",dataArrayList.get(i).title);
                intent.putExtra("description",dataArrayList.get(i).description);
                intent.putExtra("price",dataArrayList.get(i).price);
                intent.putExtra("photo",dataArrayList.get(i).photo);
                intent.putExtra("id_user",idUser);
                intent.putExtra("cart",true);
                startActivity(intent);
            }
        });
    }
}