package com.capgemini.deyvidsilva.lojitas.data.mock.produtos

import com.capgemini.deyvidsilva.lojitas.data.mock.CategoriasMock
import com.capgemini.deyvidsilva.lojitas.domain.entity.Produto

object ProdutosEletronicos {

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
        categoria = CategoriasMock.eletronicos,
        preco = preco,
        imagemProduto = android.R.drawable.ic_menu_gallery,
        estoque = estoque
    )

    val lista = listOf(
        produto(
            id = "ELE001",
            nome = "Headset Bluetooth",
            descricao = "Headset sem fio com cancelamento de ruído e bateria de até 30 horas.",
            preco = 349.90,
            estoque = 18
        ),
        produto(
            id = "ELE002",
            nome = "Smartwatch Fit",
            descricao = "Relógio inteligente com monitoramento de frequência cardíaca e oxigenação do sangue.",
            preco = 599.90,
            estoque = 12
        ),
        produto(
            id = "ELE003",
            nome = "Caixa de Som Bluetooth",
            descricao = "Caixa de som portátil com áudio estéreo e resistência à água.",
            preco = 229.90,
            estoque = 20
        ),
        produto(
            id = "ELE004",
            nome = "Carregador Turbo 30W",
            descricao = "Carregador rápido USB-C compatível com diversos smartphones.",
            preco = 89.90,
            estoque = 35
        ),
        produto(
            id = "ELE005",
            nome = "Power Bank 20.000mAh",
            descricao = "Bateria portátil de alta capacidade com duas portas USB e USB-C.",
            preco = 199.90,
            estoque = 14
        ),
        produto(
            id = "ELE006",
            nome = "Fone TWS Pro",
            descricao = "Fones de ouvido totalmente sem fio com estojo de carregamento.",
            preco = 279.90,
            estoque = 25
        ),
        produto(
            id = "ELE007",
            nome = "Assistente Virtual Smart",
            descricao = "Caixa de som inteligente com comandos de voz e integração residencial.",
            preco = 399.90,
            estoque = 9
        ),
        produto(
            id = "ELE008",
            nome = "TV Stick Streaming",
            descricao = "Dispositivo para transformar qualquer TV em uma Smart TV.",
            preco = 249.90,
            estoque = 16
        ),
        produto(
            id = "ELE009",
            nome = "Controle Bluetooth",
            descricao = "Controle sem fio compatível com Android, PC e Smart TV.",
            preco = 179.90,
            estoque = 22
        ),
        produto(
            id = "ELE010",
            nome = "Projetor Portátil",
            descricao = "Projetor compacto Full HD para filmes, apresentações e jogos.",
            preco = 1499.90,
            estoque = 6
        )
    )
}