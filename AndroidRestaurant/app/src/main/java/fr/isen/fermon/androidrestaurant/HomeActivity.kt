package fr.isen.fermon.androidrestaurant

import android.content.Intent
import android.os.Bundle
import fr.isen.fermon.androidrestaurant.category.CategoryActivity
import fr.isen.fermon.androidrestaurant.category.ItemType
import fr.isen.fermon.androidrestaurant.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.starter.setOnClickListener {
            startCategoryActivity(ItemType.entree)
        }

        binding.main.setOnClickListener {
            startCategoryActivity(ItemType.plat)
        }

        binding.dessert.setOnClickListener {
            startCategoryActivity(ItemType.DESSERT)
        }

    }

    private fun startCategoryActivity(item: ItemType) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra(CATEGORY_NAME, item)
        startActivity(intent)
    }

    companion object {
        const val CATEGORY_NAME = "CATEGORY_NAME"
    }
}