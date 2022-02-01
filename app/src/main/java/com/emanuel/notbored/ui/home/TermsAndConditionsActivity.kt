package com.emanuel.notbored.ui.home

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.emanuel.notbored.databinding.ActivityTermsAndConditionsBinding

class TermsAndConditionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTermsAndConditionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsAndConditionsBinding.inflate(layoutInflater)
        //El primer paso en este proceso es "inflar" la clase de enlace de la vista para que podamos acceder a la vista raíz dentro del
        //disposición. Esta vista raíz se utilizará como vista de contenido para el diseño.
        setContentView(binding.root)
        //Configura el contenido de la actividad en una vista explícita. Esta vista se coloca directamente en la jerarquía de vistas de la actividad.
        // Puede ser en sí misma una jerarquía de vistas compleja.
        // Cuando se llama a este método, se ignoran los parámetros de diseño de la vista especificada.
        // Tanto la anchura como la altura de la vista se establecen por defecto en
        with(binding) {
            ibBack.setOnClickListener { onBackPressed() }
            //Se llama cuando la actividad ha detectado que el usuario ha pulsado la tecla de retroceso.
            // El OnBackPressedDispatcher tendrá la oportunidad de
            // manejar el botón de retroceso antes de que
            // se invoque el comportamiento por defecto de Activity.onBackPressed().
            tvTermsAndConditions.movementMethod = ScrollingMovementMethod()
            //Establece el MovementMethod para manejar el movimiento de las teclas de flecha para este TextView.
            // Esto puede ser null para no permitir el uso de las teclas de flecha para mover el cursor o desplazar la vista.
            //Avisa que si quieres que un TextView con un oyente de teclas o un método de movimiento no sea enfocable,
            // o si quieres que un TextView sin oyente de teclas o método de movimiento sea enfocable,
            // debe llamar a setFocusable de nuevo después de llamar a esto para obtener la capacidad de enfoque
            // de vuelta a la forma en que se desea.
            //Método de movimiento que interpreta las teclas de movimiento desplazando el buffer de texto.
        }
    }
}