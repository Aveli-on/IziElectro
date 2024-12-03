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
        int[] imageList={R.drawable.phone,R.drawable.tablet,R.drawable.laptop};
        int[] ingredientList={R.string.ingr1,R.string.ingr2,R.string.ingr3};
        int[] descList={R.string.desc1,R.string.desc2,R.string.desc3};
        String[] nameList={"Phone","Tablet","Laptop"};
        String[] timeList={"1","2","3"};

        for(int i=0;i<imageList.length;i++){
           listData=new ListData(nameList[i],timeList[i],ingredientList[i],descList[i],imageList[i]);
            dataArrayList.add(listData);
        }
        listAdapter=new ListAdapter(this,dataArrayList);
        binding.listView.setAdapter(listAdapter);
        binding.listView.setClickable(true);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(userActivity.this, userActivityDatailed.class);
                intent.putExtra("name",nameList[i]);
                intent.putExtra("time",timeList[i]);
                intent.putExtra("ingredients",ingredientList[i]);
                intent.putExtra("desc",descList[i]);
                intent.putExtra("image",imageList[i]);
                startActivity(intent);
            }


        });
    }
}