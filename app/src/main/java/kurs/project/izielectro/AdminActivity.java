package kurs.project.izielectro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import kurs.project.izielectro.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {
    ActivityAdminBinding binding;
    ArrayList<ListData> dataArrayList=new ArrayList<>();
    ListAdapterAdmin listAdapterAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DatabaseHelper db=new DatabaseHelper(this);
        dataArrayList=db.getItemss();
        listAdapterAdmin=new ListAdapterAdmin(this,dataArrayList);
        binding.listView.setAdapter(listAdapterAdmin);
        binding.listView.setClickable(true); binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(AdminActivity    .this, AdminDetailedActivity.class);
                intent.putExtra("id",dataArrayList.get(i).id);
                intent.putExtra("title",dataArrayList.get(i).title);
                intent.putExtra("description",dataArrayList.get(i).description);
                intent.putExtra("price",dataArrayList.get(i).price);
                intent.putExtra("photo",dataArrayList.get(i).photo);
                intent.putExtra("rest_of",dataArrayList.get(i).restOf);
                intent.putExtra("id_category",dataArrayList.get(i).idCategory);
                startActivity(intent);
            }


        });
        binding.addButton.setOnClickListener(view -> {
            Intent intent=new Intent(AdminActivity.this, AdminDetailedActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        DatabaseHelper db=new DatabaseHelper(this);
        dataArrayList=db.getItemss();
        listAdapterAdmin=new ListAdapterAdmin(this,dataArrayList);
        binding.listView.setAdapter(listAdapterAdmin);
        super.onResume();
    }
}