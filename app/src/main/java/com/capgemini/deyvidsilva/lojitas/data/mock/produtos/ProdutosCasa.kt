package com.capgemini.deyvidsilva.lojitas.data.mock.produtos

import com.capgemini.deyvidsilva.lojitas.data.mock.CategoriasMock
import com.capgemini.deyvidsilva.lojitas.domain.entity.Produto

object ProdutosCasa {

    private fun produto(
        id: String,
        nome: String,
        descricao: String,
        preco: Double,
        estoque: Int
    ) = Produto(
        id = id,
        nome = nome,
        descricao = descricao,
        categoria = CategoriasMock.casa,
        preco = preco,
        imagemProduto = android.R.drawable.ic_menu_gallery,
        estoque = estoque
    )

    val lista = listOf(
        produto(
            id = "CAS001",
            nome = "Cafeteira Elétrica",
            descricao = "Cafeteira com capacidade para 15 xícaras e sistema corta-pingos.",
            preco = 199.90,
            estoque = 14
        ),
        produto(
            id = "CAS002",
            nome = "Liquidificador 1200W",
            descricao = "Liquidificador com 12 velocidades e copo de 3 litros.",
            preco = 249.90,
            estoque = 10
        ),
        produto(
            id = "CAS003",
            nome = "Air Fryer 5L",
            descricao = "Fritadeira elétrica sem óleo com capacidade de 5 litros.",
            preco = 429.90,
            estoque = 8
        ),
        produto(
            id = "CAS004",
            nome = "Aspirador de Pó Vertical",
            descricao = "Aspirador compacto e potente para limpeza diária da casa.",
            preco = 359.90,
            estoque = 11
        ),
        produto(
            id = "CAS005",
            nome = "Ventilador de Coluna",
            descricao = "Ventilador de 40cm com três velocidades e altura ajustável.",
            preco = 289.90,
            estoque = 13
        ),
        produto(
            id = "CAS006",
            nome = "Ferro de Passar a Vapor",
            descricao = "Ferro com base antiaderente e função vapor contínuo.",
            preco = 139.90,
            estoque = 17
        ),
        produto(
            id = "CAS007",
            nome = "Luminária LED de Mesa",
            descricao = "Luminária com intensidade regulável e iluminação em LED.",
            preco = 99.90,
            estoque = 25
        ),
        produto(
            id = "CAS008",
            nome = "Panela Elétrica",
            descricao = "Panela multifuncional para preparo de arroz, legumes e sopas.",
            preco = 269.90,
            estoque = 9
        ),
        produto(
            id = "CAS009",
            nome = "Jogo de Panelas 5 Peças",
            descricao = "Conjunto antiaderente com tampas de vidro temperado.",
            preco = 399.90,
            estoque = 7
        ),
        produto(
            id = "CAS010",
            nome = "Umidificador de Ar",
            descricao = "Umidificador ultrassônico silencioso com reservatório de 4 litros.",
            preco = 219.90,
            estoque = 16
        )
    )
}