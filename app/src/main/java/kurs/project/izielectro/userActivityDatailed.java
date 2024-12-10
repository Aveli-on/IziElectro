package kurs.project.izielectro;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kurs.project.izielectro.databinding.ActivityUserDatailedBinding;

public class userActivityDatailed extends AppCompatActivity {

    ActivityUserDatailedBinding binding;
    int id,idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityUserDatailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=this.getIntent();
        if(intent!=null){
            String title=intent.getStringExtra("title");
            String description=intent.getStringExtra("description");
            int price=intent.getIntExtra("price",0);
            id=intent.getIntExtra("id",0);
            idUser=intent.getIntExtra("id_user",0);
            String photo=intent.getStringExtra("photo");
            binding.detailName.setText(title);
            binding.detailPrice.setText(price+" руб.");
            binding.detailDescription.setText(description);
            binding.detailImage.setImageResource(this.getResources().getIdentifier(photo, "drawable", this.getPackageName()));

        }
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        if(databaseHelper.checkProduct(idUser,id)){
            binding.addToCartButton.setText("В корзине");

        }
        binding.addToCartButton.setOnClickListener(view -> {

                if (!databaseHelper.checkProduct(idUser,id)) {
                    databaseHelper.addToCart(idUser, id);
                    binding.addToCartButton.setText("В корзине");
                    Toast.makeText(userActivityDatailed.this, "Товар добавлен в корзину", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseHelper.deleteFromCart(idUser,id);
                    binding.addToCartButton.setText("В корзину");
                    Toast.makeText(userActivityDatailed.this, "Товар удален из корзины", Toast.LENGTH_SHORT).show();
                }


            });

    }
}