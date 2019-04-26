package com.manasses.manab.project.util.user

enum class AuthState(var msg: String){
    SUCCESS("Sucesso na autentição."),
    FAILED("Falha ao autenticar o usuário."),
    EXCEPTION("Excessão ao autenticar."),
    NOME("Campo nome está vazio"),
    EMAIL("Campo Email está vazio."),
    EMAILINVALID("Email inválido."),
    EMAILEXISTENTE("Email existente, tente com outro email"),
    PASSWORD("Campo Senha está vazio."),
    PASSWORDLENGHT("Campo Senha menor que 8 caracter"),
    PASSWORDCONFIRME("Campo de Confirmar senha diferente"),
    HOPE("Esperando")
}