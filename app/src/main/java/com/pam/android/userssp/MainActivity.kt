package com.pam.android.userssp

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.pam.android.userssp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), onClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE)

        val isFirsTime = preferences.getBoolean(getString(R.string.sp_first_time), true)
        Log.i("SP", "${getString(R.string.sp_first_time)} = $isFirsTime")

        if (isFirsTime) {
            val dialogView = layoutInflater.inflate(R.layout.dialog_register, null)

            val dialog =MaterialAlertDialogBuilder(this)
                .setTitle(R.string.welcome_text)
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_confirm) { _, _ -> }
                .create()

            dialog.show()

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener{
                val username = dialogView.findViewById<TextInputEditText>(R.id.etUsername)
                    .text.toString()
                if (username.isBlank()) {
                    Toast.makeText(this, R.string.invalid_username, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    with(preferences.edit()){
                        putBoolean(getString(R.string.sp_first_time), false)
                        putString(getString(R.string.sp_username), username)
                            .apply()
                        dialog.dismiss()
                    }
                }
            }


        } else {
            val username = preferences.getString(getString(R.string.sp_username), getString(R.string.hint_username))
            Toast.makeText(this, "Welcome $username", Toast.LENGTH_SHORT).show()
        }

        userAdapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }

        val swipeHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userAdapter.remove(viewHolder.adapterPosition)
            }

        })
        swipeHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun getUsers(): MutableList<User> {
        val users = mutableListOf<User>()

        val alain = User(1, "Alain", "Nicolas", "https://storage.needpix.com/rsynced_images/avatar-1577909_1280.png")
        val daniel = User(2, "Daniel", "Escalona", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQV-REr89iWROi6ScePs5agSIHpG9BPBDDZ_g&s")
        val john = User(3, "John", "Doe", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMAc3f3S1UWexljsb7Hcr23gcj8HV8EScx-Q&s")
        val emma = User(4, "Emma", "White", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcREEtSQ_wu_UT5c5Zlhq-l0aI9O5Q8uRqczfg&s")

        users.add(alain)
        users.add(daniel)
        users.add(john)
        users.add(emma)

        users.add(alain)
        users.add(daniel)
        users.add(john)
        users.add(emma)

        users.add(alain)
        users.add(daniel)
        users.add(john)
        users.add(emma)

        users.add(alain)
        users.add(daniel)
        users.add(john)
        users.add(emma)

        users.add(alain)
        users.add(daniel)
        users.add(john)
        users.add(emma)

        users.add(alain)
        users.add(daniel)
        users.add(john)
        users.add(emma)

        users.add(alain)
        users.add(daniel)
        users.add(john)
        users.add(emma)

        return users
    }

    override fun onClick(user: User, position: Int) {
        Toast.makeText(this,"$position: ${user.getFullName()}", Toast.LENGTH_SHORT).show()
    }


}