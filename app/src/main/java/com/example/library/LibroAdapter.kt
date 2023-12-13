package com.example.library

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.library.data.Libro

class LibroAdapter : RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {

    private var libros: List<Libro> = emptyList()

    inner class LibroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewFicha: TextView = itemView.findViewById(R.id.textViewFicha)

        val textViewTitulo: TextView = itemView.findViewById(R.id.textViewTitulo)
        val textViewAutor: TextView = itemView.findViewById(R.id.textViewAutor)
        val textViewISBN: TextView = itemView.findViewById(R.id.textViewISBN)
        val textViewEditorial: TextView = itemView.findViewById(R.id.textViewEditorial)
        val imageViewPortada: ImageView = itemView.findViewById(R.id.imageViewPortada)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_libro, parent, false)
        return LibroViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = libros[position]
        holder.textViewFicha.text = "Ficha: ${libro.woResultadoOpacPK.ficha ?:"Desconocido"}"
        holder.textViewTitulo.text = "Titulo: ${libro.titulo ?:"Desconocido"}"
        holder.textViewAutor.text = "Autor: ${libro.autor ?:"Desconocido"}"
        holder.textViewISBN.text = "ISBN: ${libro.isbn ?:"Desconocido"}"
        holder.textViewEditorial.text = "Editorial: ${libro.editorial ?:"Desconocido"}"

        holder.imageViewPortada.setImageResource(R.drawable.library_icon)

        holder.itemView.setOnClickListener {
            showInfoDialog(holder.itemView.context, libro)
        }
    }

    private fun showInfoDialog(context: Context, libro: Libro) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_info, null)
        val alertDialogBuilder = AlertDialog.Builder(context)

        alertDialogBuilder.setView(dialogView)


        val textViewSigNum = dialogView.findViewById<TextView>(R.id.textViewSignaturaNumber)
        val textViewSig = dialogView.findViewById<TextView>(R.id.textViewSignatura)

        val txtedicion = dialogView.findViewById<TextView>(R.id.textViewEdicion)
        val txtInfoEdicion = dialogView.findViewById<TextView>(R.id.textViewinfoEdicion)

        val txtDF = dialogView.findViewById<TextView>(R.id.textViewDfisica)
        val txtinfoDF = dialogView.findViewById<TextView>(R.id.textViewinfoDfisica)

        textViewSig.text = "Signatura Topografica: "
        textViewSigNum.text = "${libro.signatura}"

        txtedicion.text = "Edicion: "
        txtInfoEdicion.text = "${libro.edicion}"

        txtDF.text = "D. Fisica: "
        txtinfoDF.text = "${libro.dfisica}"



        alertDialogBuilder.setPositiveButton("Cerrar") { dialog, _ -> dialog.dismiss() }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()


    }

    override fun getItemCount(): Int {
        return libros.size
    }

    fun submitList(libros: List<Libro>) {
        this.libros = libros
        notifyDataSetChanged()
    }
}
