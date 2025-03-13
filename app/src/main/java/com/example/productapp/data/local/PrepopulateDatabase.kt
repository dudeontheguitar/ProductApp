package com.example.productapp.data.local

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object PrepopulateDatabase {

    fun insertDefaultProducts(productDao: ProductDao) {
        CoroutineScope(Dispatchers.IO).launch {
            if (productDao.getAllProductsOnce().isEmpty()) { // Проверяем, есть ли уже товары
                val products = listOf(
                    ProductEntity(1, "Sonic", 100.50, "https://s.iimg.su/s/11/G1cl0yNRAt8wxVdQGGTpOQ8BgTZOXcvW7OnUE0Xs.jpg", 0),
                    ProductEntity(2, "Balabol",29.50, "https://s.iimg.su/s/12/zexarhow5rLgee7ciuVQlKb9AMq7yMNrXS7q7uqE.jpg", 0),
                    ProductEntity(3, "Tysyp qoigan bala", 39.50, "https://s.iimg.su/s/12/voV9yz8BtWvoGLYYHhCA8MmNz5pB58Fjp8R5asVC.jpg", 0),
                    ProductEntity(4, "Honey", 49.40,"https://s.iimg.su/s/12/2DPkUCNR1gW5ndEOsRxw5cdgQds7ujxMnnkCoVSS.jpg",  0),
                    ProductEntity(5, "Ice Cream",59.30, "https://s.iimg.su/s/12/6oZhIpVlrhMCHdrAK9lgiev8OZ3W5hWaW1HslIKv.jpg",  0),
                    ProductEntity(6, "Sdu", 69.60,"https://s.iimg.su/s/12/0sUQKCsc5JUYjxXeL6V905q7rOy2biVERaO15dDy.jpg",  0),
                    ProductEntity(7, "Marshmallow", 79.60,"https://cdn.pixabay.com/photo/2017/08/29/08/51/marshmallow-2692477_1280.jpg",  0),
                    ProductEntity(8, "Nuts", 89.50,"https://cdn.pixabay.com/photo/2017/11/22/22/53/nuts-2971675_1280.jpg",  0),
                    ProductEntity(9, "Potatoes", 99.30,"https://cdn.pixabay.com/photo/2018/05/29/23/18/potato-3440360_960_720.jpg",  0),
                    ProductEntity(10, "Coffee", 109.20,"https://cdn.pixabay.com/photo/2021/09/17/12/12/coffee-6632524_1280.jpg",  0)
                )
                productDao.insertProducts(products) // Заполняем базу данных
            }
        }
    }
}
