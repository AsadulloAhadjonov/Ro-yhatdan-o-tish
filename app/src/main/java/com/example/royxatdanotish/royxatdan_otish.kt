package com.example.royxatdanotish

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.royxatdanotish.Adapter.AdapterRv
import com.example.royxatdanotish.Mudels.User
import com.example.royxatdanotish.databinding.ActivityRoyxatdanOtishBinding
import com.example.royxatdanotish.db.DbHelper2
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date


class royxatdan_otish : AppCompatActivity() {
    private val binding by lazy { ActivityRoyxatdanOtishBinding.inflate(layoutInflater) }
    private lateinit var spinner: Spinner
    private lateinit var dbHelper2: DbHelper2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        spinner = binding.spinnerDavlat
        dbHelper2 = DbHelper2(this)

        val countries = resources.getStringArray(R.array.country_names)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        binding.plus.setOnClickListener {
            getImage.launch("image/*")
        }

        binding.btnTizimgaKirish.setOnClickListener {
            if (filePath.isEmpty()){
                if (binding.edtImsFamilya.text.isEmpty() || binding.edtManzil.text.isEmpty() || binding.edtParol.text.isEmpty() || binding.edtTelefonRaqam.text.isEmpty()){
                    Toast.makeText(this, "Ma'lumotlarni to'liq kirgizing", Toast.LENGTH_SHORT).show()
                }else {
                    val user = User(
                        binding.edtImsFamilya.text.toString(),
                        binding.edtTelefonRaqam.text.toString(),
                        binding.spinnerDavlat.selectedItem.toString(),
                        binding.edtManzil.text.toString(),
                        binding.edtParol.text.toString(),
                        0
                    )
                    dbHelper2.addAll(user)
                    startActivity(Intent(this, UsersActivity::class.java))
                    binding.edtImsFamilya.text.clear()
                    binding.edtManzil.text.clear()
                    binding.edtParol.text.clear()
                    binding.edtTelefonRaqam.text.clear()
                    Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
                }
            }else{
                if (binding.edtImsFamilya.text.isEmpty() || binding.edtManzil.text.isEmpty() || binding.edtParol.text.isEmpty() || binding.edtTelefonRaqam.text.isEmpty()){
                    Toast.makeText(this, "Ma'lumotlarni to'liq kirgizing", Toast.LENGTH_SHORT).show()}else {
                    val user = User(
                        binding.edtImsFamilya.text.toString(),
                        binding.edtTelefonRaqam.text.toString(),
                        binding.spinnerDavlat.selectedItem.toString(),
                        binding.edtManzil.text.toString(),
                        binding.edtParol.text.toString(),
                        filePath,
                        1
                    )
                    dbHelper2.addAll(user)
                    startActivity(Intent(this, UsersActivity::class.java))
                    Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
                    binding.edtImsFamilya.text.clear()
                    binding.edtManzil.text.clear()
                    binding.edtParol.text.clear()
                    binding.edtTelefonRaqam.text.clear()
                }
            }
        }
    }

    var filePath = ""

    private val getImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri == null) {
                return@registerForActivityResult
            }
            binding.avatar.setImageURI(uri)
            val stream = contentResolver.openInputStream(uri)
            val title = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val file = File(filesDir, "$title.jpg")
            val outputImage = FileOutputStream(file)
            stream?.copyTo(outputImage)
            stream?.close()
            outputImage?.close()

            filePath = file.absolutePath
        }
}