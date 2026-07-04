package com.capgemini.deyvidsilva.lojitas.data.mock

import com.capgemini.deyvidsilva.lojitas.data.mock.produtos.ProdutosCasa
import com.capgemini.deyvidsilva.lojitas.data.mock.produtos.ProdutosEletronicos
import com.capgemini.deyvidsilva.lojitas.data.mock.produtos.ProdutosInformatica
import com.capgemini.deyvidsilva.lojitas.data.mock.produtos.ProdutosMoveis
import com.capgemini.deyvidsilva.lojitas.domain.entity.Produto

object ProdutosMock {

    val listaProdutos = buildList {
        addAll(ProdutosInformatica.lista)
        addAll(ProdutosEletronicos.lista)
        addAll(ProdutosMoveis.lista)
        addAll(ProdutosCasa.lista)
    }

}