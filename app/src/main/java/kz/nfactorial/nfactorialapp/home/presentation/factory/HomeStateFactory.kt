package kz.nfactorial.nfactorialapp.home.presentation.factory

import kz.nfactorial.nfactorialapp.R
import kz.nfactorial.nfactorialapp.home.presentation.HomeState
import kz.nfactorial.nfactorialapp.home.presentation.models.Banner
import kz.nfactorial.nfactorialapp.home.presentation.models.ChipItem
import kz.nfactorial.nfactorialapp.home.presentation.models.Collection
import kz.nfactorial.nfactorialapp.home.presentation.models.Price
import kz.nfactorial.nfactorialapp.home.presentation.models.Product
import kz.nfactorial.nfactorialapp.home.presentation.models.Rating
import kz.nfactorial.nfactorialapp.home.presentation.models.Store

class HomeStateFactory {

    fun createInitialState(): HomeState {
        return HomeState(
            account = null,
            searchField = "",
            banner = Banner(
                image = R.drawable.nike_banner,
                title = "It's Time for\nPayday Sale!",
                description = "Up to 70% off!",
            ),
            filters = listOf(
                ChipItem(1, "Sandals"),
                ChipItem(2, "Heels"),
                ChipItem(3, "Shoes"),
                ChipItem(4, "Slippers"),
                ChipItem(5, "Boots"),
                ChipItem(6, "Sneakers"),
                ChipItem(7, "Loafers"),
                ChipItem(8, "Oxfords"),
                ChipItem(9, "Moccasins"),
                ChipItem(10, "Flip-flops"),
            ),
            selectedFilterIds = emptySet(),
            collections = listOf(
                Collection(
                    name = "Trending Products",
                    products = listOf(
                        Product(
                            name = "Founds",
                            id = 1,
                            image = R.drawable.founds,
                            price = Price(45, "$"),
                        ),
                        Product(
                            name = "Demix",
                            id = 2,
                            image = R.drawable.demix,
                            price = Price(40, "$"),
                        ),
                        Product(
                            name = "Dino Ricci",
                            id = 3,
                            image = R.drawable.dino_ricci,
                            price = Price(38, "$"),
                        ),
                        Product(
                            name = "Adidas Originals",
                            id = 4,
                            image = R.drawable.adidas_originals,
                            price = Price(100, "$"),
                        )
                    ),
                )
            ),
            stores = listOf(
                Store(
                    id = 0,
                    name = "Lamoda",
                    image = R.drawable.store_lamoda,
                    rating = Rating(4.7f, 2300),
                ),
                Store(
                    id = 1,
                    name = "Zara",
                    image = R.drawable.store_zara,
                    rating = Rating(4.4f, 21200),
                    products = listOf(
                        Product(
                            name = "Founds",
                            id = 1,
                            image = R.drawable.shoes_1,
                            price = Price(45, "$"),
                        ),
                        Product(
                            name = "Demix",
                            id = 2,
                            image = R.drawable.shoes_5,
                            price = Price(40, "$"),
                        ),
                        Product(
                            name = "Dino Ricci",
                            id = 3,
                            image = R.drawable.shoes_6,
                            price = Price(38, "$"),
                        ),
                        Product(
                            name = "Adidas Originals",
                            id = 4,
                            image = R.drawable.shoes_7,
                            price = Price(100, "$"),
                        ),
                    )
                ),
                Store(
                    id = 2,
                    name = "Intertop",
                    image = R.drawable.store_intertop,
                    rating = Rating(3.4f, 500),
                ),
                Store(
                    id = 3,
                    name = "Adidas",
                    image = R.drawable.store_adidas,
                    rating = Rating(5.0f, 23000),
                )
            ),
        )
    }
}