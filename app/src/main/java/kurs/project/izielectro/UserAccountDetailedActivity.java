package kurs.project.izielectro;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kurs.project.izielectro.databinding.ActivityUserAccountDetailedBinding;

public class UserAccountDetailedActivity extends AppCompatActivity {
    ActivityUserAccountDetailedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityUserAccountDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=this.getIntent();
        if (intent!=null) {
            int idUser=intent.getIntExtra("id_user",0);
            DatabaseHelper db=new DatabaseHelper(this);
            String[] data=db.getUser(idUser);
            binding.detailLogin.setText(data[0]);
            binding.detailFio.setText(data[1]);
            binding.detailPhone.setText(data[2]);
            binding.detailRole.setText(data[3]);
        }

    }
}