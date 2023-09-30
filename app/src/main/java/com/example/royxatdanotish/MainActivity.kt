package com.example.royxatdanotish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.royxatdanotish.databinding.ActivityMainBinding
import com.example.royxatdanotish.db.DbHelper2
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var dbHelper2: DbHelper2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dbHelper2 = DbHelper2(this)

        binding.txtRoyxatdanOtish.setOnClickListener {
            startActivity(Intent(this, royxatdan_otish::class.java))
        }

        binding.btnTizimgaKirish.setOnClickListener {
            var get = dbHelper2.getAll()
            for (i in 0..get.size){
                try {
                    if (binding.edtTelefonRaqam.text.toString().equals(get[i].phone)  && binding.edtParol.text.toString().equals(get[i].password)){
                        startActivity(Intent(this, UsersActivity::class.java))
                        break
                    }
                }catch (e:Exception){
                    Toast.makeText(this, "Ma'lumot topilmadi", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}