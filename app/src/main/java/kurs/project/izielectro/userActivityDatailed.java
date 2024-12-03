package kurs.project.izielectro;

import static java.security.AccessController.getContext;

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
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityUserDatailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=this.getIntent();
        /*intent.putExtra("id",dataArrayList.get(i).id);
                intent.putExtra("title",dataArrayList.get(i).title);
                intent.putExtra("description",dataArrayList.get(i).description);
                intent.putExtra("price",dataArrayList.get(i).price);
                intent.putExtra("photo",dataArrayList.get(i).photo);*/
        if(intent!=null){
            String title=intent.getStringExtra("title");
            String description=intent.getStringExtra("description");
            int price=intent.getIntExtra("price",0);
            id=intent.getIntExtra("id",0);
            String photo=intent.getStringExtra("photo");
            binding.detailName.setText(title);
            binding.detailPrice.setText(price+" руб.");
            binding.detailDescription.setText(description);
            binding.detailImage.setImageResource(this.getResources().getIdentifier(photo, "drawable", this.getPackageName()));
            ;
        }
    }
}