package kurs.project.izielectro;

import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kurs.project.izielectro.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        databaseHelper=new DatabaseHelper(this);
        binding.signupButton.setOnClickListener(view -> {
            String fio=binding.signupFio.getText().toString();
            String login=binding.signupLogin.getText().toString();
            String password=binding.signupPassword.getText().toString();
            String phone=binding.signupPhone.getText().toString();
            String confirmPassword=binding.signupConfirm.getText().toString();
            if(login.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || fio.isEmpty()){
                Toast.makeText(SignupActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            }else if(password.equals(confirmPassword)){
                Boolean checkUsername=databaseHelper.checkEmail(login);
                if(checkUsername==true){
                    Boolean insert=databaseHelper.insertDataUser(fio,"Пользователь",login,password,phone);
                    if(insert){
                        Toast.makeText(SignupActivity.this, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(SignupActivity.this, "Регистрация не удалась", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignupActivity.this, "Такой логин уже зарегистрирован", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(SignupActivity.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
            }
        });

        binding.loginRedirectText.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }
}