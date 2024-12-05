package kurs.project.izielectro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kurs.project.izielectro.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        databaseHelper=new DatabaseHelper(this);
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.loginEmail.getText().toString();
                String password=binding.loginPassword.getText().toString();
                if (email.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                }else{
                    int checkCredentials=databaseHelper.checkEmailPassword(email,password);
                    if(checkCredentials!=-1){
                        Toast.makeText(LoginActivity.this, "Вы успешно вошли", Toast.LENGTH_SHORT).show();
                        if (databaseHelper.checkRole(checkCredentials).equals("Пользователь")){
                            Intent intent=new Intent(getApplicationContext(), userActivity.class);
                            intent.putExtra("id_user",checkCredentials);
                            startActivity(intent);
                        }
                        else if(databaseHelper.checkRole(checkCredentials).equals("Администратор")){
                            Intent intent=new Intent(getApplicationContext(), AdminActivity.class);
                            intent.putExtra("id_user",checkCredentials);
                            startActivity(intent);
                        }

                    }else{
                        Toast.makeText(LoginActivity.this, "Неверные данные", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}