package com.emanuel.notbored.model

data class ActivitiesModel(
    //Cada actividad contiene
    //un identificador
    //la categoria
    //el precio
    //y la informacion
    val id: Int,
    val category: String,
    val price : Double,
    val info : String
)
