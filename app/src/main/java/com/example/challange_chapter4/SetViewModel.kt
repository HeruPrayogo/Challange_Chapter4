package com.example.challange_chapter4

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SetViewModel(app: Application): AndroidViewModel(app) {
    var note: MutableLiveData<List<RoomData>> = MutableLiveData()

    init {
        getAllNote()
    }
    fun getNoteObserver(): MutableLiveData<List<RoomData>>{
        return note
    }

    fun getAllNote() {
        GlobalScope.launch {
            val userDao = DatabaseNote.getInstance(getApplication())!!.noteDao()
            val listNote = userDao.getDataAsc()
            note.postValue(listNote)
        }
    }
    fun insertData(data: RoomData){
        val user = DatabaseNote.getInstance(getApplication())!!.noteDao()
        user!!.insertData(data)
        getAllNote()
    }
    fun deleteData(data: RoomData){
        val user = DatabaseNote.getInstance(getApplication())!!.noteDao()
        user?.deleteData(data)
        getAllNote()
    }
    fun updateData(data: RoomData){
        val user = DatabaseNote.getInstance(getApplication())!!.noteDao()
        user?.updateData(data)
        getAllNote()
    }

}



