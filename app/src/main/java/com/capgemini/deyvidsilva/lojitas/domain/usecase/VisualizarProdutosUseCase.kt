package com.capgemini.deyvidsilva.lojitas.domain.usecase

import com.capgemini.deyvidsilva.lojitas.data.repository.LojitasRepository
import com.capgemini.deyvidsilva.lojitas.domain.entity.Produto

class VisualizarProdutosUseCase(
    private val repository: LojitasRepository
) {
    operator fun invoke(): List<Produto> {
        return repository.obterTodosProdutos();
    }
}