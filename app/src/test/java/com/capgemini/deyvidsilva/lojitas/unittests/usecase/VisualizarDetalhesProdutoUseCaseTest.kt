package com.capgemini.deyvidsilva.lojitas.unittests.usecase

import com.capgemini.deyvidsilva.lojitas.domain.usecase.VisualizarDetalhesProdutoUseCase
import com.capgemini.deyvidsilva.lojitas.unittests.fake.FakeLojitasRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull

import org.junit.Before
import org.junit.Test

class VisualizarDetalhesProdutoUseCaseTest {

    private lateinit var fakeRepository: FakeLojitasRepository
    private lateinit var useCase: VisualizarDetalhesProdutoUseCase

    @Before
    fun setUp() {
        fakeRepository = FakeLojitasRepository()
        useCase = VisualizarDetalhesProdutoUseCase(fakeRepository)
    }

    @Test
    fun `deve retornar o produto correto quando o id existir`() {
        val resultado = useCase("2")
        assertNotNull(resultado)
        assertEquals("Mouse", resultado?.nome)
    }

    @Test
    fun `deve retornar null quando o id nao existir`() {
        val resultado = useCase("99")
        assertNull(resultado)
    }
}