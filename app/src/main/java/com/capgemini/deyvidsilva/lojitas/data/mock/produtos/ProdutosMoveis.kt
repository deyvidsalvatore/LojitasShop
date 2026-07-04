package com.capgemini.deyvidsilva.lojitas.data.mock.produtos

import com.capgemini.deyvidsilva.lojitas.data.mock.CategoriasMock
import com.capgemini.deyvidsilva.lojitas.domain.entity.Produto

object ProdutosMoveis {

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
        categoria = CategoriasMock.moveis,
        preco = preco,
        imagemProduto = android.R.drawable.ic_menu_gallery,
        estoque = estoque
    )

    val lista = listOf(
        produto(
            id = "MOV001",
            nome = "Mesa em L",
            descricao = "Mesa espaçosa em formato L, ideal para escritórios e setups com dois monitores.",
            preco = 799.90,
            estoque = 8
        ),
        produto(
            id = "MOV002",
            nome = "Mesa Gamer",
            descricao = "Mesa gamer com acabamento em fibra de carbono e suporte para headset e copo.",
            preco = 999.90,
            estoque = 6
        ),
        produto(
            id = "MOV003",
            nome = "Escrivaninha Moderna",
            descricao = "Escrivaninha compacta com design moderno, perfeita para estudos e home office.",
            preco = 449.90,
            estoque = 12
        ),
        produto(
            id = "MOV004",
            nome = "Cadeira de Escritório",
            descricao = "Cadeira ergonômica com ajuste de altura, apoio lombar e braços reguláveis.",
            preco = 699.90,
            estoque = 10
        ),
        produto(
            id = "MOV005",
            nome = "Estante Multiuso",
            descricao = "Estante com cinco prateleiras para livros, decoração e organização.",
            preco = 359.90,
            estoque = 9
        ),
        produto(
            id = "MOV006",
            nome = "Rack para TV",
            descricao = "Rack moderno para televisores de até 65 polegadas com nichos organizadores.",
            preco = 549.90,
            estoque = 7
        ),
        produto(
            id = "MOV007",
            nome = "Gaveteiro com Rodízios",
            descricao = "Gaveteiro com três gavetas e rodinhas para facilitar a organização do ambiente.",
            preco = 279.90,
            estoque = 15
        ),
        produto(
            id = "MOV008",
            nome = "Suporte para Monitor",
            descricao = "Suporte articulado para monitor com ajuste de altura e inclinação.",
            preco = 189.90,
            estoque = 18
        ),
        produto(
            id = "MOV009",
            nome = "Prateleira de Parede",
            descricao = "Prateleira em MDF para decoração e organização de ambientes.",
            preco = 119.90,
            estoque = 22
        ),
        produto(
            id = "MOV010",
            nome = "Armário Multiuso",
            descricao = "Armário com portas e prateleiras internas para armazenamento de diversos itens.",
            preco = 849.90,
            estoque = 5
        )
    )
}