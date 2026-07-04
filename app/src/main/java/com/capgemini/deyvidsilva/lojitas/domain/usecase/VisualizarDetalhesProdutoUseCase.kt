package com.capgemini.deyvidsilva.lojitas.domain.usecase

import com.capgemini.deyvidsilva.lojitas.data.repository.LojitasRepository
import com.capgemini.deyvidsilva.lojitas.domain.entity.Produto

class VisualizarDetalhesProdutoUseCase(
    private val repository: LojitasRepository
) {
    operator fun invoke(id: String): Produto? {
        return repository.obterProdutoPorId(id);
    }
}