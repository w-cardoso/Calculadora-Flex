package com.example.calculadoraflexwevs.ui.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculadoraflexwevs.R
import com.example.calculadoraflexwevs.model.CarData
import com.example.calculadoraflexwevs.ui.result.ResultActivity
import com.example.calculadoraflexwevs.watchers.DecimalTextWatcher
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_form_activity.*
import org.jetbrains.anko.startActivity

class FormActivity : AppCompatActivity() {
    private lateinit var userId: String
    private lateinit var mAuth: FirebaseAuth
    private val firebaseReferenceNode = "CarData"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_activity)
        mAuth = FirebaseAuth.getInstance()
        userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        listenerFirebaseRealtime()

        etGasPrice.addTextChangedListener(DecimalTextWatcher(etGasPrice))
        etEthanolPrice.addTextChangedListener(DecimalTextWatcher(etEthanolPrice))
        etGasAverage.addTextChangedListener(DecimalTextWatcher(etGasAverage, 1))
        etEthanolAverage.addTextChangedListener(DecimalTextWatcher(etEthanolAverage, 1))

        btCalculate.setOnClickListener {
            val carData = CarData(
                etGasPrice.text.toString().toDouble(),
                etEthanolPrice.text.toString().toDouble(),
                etGasAverage.text.toString().toDouble(),
                etEthanolAverage.text.toString().toDouble()
            )
            saveCarData(carData)
            startActivity<ResultActivity>(
                "CAR_DATA" to carData
                /*"GAS_PRICE" to etGasPrice.text.toString().toDouble(),
                "ETHANOL_PRICE" to etEthanolPrice.text.toString().toDouble(),
                "GAS_AVERAGE" to etGasAverage.text.toString().toDouble(),
                "ETHANOL_AVERAGE" to etEthanolAverage.text.toString().toDouble()*/
            )
        }
    }

    private fun saveCarData(carData: CarData) {
        FirebaseDatabase.getInstance().getReference(firebaseReferenceNode)
            .child(userId)
            .setValue(carData)
    }

    private fun listenerFirebaseRealtime() {
        val database = FirebaseDatabase.getInstance()
        //Define para usar dados off-line
//        database.setPersistenceEnabled(true)
        database
            .getReference(firebaseReferenceNode)
            .child(userId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val carData = dataSnapshot.getValue(CarData::class.java)
                    etGasPrice.setText(carData?.gasPrice.toString())
                    etEthanolPrice.setText(carData?.ethanolPrice.toString())
                    etGasAverage.setText(carData?.gasAverage.toString())
                    etEthanolAverage.setText(carData?.ethanolAverage.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }
}