package com.example.spreng.form

abstract class ChallengeForm(
    val title: String,
    val questionContent: String,
    val questionType: QuestionType,
    val answer: String,
    val answerType: AnswerType
) {
}