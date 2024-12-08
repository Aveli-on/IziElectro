package kurs.project.izielectro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


import kurs.project.izielectro.databinding.ActivityUserBinding;

public class userActivity extends AppCompatActivity {
int idUser;
    ActivityUserBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList=new ArrayList<>();

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
        ArrayList<Category> dataCategoryList= new ArrayList<>();
        dataCategoryList.add(new Category(0,"Все категории"));
        dataCategoryList.addAll(db.getCategory());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getCategoryName(dataCategoryList));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinCategory.setAdapter(adapter);
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
        binding.cartButton.setOnClickListener(view -> {
            Intent intentt=new Intent(getApplicationContext(), CartUserActivity.class);
            intentt.putExtra("id_user",idUser);
            startActivity(intentt);
        });
        binding.accountButton.setOnClickListener(view -> {
            Intent intentt=new Intent(getApplicationContext(), UserAccount.class);
            intentt.putExtra("id_user",idUser);
            startActivity(intentt);
        });
        binding.spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                dataArrayList=db.getItemsFilter(binding.searchView.getQuery().toString(),getIdCategory(adapterView.getSelectedItem().toString(),dataCategoryList));
                listAdapter=new ListAdapter(userActivity.this,dataArrayList);
                binding.listView.setAdapter(listAdapter);
                binding.listView.setClickable(true);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                dataArrayList = db.getItemsFilter(newText, getIdCategory(binding.spinCategory.getSelectedItem().toString(), dataCategoryList));
                listAdapter = new ListAdapter(userActivity.this, dataArrayList);
                binding.listView.setAdapter(listAdapter);
                binding.listView.setClickable(true);
                return false;
            }
        });
    }
    private String[] getCategoryName(ArrayList<Category> categories) {
        String[] names = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            names[i] = categories.get(i).title;
        }
        return names;
    }
    private int getIdCategory(String name,ArrayList<Category> categories){
        int id;
        for (Category category:categories)
        {
            if (category.title.equals(name)){
                id=category.id;
                return id;
            }
        }
        return 0;
    }

    @Override
    protected void onResume() {
        binding.spinCategory.setSelection(0);
        binding.searchView.setQuery("", false);
        super.onResume();
    }
}