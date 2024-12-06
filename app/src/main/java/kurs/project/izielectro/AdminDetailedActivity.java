package kurs.project.izielectro;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import kurs.project.izielectro.databinding.ActivityAdminBinding;
import kurs.project.izielectro.databinding.ActivityAdminDetailedBinding;

public class AdminDetailedActivity extends AppCompatActivity {
    ActivityAdminDetailedBinding binding;
    int id=0;
    int idCategory=0;
    String photo="standart";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityAdminDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DatabaseHelper db=new DatabaseHelper(this);
        ArrayList<Category> dataArrayList=db.getCategory();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getCategoryName(dataArrayList));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.detailCategory.setAdapter(adapter);
        Intent intent=this.getIntent();
        binding.detailImage.setImageResource(this.getResources().getIdentifier(photo, "drawable", this.getPackageName()));
        idCategory=getIdCategory(binding.detailCategory.getSelectedItem().toString(),dataArrayList);
        if(intent!=null){
            id=intent.getIntExtra("id",0);
            if (id!=0) {
                String title = intent.getStringExtra("title");
                String description = intent.getStringExtra("description");
                int price = intent.getIntExtra("price", 0);
                int restOf = intent.getIntExtra("rest_of", 0);
                photo = intent.getStringExtra("photo");
                idCategory=intent.getIntExtra("id_category",0);
                binding.detailCategory.setSelection(getIdCategoryList(dataArrayList,idCategory));
                binding.detailName.setText(title);
                binding.detailPrice.setText(price + "");
                binding.detailRestOf.setText(restOf + "");
                binding.detailDescription.setText(description);
                binding.detailImage.setImageResource(this.getResources().getIdentifier(photo, "drawable", this.getPackageName()));
                binding.detailPhoto.setText(photo);
                }
        }
        if (id==0){
            binding.saveButton.setText("Добавить");
        }
        binding.detailPhoto.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                int i= binding.detailPhoto.getResources().getIdentifier(editable.toString(), "drawable", (binding.detailPhoto.getContext()).getPackageName());
           if (i !=0){
                binding.detailImage.setImageResource(i);
            }
            }
        });
        binding.saveButton.setOnClickListener(view -> {
            if (binding.detailPhoto.getText().toString().isEmpty()||binding.detailRestOf.getText().toString().isEmpty()||binding.detailPrice.getText().toString().isEmpty()||binding.detailName.getText().toString().isEmpty()||binding.detailDescription.getText().toString().isEmpty()){
                Toast.makeText(AdminDetailedActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            }else {
                int i = binding.detailPhoto.getResources().getIdentifier(binding.detailPhoto.getText().toString(), "drawable", (binding.detailPhoto.getContext()).getPackageName());
                if (i == 0) binding.detailPhoto.setText(photo);
                db.updateSaveProduct(id, binding.detailPhoto.getText().toString(), binding.detailName.getText().toString(), binding.detailDescription.getText().toString(), Integer.parseInt(binding.detailPrice.getText().toString()), Integer.parseInt(binding.detailRestOf.getText().toString()), idCategory);
                if (id == 0)
                    Toast.makeText(AdminDetailedActivity.this, "Товар успешно добавлен", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AdminDetailedActivity.this, "Изменения сохранены", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        binding.detailCategory.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idCategory=getIdCategory(adapterView.getItemAtPosition(i).toString(),dataArrayList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        }));
    }

    private String[] getCategoryName(ArrayList<Category> categories) {
        String[] names = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            names[i] = categories.get(i).title;
        }
        return names;
    }
    private int getIdCategoryList(ArrayList<Category> categories,int id) {

        for (int i = 0; i < categories.size(); i++) {
            if( categories.get(i).id==id){
                return i;
            }
        }
        return 0;
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