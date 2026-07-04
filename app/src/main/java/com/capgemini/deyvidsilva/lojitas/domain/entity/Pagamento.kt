package com.capgemini.deyvidsilva.lojitas.domain.entity

import com.capgemini.deyvidsilva.lojitas.domain.entity.enums.TipoPagamento

data class Pagamento (
    val tipo: TipoPagamento,
    val numeroCartao: String? = null,
    val nomeTitular: String?= null,
    val validade: String? = null,
    val cvv: String? = null
)
