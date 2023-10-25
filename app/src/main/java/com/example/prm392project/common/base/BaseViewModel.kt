package com.example.cstore.common.base

import androidx.lifecycle.ViewModel
import com.google.gson.Gson

open class BaseViewModel : ViewModel() {
    /* **********************************************************************
     * Variable
     ********************************************************************** */
    protected val gson = Gson()

}