package com.example.calculadoraflexwevs.ui.singup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calculadoraflexwevs.BaseActivity
import com.example.calculadoraflexwevs.R
import com.example.calculadoraflexwevs.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sing_up.*
import org.jetbrains.anko.toast

class SignUpActivity : BaseActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        mAuth = FirebaseAuth.getInstance()
        btCreate.setOnClickListener {
            mAuth.createUserWithEmailAndPassword(
                inputLoginEmail.text.toString(),
                inputLoginPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    saveInRealTimeDatabase()
                } else {
                    toast(it.exception?.message.toString())
                }
            }
        }

    }

    private fun saveInRealTimeDatabase() {
        val user = User(
            inputName.text.toString(), inputLoginEmail.text.toString(),
            inputPhone.text.toString()
        )
        FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(user)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    toast("Usuário criado com sucesso")
                    val returnIntent = Intent()
                    returnIntent.putExtra("email", inputLoginEmail.text.toString())
                    setResult(RESULT_OK, returnIntent)
                    finish()
                } else {
                    toast("Erro ao criar o usuário")
                }
            }
    }
}
