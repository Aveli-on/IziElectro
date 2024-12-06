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
        ArrayList<Category> dataCategoryList= new ArrayList<>();
        dataCategoryList.add(new Category(0,"Все категории"));
        dataCategoryList.addAll(db.getCategory());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getCategoryName(dataCategoryList));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinCategory.setAdapter(adapter);
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
        binding.usersOrderButton.setOnClickListener(view -> {
            Intent intent=new Intent(AdminActivity.this, AdminOrderActivity.class);
            startActivity(intent);
        });

        binding.spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    dataArrayList=db.getItemsFilter(binding.searchView.getQuery().toString(),getIdCategory(adapterView.getSelectedItem().toString(),dataCategoryList));
                    listAdapterAdmin=new ListAdapterAdmin(AdminActivity.this,dataArrayList);
                    binding.listView.setAdapter(listAdapterAdmin);
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
                listAdapterAdmin = new ListAdapterAdmin(AdminActivity.this, dataArrayList);
                binding.listView.setAdapter(listAdapterAdmin);
                binding.listView.setClickable(true);
                return false;
            }
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
}