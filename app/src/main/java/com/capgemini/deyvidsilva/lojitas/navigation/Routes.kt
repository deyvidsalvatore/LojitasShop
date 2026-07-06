package com.capgemini.deyvidsilva.lojitas.navigation

object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val CHECKOUT = "checkout"
    const val ORDERS = "orders"

    const val SEARCH = "search/{${Arguments.SEARCH_QUERY}}"
    const val DETAILS = "details/{${Arguments.PRODUCT_ID}}"

    fun criarRotaPesquisa(query: String): String {
        return "search/$query"
    }

    fun criarRotaDetalhes(productId: String): String {
        return "details/$productId"
    }
}