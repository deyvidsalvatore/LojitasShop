package com.capgemini.deyvidsilva.lojitas.unittests.usecase

import com.capgemini.deyvidsilva.lojitas.domain.usecase.VisualizarProdutosUseCase
import com.capgemini.deyvidsilva.lojitas.unittests.fake.FakeLojitasRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class VisualizarProdutosUseCaseTest {

    private lateinit var fakeRepository: FakeLojitasRepository
    private lateinit var useCase: VisualizarProdutosUseCase

    @Before
    fun setUp() {
        fakeRepository = FakeLojitasRepository()
        useCase = VisualizarProdutosUseCase(fakeRepository)
    }

    @Test
    fun `deve retornar todos os produtos do repositorio`() {
        val resultado = useCase()
        assertEquals(2, resultado.size)
        assertEquals("Teclado", resultado[0].nome)
        assertEquals("Mouse", resultado[1].nome)
    }
}