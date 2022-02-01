package com.emanuel.notbored.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.emanuel.notbored.R
import com.emanuel.notbored.databinding.ActivityDetailsBinding
import com.emanuel.notbored.model.Activities
import com.emanuel.notbored.model.ActivitiesModel

class DetailsActivity : AppCompatActivity() {
    private val activities: List<ActivitiesModel> = Activities.activitiesList
    //Una lista con cada actividad
    //Los detalles de cada actividad
    //inicializamos el ActivityDetailsBinding
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //Recuperar la ventana actual de la actividad.
        //Ajusta el color de la barra de estado al color b #1E88E5
        //Devuelve un entero de color temático asociado a un determinado ID de recurso
        this.window.statusBarColor = ResourcesCompat.getColor(resources, R.color.color_b, null)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        with(binding) {
            btnBack.setOnClickListener { onBackPressed() }
            //Se habilita el botón para cuando el usuario lo toque vuelva hacia atras
            btnTry.setOnClickListener { tryAnother() }
            //Se habilita el botón para cuando el usuario lo toque cambie de actividad

        }
        takeAndShowInfo()
    }

    private fun tryAnother() {
        //En esta función se va cambiando de actividad en un modo random
        val randomNum = activities.indices.random()
        val activity = activities[randomNum]
        val category = activity.category
        val price = activity.price
        val info = activity.info
        showText(category, info, price)
    }

    @SuppressLint("SetTextI18n")
    private fun takeAndShowInfo() {
        //Muestra la informacion de la actividad
        val price = intent.extras?.getDouble("INTENT_PRICE")!!
        val category = intent.extras?.getString("INTENT_CAT")
        val info = intent.extras?.getString("INTENT_INFO")
        showText(category, info, price)
    }

    @SuppressLint("SetTextI18n")
    private fun showText(category: String?, info: String?, price: Double) {
        val participants = intent.extras?.getString("INTENT_PART")!!
        val random = intent.extras?.getBoolean("INTENT_RANDOM")!!

        with(binding) {
            if (random) {
                tvActivityTitle.text = "Random"
                with(tvRandom) {
                    isVisible = true
                    text = category
                }
                tvParticipant.text = participants
                tvInfo.text = info
                tvPrice.text =
                        //Establesco las categorias sobre el precio de cada actividad
                    when (price) {
                        0.0 -> "Free"
                        in 0.0..0.3 -> "Low"
                        in 0.3..0.6 -> "Medium"
                        in 0.6..1.0 -> "High"
                        else -> ""
                    }
            } else {
                tvActivityTitle.text = category
                tvParticipant.text = participants
                tvInfo.text = info
                tvPrice.text =
                    when (price) {
                        0.0 -> "Free"
                        in 0.0..0.3 -> "Low"
                        in 0.3..0.6 -> "Medium"
                        in 0.6..1.0 -> "High"
                        else -> ""
                    }
            }
        }
    }
}