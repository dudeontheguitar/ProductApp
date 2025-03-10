package com.example.productapp.data.local

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object PrepopulateDatabase {

    fun insertDefaultProducts(productDao: ProductDao) {
        CoroutineScope(Dispatchers.IO).launch {
            if (productDao.getAllProductsOnce().isEmpty()) { // Проверяем, есть ли уже товары
                val products = listOf(
                    ProductEntity(1, "Avocado", 19.99, "https://cdn.pixabay.com/photo/2024/01/09/22/11/avocado-8498520_1280.jpg", 0),
                    ProductEntity(2, "Carrots",29.99, "https://media.istockphoto.com/id/1388403435/photo/fresh-carrots-isolated-on-white-background.jpg?s=2048x2048&w=is&k=20&c=9sTXjB11hCYhq6VQVltZu3_QyHlygKVIbwi3iPVBhZw=", 0),
                    ProductEntity(3, "Bread", 39.99, "https://cdn.pixabay.com/photo/2018/06/10/20/30/bread-3467243_960_720.jpg", 0),
                    ProductEntity(4, "Honey", 49.99,"https://cdn.pixabay.com/photo/2020/04/14/18/13/honey-5043708_1280.jpg",  0),
                    ProductEntity(5, "Ice Cream",59.99, "https://cdn.pixabay.com/photo/2017/11/30/08/56/ice-cream-2987955_1280.jpg",  0),
                    ProductEntity(6, "Eggs", 69.99,"https://cdn.pixabay.com/photo/2016/05/05/15/29/eggs-1374141_960_720.jpg",  0),
                    ProductEntity(7, "Marshmallow", 79.99,"https://cdn.pixabay.com/photo/2017/08/29/08/51/marshmallow-2692477_1280.jpg",  0),
                    ProductEntity(8, "Nuts", 89.99,"https://cdn.pixabay.com/photo/2017/11/22/22/53/nuts-2971675_1280.jpg",  0),
                    ProductEntity(9, "Potatoes", 99.99,"https://cdn.pixabay.com/photo/2018/05/29/23/18/potato-3440360_960_720.jpg",  0),
                    ProductEntity(10, "Coffee", 109.99,"https://cdn.pixabay.com/photo/2021/09/17/12/12/coffee-6632524_1280.jpg",  0)
                )
                productDao.insertProducts(products) // Заполняем базу данных
            }
        }
    }
}
