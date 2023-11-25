package com.faza.challenge_4.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faza.challenge_4.api.ApiClient
import com.faza.challenge_4.model.Cart
import com.faza.challenge_4.model.CategoryMenu
import com.faza.challenge_4.model.OrderReq
import com.faza.challenge_4.model.OrderResponse
import com.faza.challenge_4.repository.CartRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel (application: Application) : ViewModel(){
    private val repo: CartRepository = CartRepository(Application())

    val allOrder: LiveData<List<Cart>> = repo.getAllCartOrder()
    private val order = MutableLiveData<Boolean>()
    val orderSucces: LiveData<Boolean> = order

    fun deleteCart(cartId: Int) {
       repo.deleteCart(cartId)
    }

    fun deleteItem() {
        repo.deleteItem()
    }

    private fun updateQuantity (cart: Cart) {
        repo.update(cart)
    }

    @SuppressLint("SuspiciousIndentation")
    fun increment(cart: Cart){
        val total = cart.quantity + 1
        cart.totalAll = cart.foodPrice * total
        updateQuantity(cart)
    }

    fun decrement(cart: Cart) {
        val total = cart.quantity - 1
        cart.totalAll = cart.foodPrice * total
        updateQuantity(cart)
    }

    fun postData(orderReq: OrderReq){
        ApiClient.instance.postOrder(orderReq)
            .enqueue(object : Callback<OrderResponse> {
                override fun onResponse(
                    call: Call<OrderResponse>,
                    response: Response<OrderResponse>
                ) {

                    if (response.isSuccessful){
                        order.postValue(true)
                        deleteItem()
                    } else {
                        order.postValue(false)
                    }
                }

                override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                    Log.e("Error", "message= $t")
                }

            })
    }

}
