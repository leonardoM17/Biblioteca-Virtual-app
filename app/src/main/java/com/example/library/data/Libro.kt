package com.example.library.data

data class Libro(
    val woResultadoOpacPK: woResultadoOpacPK,
    val titulo: String?,
    val autor: String?,
    val isbn: String?,
    val editorial: String?,
    val edicion: String?,
    val signatura: String?,
    val dfisica: String?

)


data class woResultadoOpacPK(
    val fkWaEmpresa: Int,
    val ficha: String?
)
