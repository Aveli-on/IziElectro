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

import kurs.project.izielectro.databinding.ActivityMainBinding;
import kurs.project.izielectro.databinding.ActivityUserBinding;

public class userActivity extends AppCompatActivity {
int idUser;
    ActivityUserBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList=new ArrayList<>();
    ListData listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=this.getIntent();
        if (intent!=null) {
            idUser=intent.getIntExtra("id_user",0);
        }
        DatabaseHelper db=new DatabaseHelper(this);
        dataArrayList= db.getItemss();
        listAdapter=new ListAdapter(this,dataArrayList);
        binding.listView.setAdapter(listAdapter);
        binding.listView.setClickable(true);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(userActivity.this, userActivityDatailed.class);
                intent.putExtra("id",dataArrayList.get(i).id);
                intent.putExtra("title",dataArrayList.get(i).title);
                intent.putExtra("description",dataArrayList.get(i).description);
                intent.putExtra("price",dataArrayList.get(i).price);
                intent.putExtra("photo",dataArrayList.get(i).photo);
                intent.putExtra("id_user",idUser);
                startActivity(intent);
            }


        });
    }
}