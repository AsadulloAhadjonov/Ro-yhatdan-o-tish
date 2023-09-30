package com.example.royxatdanotish

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import com.example.royxatdanotish.Adapter.AdapterRv
import com.example.royxatdanotish.Mudels.User
import com.example.royxatdanotish.databinding.ActivityUsersBinding
import com.example.royxatdanotish.databinding.DialogItemBinding
import com.example.royxatdanotish.db.DbHelper2
import com.google.android.material.bottomsheet.BottomSheetDialog

class UsersActivity : AppCompatActivity() {
    private val binding by lazy { ActivityUsersBinding.inflate(layoutInflater) }
    private lateinit var list:ArrayList<User>
    private lateinit var dbHelper2: DbHelper2
    private lateinit var adapter: AdapterRv
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dbHelper2 = DbHelper2(this)
        list = dbHelper2.getAll()
        adapter = AdapterRv(list, object : AdapterRv.Rv{
            override fun click(user: User) {
                var dialog = BottomSheetDialog(this@UsersActivity)
                var item = DialogItemBinding.inflate(layoutInflater)
                dialog.setContentView(item.root)

                item.dialogName.text = user.nameSourname
                item.dialogNumber.text = user.phone
                item.imgDialog.setImageURI(Uri.parse(user.image))

                item.tel.setOnClickListener {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:${user.phone.trim()}")
                    startActivity(intent)
                }

                item.sms.setOnClickListener {
                    val smsUri = Uri.parse("smsto:${user.phone.trim()}")
                    val intent = Intent(Intent.ACTION_SENDTO, smsUri)
                    startActivity(intent)
                }

                dialog.show()
            }
        })
        binding.rv.adapter = adapter
        adapter.list.addAll(dbHelper2.getAll())

        binding.text.setOnClickListener {
            finish()
        }
    }
}