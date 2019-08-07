package com.example.calculadoraflexwevs.ui.form

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadoraflexwevs.BaseActivity
import com.example.calculadoraflexwevs.R
import com.example.calculadoraflexwevs.extensions.format
import com.example.calculadoraflexwevs.model.CarData
import com.example.calculadoraflexwevs.ui.login.LoginActivity
import com.example.calculadoraflexwevs.ui.result.ResultActivity
import com.example.calculadoraflexwevs.utils.CalculaFlexTracker
import com.example.calculadoraflexwevs.utils.DatabaseUtil
import com.example.calculadoraflexwevs.utils.RemoteConfig
import com.example.calculadoraflexwevs.watchers.DecimalTextWatcher
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_form_activity.*
import org.jetbrains.anko.startActivity

class FormActivity : BaseActivity() {
    private lateinit var userId: String
    private lateinit var mAuth: FirebaseAuth
    private val firebaseReferenceNode = "CarData"
    private val defaultClearValueText = "0.0"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_activity)
        mAuth = FirebaseAuth.getInstance()
        userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        listenerFirebaseRealtime()
        loadBanner()

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
            )
            sendDataToAnalytics()
        }
    }

    private fun saveCarData(carData: CarData) {
        FirebaseDatabase.getInstance().getReference(firebaseReferenceNode)
            .child(userId)
            .setValue(carData)
    }

    private fun listenerFirebaseRealtime() {
        DatabaseUtil.getDatabase()
            .getReference(firebaseReferenceNode)
            .child(userId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val carData = dataSnapshot.getValue(CarData::class.java)
                    etGasPrice.setText(carData?.gasPrice?.format(2))
                    etEthanolPrice.setText(carData?.ethanolPrice?.format(2))
                    etGasAverage.setText(carData?.gasAverage?.format(1))
                    etEthanolAverage.setText(carData?.ethanolAverage?.format(1))
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.form_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> {
                clearData()
                true
            }
            R.id.action_logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        mAuth.signOut()
        startActivity<LoginActivity>()
        finish()
    }

    private fun clearData() {
        etGasPrice.setText(defaultClearValueText)
        etEthanolPrice.setText(defaultClearValueText)
        etGasAverage.setText(defaultClearValueText)
        etEthanolAverage.setText(defaultClearValueText)
    }

    private fun loadBanner() {
        val loginBanner = RemoteConfig.getFirebaseRemoteConfig()
            .getString("banner_image")

        Picasso.get().load(loginBanner).into(ivBanner)
    }

    private fun sendDataToAnalytics() {
        val bundle = Bundle()
        bundle.putString("EVENT_NAME", "CALCULATION")
        bundle.putDouble("GAS_PRICE", etGasPrice.text.toString().toDouble());
        bundle.putDouble("ETHANOL_PRICE", etEthanolPrice.text.toString().toDouble());
        bundle.putDouble("GAS_AVERAGE", etGasAverage.text.toString().toDouble());
        bundle.putDouble("ETHANOL_AVERAGE", etEthanolAverage.text.toString().toDouble());
        CalculaFlexTracker.trackEvent(this, bundle)
    }

}
