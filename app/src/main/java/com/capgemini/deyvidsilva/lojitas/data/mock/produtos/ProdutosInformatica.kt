package com.capgemini.deyvidsilva.lojitas.data.mock.produtos

import com.capgemini.deyvidsilva.lojitas.data.mock.CategoriasMock
import com.capgemini.deyvidsilva.lojitas.domain.entity.Produto

object ProdutosInformatica {

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
        categoria = CategoriasMock.informatica,
        preco = preco,
        imagemProduto = android.R.drawable.ic_menu_gallery,
        estoque = estoque
    )

    val lista = listOf(
        produto(
            id = "INF001",
            nome = "Monitor Gamer 24\"",
            descricao = "Monitor Full HD de 24 polegadas com taxa de atualização de 165Hz, ideal para jogos e produtividade.",
            preco = 899.90,
            estoque = 12
        ),
        produto(
            id = "INF002",
            nome = "Notebook Ultra",
            descricao = "Notebook com processador Intel Core i7, 16GB de RAM e SSD de 512GB para alto desempenho.",
            preco = 4299.90,
            estoque = 6
        ),
        produto(
            id = "INF003",
            nome = "Mouse Gamer RGB",
            descricao = "Mouse óptico com sensor de alta precisão, iluminação RGB e seis botões programáveis.",
            preco = 149.90,
            estoque = 30
        ),
        produto(
            id = "INF004",
            nome = "Teclado Mecânico",
            descricao = "Teclado mecânico com switches azuis, iluminação RGB e estrutura em alumínio.",
            preco = 299.90,
            estoque = 18
        ),
        produto(
            id = "INF005",
            nome = "SSD NVMe 1TB",
            descricao = "SSD NVMe PCIe de 1TB com alta velocidade para inicialização e carregamento de programas.",
            preco = 549.90,
            estoque = 15
        ),
        produto(
            id = "INF006",
            nome = "HD Externo 2TB",
            descricao = "HD portátil de 2TB para backup de arquivos, fotos e vídeos.",
            preco = 429.90,
            estoque = 10
        ),
        produto(
            id = "INF007",
            nome = "Webcam Full HD",
            descricao = "Webcam com resolução Full HD 1080p e microfone integrado para videochamadas.",
            preco = 199.90,
            estoque = 22
        ),
        produto(
            id = "INF008",
            nome = "Hub USB 3.0",
            descricao = "Hub com quatro portas USB 3.0 para expandir a conectividade do computador.",
            preco = 89.90,
            estoque = 35
        ),
        produto(
            id = "INF009",
            nome = "Pen Drive 128GB",
            descricao = "Pen drive USB 3.2 com capacidade de 128GB para armazenamento de arquivos.",
            preco = 79.90,
            estoque = 40
        ),
        produto(
            id = "INF010",
            nome = "Cadeira Gamer",
            descricao = "Cadeira ergonômica com apoio lombar e ajuste de altura para maior conforto.",
            preco = 1299.90,
            estoque = 7
        )
    )
}