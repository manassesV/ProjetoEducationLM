package com.manasses.manab.project.data.local.entity

import com.google.gson.annotations.SerializedName


class Atividade(
    var hash: String?,
    var atividade: String?,
    var descricao: String?

)

class Question(
    var pontos: Int?,
    var question: String?,
    var correct_answer: String?,
    var anwser: ArrayList<String>
)