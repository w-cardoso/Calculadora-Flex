package com.example.calculadoraflexwevs.ui.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calculadoraflexwevs.R
import com.example.calculadoraflexwevs.ui.form.FormActivity
import com.example.calculadoraflexwevs.ui.singup.SignUpActivity
import com.example.calculadoraflexwevs.utils.DatabaseUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private val newUserRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
        mAuth.currentUser?.reload()
        if (mAuth.currentUser != null) {
            goToHome()
        }
        btLogin.setOnClickListener {
            mAuth.signInWithEmailAndPassword(
                inputLoginEmail.editText?.text.toString(),
                inputLoginPassword.editText?.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    goToHome()
                } else {
                    it.exception?.message?.let { it1 -> toast(it1) }
                }
            }
        }

        btSingUp.setOnClickListener {
            startActivityForResult(
                Intent(this, SignUpActivity::class.java),
                newUserRequestCode
            )
        }
    }

    private fun goToHome() {
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this) { instanceIdResult ->
            val newToken = instanceIdResult.token
            DatabaseUtil.saveToken(newToken)
        }
        startActivity(intentFor<FormActivity>().singleTop())
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newUserRequestCode && resultCode == Activity.RESULT_OK) {
            inputLoginEmail.editText?.setText(data?.getStringExtra("email"))
        }
    }

}