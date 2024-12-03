package kurs.project.izielectro;

import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
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
        setContentView(R.layout.activity_signup);
        databaseHelper=new DatabaseHelper(this);
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.signupEmail.getText().toString();
                String password=binding.signupPassword.getText().toString();
                String confirmPassword=binding.signupConfirm.getText().toString();
                if(email.equals(("")) || password.equals("") || confirmPassword.equals("")){
                    Toast.makeText(SignupActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                }else if(password.equals(confirmPassword)){
                    Boolean checkUsername=databaseHelper.checkEmail(email);
                    if(checkUsername==true){
                        Boolean insert=databaseHelper.insertData(email,password);
                        if(insert){
                            Toast.makeText(SignupActivity.this, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(SignupActivity.this, "Регистрация не удалась", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignupActivity.this, "Такой email уже зарегистрирован", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(SignupActivity.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                }
            }
        } );
        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }
}