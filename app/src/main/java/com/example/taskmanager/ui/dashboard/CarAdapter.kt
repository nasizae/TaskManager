package com.example.taskmanager.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.model.Car
import com.example.taskmeneger.databinding.ItemTaskBinding

class CarAdapter(): RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private val list = arrayListOf<Car>()

    fun addCar(cars: List<Car>) {
        list.clear()
        list.addAll(cars)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(list[position])

    }

    inner class CarViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(car: Car) {
            binding.tvTitle.text = car.brand
            binding.tvDesc.text = car.model


        }
    }
}