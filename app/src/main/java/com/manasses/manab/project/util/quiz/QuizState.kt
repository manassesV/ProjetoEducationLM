package com.manasses.manab.project.util.quiz

enum class QuizState(var msg: String){
    DATABASEERROR("Error no banco de dados."),
    EXCEPTION("Excessão ao acessar o banco de dados."),
}