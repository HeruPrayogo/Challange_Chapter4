package com.example.challange_chapter4

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

import com.example.challange_chapter4.databinding.ItemNoteBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AdapterNote(var listData: List<RoomData>): RecyclerView.Adapter<AdapterNote.ViewHolder>() {
    var dbNote: DatabaseNote? = null
    class ViewHolder(var binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun getData(itemData: RoomData){
            binding.data = itemData
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getData(listData[position])
        holder.binding.deleteNote.setOnClickListener {
            dbNote = DatabaseNote.getInstance(it.context)
            GlobalScope.async {
                SetViewModel(Application()).deleteData(listData[position])
                dbNote?.noteDao()?.deleteData(listData[position])
                Navigation.findNavController(it).navigate(R.id.homeFragment)
            }
        }
        holder.binding.editNote.setOnClickListener{
            val bundle = Bundle()
            bundle.putSerializable("data", listData[position])
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_editFragment, bundle)
        }
    }

    fun setData(listData: ArrayList<RoomData>){
        this.listData=listData
    }
}