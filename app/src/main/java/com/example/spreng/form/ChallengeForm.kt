package com.example.spreng.form

data class ChallengeForm(
    val title: String,
    val questionContent: String,
    val questionType: QuestionType,
    val answer: Any,
    val answerType: AnswerType,
    val answerOptions: List<String>? = null,
    val maskedAnswer: List<String>? = null
) {

}