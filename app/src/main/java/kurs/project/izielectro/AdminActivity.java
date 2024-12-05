package kurs.project.izielectro;

import android.os.Bundle;

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
        binding.listView.setClickable(true);
    }
}