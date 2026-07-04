package com.capgemini.deyvidsilva.lojitas.unittests.usecase

import com.capgemini.deyvidsilva.lojitas.domain.usecase.PesquisarProdutoUseCase
import com.capgemini.deyvidsilva.lojitas.unittests.fake.FakeLojitasRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PesquisarProdutoUseCaseTest {
    private lateinit var fakeRepository: FakeLojitasRepository
    private lateinit var useCase: PesquisarProdutoUseCase

    @Before
    fun setUp() {
        fakeRepository = FakeLojitasRepository()
        useCase = PesquisarProdutoUseCase(fakeRepository)
    }

    @Test
    fun `deve repassar a query para o repositorio e retornar resultado correspondente`() {
        val resultado = useCase("teclado")
        assertEquals(1, resultado.size)
        assertEquals("Teclado", resultado.first().nome)
    }

    @Test
    fun `deve retornar lista vazia se nao houver correspondencia`() {
        val resultado = useCase("monitor")
        assertTrue(resultado.isEmpty())
    }

}