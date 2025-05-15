package com.example.productapp.data.local

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object PrepopulateDatabase {

    fun insertDefaultProducts(productDao: ProductDao) {
        CoroutineScope(Dispatchers.IO).launch {
            if (productDao.getAllProductsOnce().isEmpty()) {
                val products = listOf(
                    ProductEntity(name = "Avocado", price = 16.95, imageUrl = "https://cdn.pixabay.com/photo/2024/01/09/22/11/avocado-8498520_1280.jpg", quantity = 10),
                    ProductEntity(name = "Carrots", price = 29.99, imageUrl = "https://media.istockphoto.com/id/1388403435/photo/fresh-carrots-isolated-on-white-background.jpg?s=2048x2048&w=is&k=20&c=9sTXjB11hCYhq6VQVltZu3_QyHlygKVIbwi3iPVBhZw=", quantity = 20),
                    ProductEntity(name = "Bread", price = 39.99, imageUrl = "https://cdn.pixabay.com/photo/2018/06/10/20/30/bread-3467243_960_720.jpg", quantity = 15),
                    ProductEntity(name = "Honey", price = 49.99, imageUrl = "https://cdn.pixabay.com/photo/2020/04/14/18/13/honey-5043708_1280.jpg", quantity = 3),
                    ProductEntity(name = "Ice Cream", price = 59.99, imageUrl = "https://cdn.pixabay.com/photo/2017/11/30/08/56/ice-cream-2987955_1280.jpg", quantity = 5),
                    ProductEntity(name = "Eggs", price = 69.99, imageUrl = "https://cdn.pixabay.com/photo/2016/05/05/15/29/eggs-1374141_960_720.jpg", quantity = 30),
                    ProductEntity(name = "Marshmallow", price = 79.99, imageUrl = "https://cdn.pixabay.com/photo/2017/08/29/08/51/marshmallow-2692477_1280.jpg", quantity = 25),
                    ProductEntity(name = "Nuts", price = 89.99, imageUrl = "https://cdn.pixabay.com/photo/2017/11/22/22/53/nuts-2971675_1280.jpg", quantity = 1),
                    ProductEntity(name = "Potatoes", price = 99.99, imageUrl = "https://cdn.pixabay.com/photo/2018/05/29/23/18/potato-3440360_960_720.jpg", quantity = 40),
                    ProductEntity(name = "Coffee", price = 109.99, imageUrl = "https://cdn.pixabay.com/photo/2021/09/17/12/12/coffee-6632524_1280.jpg", quantity = 12)
                )
                productDao.insertProducts(products)
            }
        }
    }
}
