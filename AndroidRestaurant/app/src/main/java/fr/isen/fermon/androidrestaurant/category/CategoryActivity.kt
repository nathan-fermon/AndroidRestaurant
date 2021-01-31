package com.example.isen_2021

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.HttpResponse
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.isen_2021.category.CategoryAdapter
import com.google.gson.GsonBuilder
import fr.isen.fermon.androidrestaurant.HomeActivity
import fr.isen.fermon.androidrestaurant.R
import fr.isen.fermon.androidrestaurant.databinding.ActivityCategoryBinding
import network.MenuResult
import org.json.JSONObject

enum class ItemType {
    entree, plat, dessert, ENTREE,PLAT,DESSERT }
    companion object {
        fun title (item: ItemType?): String{
            return when (item){
                ItemType.entree ->"Entrées"
                ItemType.plat -> "Plats"
                ItemType.DESSERT -> "Desserts"
                else -> ""
            }
        }
    }
}

class CategoryActivity : AppCompatActivity() {

    private lateinit var bindind: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        val selectedItem = intent.getSerializableExtra(HomeActivity.CATEGORY_NAME) as? ItemType
        bindind.categoryTitle.text = getCategoryTitle(selectedItem)

        loadList()

        Log.d("lifecycle", "onCreate")
    }

    private fun loadList(items:List <String>?) {
        items?.let {
            List<String>
            var entries = listOf<String>("salade", "boeuf", "glace")
            val adapter = CategoryAdapter(entries)
            bindind.recyclerView.layoutManager = LinearLayoutManager(this)
            bindind.recyclerView.adapter = adapter
        }
    }

    private fun getCategoryTitle(item: ItemType?): String {
        return when(item) {
            ItemType.entree -> getString(R.string.entree)
            ItemType.plat -> getString(R.string.plat)
            ItemType.DESSERT -> getString(R.string.dessert)
            else -> ""
        }
    }

    private fun launchRequest(item: ItemType){
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val parameter = JSONObject()
        parameter.put("id_shop",1)
        val request = JsonObjectRequest(Request.Method.POST,
        url,
        parameter,
            {
                val menuResult = GsonBuilder().create().fromJson(it.toString(),MenuResult::class.java)
                val items = menuResult.data.firstOrNull { it.name == ItemType.title(item) }
                loadList(items?.items?.map { it.name })
            },
            {
                Log.d("request", it.toString())
            })
        queue.add((request))
    }

    private fun cacheResult (response: String) {
        val sharedPreferences = getSharedPreferences(USER_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString((REQUEST_CACHE,response))
        editor.apply()
    }


    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle", "onRestart")
    }

    override fun onDestroy() {
        Log.d("lifecycle", "onDestroy")
        super.onDestroy()
    }

    companion object{
        const val USER_PREFERENCE_NAME = "USER_PREFERENCE_NAME"
        const val REQUEST_CACHE = "REQUEST_CACHE"
    }
}
