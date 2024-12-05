package kurs.project.izielectro;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import kurs.project.izielectro.databinding.ActivityUserAccountBinding;

public class UserAccount extends AppCompatActivity {
    ActivityUserAccountBinding binding;
    int idUser;
    ListOrderAdapter listOrderAdapter;
    ArrayList<ListOrder> dataArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityUserAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=this.getIntent();
        if (intent!=null) {
            idUser=intent.getIntExtra("id_user",0);
        }
        DatabaseHelper db=new DatabaseHelper(this);
        dataArrayList= db.getOrder(idUser);
        listOrderAdapter=new ListOrderAdapter(this,dataArrayList);
        binding.listView.setAdapter(listOrderAdapter);
        binding.listView.setClickable(true);
        binding.accountDetailButton.setOnClickListener(view -> {
            Intent intent1 = new Intent(UserAccount.this, UserAccountDetailedActivity.class);
            intent1.putExtra("id_user",idUser);
            startActivity(intent1);
        });

    }
}