package com.example.taskmeneger.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref (private val context: Context) {

    private val pref= context.getSharedPreferences(PREF_NAME,MODE_PRIVATE)

    fun isOnBoardingShow():Boolean{
        return pref.getBoolean(SHOWED_KEY,false)
    }

    fun onOnBoardingShowed(){
        pref.edit().putBoolean(SHOWED_KEY,true).apply()
    }
    fun seveName(name:String){
        pref.edit().putString(SEVE_NAME,name).apply()
    }

    fun getName():String?{
        return pref.getString(SEVE_NAME,null)
    }
    fun seveImage(image:String){
        pref.edit().putString(IMAGE_KEY,image).apply()
    }

    fun getImage():String?{
        return pref.getString(IMAGE_KEY,null)
    }

    companion object{
        const val PREF_NAME="pref_name"
        const val SHOWED_KEY="seen_key"
        const val SEVE_NAME="seve_name"
        const val IMAGE_KEY="image_key"
    }
}