package com.example.isen_2021.basket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.isen_2021.detail.DetailViewFragment
import fr.isen.fermon.androidrestaurant.R
import fr.isen.fermon.androidrestaurant.databinding.ActivityBasketBinding

class BasketActivity : AppCompatActivity(), BasketCellInterface {
    lateinit var binding: ActivityBasketBinding
    private lateinit var basket: Basket
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        basket = Basket.getBasket(this)

        val fragment = BasketItemsFragment(basket, this)
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit()
    }

    override fun onDeleteItem(item: BasketItem) {
        basket.items.remove(item)
        basket.save(this)
    }

    override fun onShowDetail(item: BasketItem) {
        val fragment = DetailViewFragment(item.dish)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}