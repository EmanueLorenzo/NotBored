package com.emanuel.notbored.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.emanuel.notbored.databinding.ActivityMainBinding
import com.emanuel.notbored.ui.activity.ActivitiesActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //El primer paso en este proceso es "inflar" la clase de enlace de la vista para que podamos acceder a la vista raíz dentro del
        //disposición. Esta vista raíz se utilizará como vista de contenido para el diseño.
        //Configurar el contenido de la actividad en una vista explícita. Esta vista se coloca directamente en la jerarquía de vistas de la actividad.
        // Puede ser en sí misma una jerarquía de vistas compleja. Cuando se llama a este método, se ignoran los parámetros de diseño de la vista especificada.
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        with(binding) {
            tvConditions.setOnClickListener { goToTermsActivity() }
            //Registrar una llamada de retorno para ser invocada cuando se haga clic en esta vista.
            // Si esta vista no es clicable, se convierte en clicable
            //Agregar una acción que será invocada después del cambio de texto.
            etParticipants.doAfterTextChanged { afterTextChanged() }
            btnStart.setOnClickListener { goToActivities() }
        }
    }


    private fun goToActivities() {
        val intent = Intent(this, ActivitiesActivity::class.java)
        //Crear una intención para un componente específico
        intent.putExtra("INTENT_PART", binding.etParticipants.text.toString())
        startActivity(intent)
    }

    private fun afterTextChanged() {
        val text = binding.etParticipants.text.toString()
        //Retorno el texto de la cantidad de participantes que ha puesto el usuario y lo convierto
        //a string
        checkValue(text)
    }

    private fun checkValue(text: String) {
        //La funcion verifica que el usuario ha ingresado un numero mayor a 0 y si la secuencia no esta
        //vacia entonces el boton se activa sino da error
        with(binding) {
            try {
                if (text.toInt() > 0 && text.isNotEmpty()) {
                    btnStart.isEnabled = true
                } else showError()
            } catch (e: Exception) {
                showError()
            }
        }

    }

    private fun showError() {
        //Le indicamos al usuario en un toast de tiempo determinado que tiene que ingresar
        // solo numeros positivos
        Toast.makeText(
            this@MainActivity,
            "Only positive number",
            Toast.LENGTH_SHORT
        ).show()
        binding.btnStart.isEnabled = false
    }

    private fun goToTermsActivity() {
        //Comienza la actividad terminos y condiciones
        startActivity(Intent(this, TermsAndConditionsActivity::class.java))
    }

}
