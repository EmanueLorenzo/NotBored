package com.emanuel.notbored.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.notbored.databinding.ItemActivitiesBinding
import com.emanuel.notbored.model.ActivitiesModel

class ActivityAdapter(

    private val activities: List<ActivitiesModel>,
    //Una lista con cada actividad
    //Los detalles de cada actividad
    private val onActivityClickListener: OnActivityClickListener
) :

    RecyclerView.Adapter<ActivityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //El primer paso en este proceso es "inflar" la clase de enlace de la vista para que podamos acceder a la vista raíz dentro del
        //disposición. Esta vista raíz se utilizará como vista de contenido para el diseño.
        //Configurar el contenido de la actividad en una vista explícita. Esta vista se coloca directamente en la jerarquía de vistas de la actividad.
        // Puede ser en sí misma una jerarquía de vistas compleja. Cuando se llama a este método, se ignoran los parámetros de diseño de la vista especificada.
        val binding = ItemActivitiesBinding.inflate(layoutInflater, parent, false)
        return ActivityViewHolder(binding, onActivityClickListener)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.bind(activities[position])
    }

    override fun getItemCount(): Int = activities.size

}

class ActivityViewHolder(
    private val binding: ItemActivitiesBinding,
    onActivityClickListener: OnActivityClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(activity: ActivitiesModel) {
        binding.run {
            tvActivity.text = activity.category
            //Muestra el texto de la actividad
        }
    }

    init {
        binding.btnNext.setOnClickListener{
            //Cambia de actividad al presionar el boton
            onActivityClickListener.onActivityItemClicked(adapterPosition)
        }
    }
}

interface OnActivityClickListener {
    fun onActivityItemClicked(position: Int)
}