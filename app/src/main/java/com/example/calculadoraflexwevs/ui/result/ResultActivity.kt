package com.example.calculadoraflexwevs.ui.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculadoraflexwevs.BaseActivity
import com.example.calculadoraflexwevs.R
import com.example.calculadoraflexwevs.extensions.format
import com.example.calculadoraflexwevs.model.CarData
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        calculate()
    }

    private fun calculate() {
        val carData = intent.extras?.getParcelable<CarData>("CAR_DATA")
        carData?.run {
            val performanceOfMyCar = carData.ethanolAverage / carData.gasAverage
            val priceOfFuelIndice = carData.ethanolPrice / carData.gasPrice
            if (priceOfFuelIndice <= performanceOfMyCar) {
                tvSuggestion.text = getString(R.string.ethanol)
            } else {
                tvSuggestion.text = getString(R.string.gasoline)
            }
            tvEthanolAverageResult.text = (ethanolPrice / ethanolAverage).format(2)
            tvGasAverageResult.text = (ethanolPrice / gasAverage).format(2)

            tvFuelRatio.text = getString(R.string.label_fuel_ratio, performanceOfMyCar.format(2))
        }
    }
}



