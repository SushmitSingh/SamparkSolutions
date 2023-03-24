package com.example.samparksolutions.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samparksolutions.data.Alphabet

class MyViewModel: ViewModel() {

    val listA = MutableLiveData<List<Alphabet>>()
    val listB = MutableLiveData<List<Alphabet>>()

    init {
        val items = listOf(
            Alphabet("A"),
            Alphabet("B"),
            Alphabet("C"),
            Alphabet("D"),
            Alphabet("E"),
            Alphabet("F"),
            Alphabet("G"),
            Alphabet("H"),
            Alphabet("I"),
            Alphabet("J")
        )
        listA.value = items
        listB.value = emptyList()
    }

    fun moveSelectedItemsToLeft() {
        val selectedItems = listB.value!!.filter { it.selected }
        listB.value = listB.value!!.filter { !it.selected }
        listA.value = listA.value!! + selectedItems
    }

    fun moveSelectedItemsToRight() {
        val selectedItems = listA.value!!.filter { it.selected }
        listA.value = listA.value!!.filter { !it.selected }
        listB.value = listB.value!! + selectedItems
    }

    fun onAlphabetSelected(alphabet: Alphabet) {
        alphabet.selected = !alphabet.selected
    }
}

