package com.emanuel.notbored.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.emanuel.notbored.R
import com.emanuel.notbored.databinding.ActivityActivitiesBinding
import com.emanuel.notbored.model.Activities
import com.emanuel.notbored.model.ActivitiesModel
import com.emanuel.notbored.ui.details.DetailsActivity
import com.emanuel.notbored.viewmodel.ActivityAdapter
import com.emanuel.notbored.viewmodel.OnActivityClickListener

class ActivitiesActivity : AppCompatActivity(), OnActivityClickListener {
    private val activities: List<ActivitiesModel> = Activities.activitiesList

    //Binding: El proceso de preparar una vista hija para mostrar los datos correspondientes a una posición dentro del adaptador.
    //Reciclar (vista): Una vista utilizada previamente para mostrar los datos correspondientes a una posición específica del adaptador puede
    // ser colocada en una caché para su posterior reutilización para volver a mostrar el mismo tipo de datos más adelante.
    // Esto puede mejorar drásticamente el rendimiento al omitir el inflado o la construcción del diseño inicial.

    private lateinit var activityAdapter: ActivityAdapter
    private lateinit var binding: ActivityActivitiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Establecer el contenido de la actividad en una vista explícita.
        // Esta vista se coloca directamente en la jerarquía de vistas de la actividad
        initUI()
        //Ayudante para acceder a las características de los recursos.
        this.window.statusBarColor = ResourcesCompat.getColor(resources, R.color.color_b, null)
    }

    private fun initUI() {
        binding.btnRandom.setOnClickListener {
            val positionRandom = getRandomNum()
            //Devuelve una posición random
            goToDetails(positionRandom, true)
        }
        initRecycler()
    }

    private fun initRecycler() {
        //Una vista flexible para proporcionar una ventana limitada a un gran conjunto de datos.
        activityAdapter = ActivityAdapter(Activities.activitiesList, this)
        with(binding.listActivities) {
            layoutManager = LinearLayoutManager(context)
            //Establece el RecyclerView.LayoutManager que utilizará este RecyclerView.
            adapter = activityAdapter
            //Establece un nuevo adaptador para proporcionar vistas hijo bajo demanda.
            //Cuando se cambia el adaptador, todas las vistas existentes se reciclan al pool.
            // Si el pool sólo tiene un adaptador, se despejará
        }
    }

    override fun onActivityItemClicked(position: Int) {
        goToDetails(position, false)
    }

    private fun goToDetails(position: Int, random: Boolean) {
        val participant = intent.extras?.getString("INTENT_PART")!!
        val activity = activities[position]
        val intent = Intent(this, DetailsActivity::class.java)
        //Crear una intención para un componente específico
        intent.putExtra("INTENT_PART", participant)
        intent.putExtra("INTENT_CAT", activity.category)
        intent.putExtra("INTENT_PRICE", activity.price)
        intent.putExtra("INTENT_INFO", activity.info)
        intent.putExtra("INTENT_RANDOM", random)
        startActivity(intent)
        //Comienza la actividad
    }

    private fun getRandomNum(): Int = activities.indices.random()
    //Devuelve un numero random
}