package com.example.projectakhirpraktikum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter_batik(private var data: ArrayList<class_batik>, private val listener: OnItemClickListener) : RecyclerView.Adapter<adapter_batik.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter_batik.CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_data_batik,
            parent, false
        )
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: adapter_batik.CourseViewHolder, position: Int) {
        val batik = data[position]
        holder.bind(batik, listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setData(newData: ArrayList<class_batik>) {
        data = newData
        notifyDataSetChanged()
    }

    fun updateList(newList: List<class_batik>) {
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }


    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foto: ImageView = itemView.findViewById(R.id.idfoto)
        val nama: TextView = itemView.findViewById(R.id.idnama_batik)
        val asal: TextView = itemView.findViewById(R.id.id_asaldaerah)

        fun bind(batik: class_batik, listener: OnItemClickListener) {
            foto.setImageResource(batik.foto)
            nama.text = batik.nama
            asal.text = batik.asal

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}
