package kurs.project.izielectro;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kurs.project.izielectro.databinding.ActivityUserDatailedBinding;

public class userActivityDatailed extends AppCompatActivity {

    ActivityUserDatailedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityUserDatailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=this.getIntent();
        if(intent!=null){
            String name=intent.getStringExtra("name");
            String time=intent.getStringExtra("time");
            int ingredients=intent.getIntExtra("ingredients",R.string.ingr1);
            int desc=intent.getIntExtra("desc",R.string.desc1);
            int image=intent.getIntExtra("image",R.drawable.phone);
            binding.detailName.setText(name);
            binding.detailTime.setText(time);
            binding.detailIngredients.setText(ingredients);
            binding.detailDesc.setText(desc);
            binding.detailImage.setImageResource(image);
        }
    }
}