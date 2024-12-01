package com.example.spreng.data

import com.example.spreng.form.AnswerType
import com.example.spreng.form.ChallengeForm
import com.example.spreng.form.QuestionType

interface LessonRepository {
    fun getLesson(lessonId: Int): List<ChallengeForm>
}

class DemoLessonRepository : LessonRepository {
    override fun getLesson(lessonId: Int): List<ChallengeForm> {
        return listOf(
            ChallengeForm(
                title = "Hoàn thiện câu sau",
                questionContent = "Hôm qua, tớ đi chợ và mua một quả táo rất to",
                questionType = QuestionType.TEXT,
                answer = listOf("yesterday", ", ", "I ", "went ", "to ", "the ", "market",
                    " and ", "bought ", "a ", "very ", "big ", "apple"),
                answerType = AnswerType.WORD_PICKER_FILLING,
                answerOptions = listOf("pear", "about", "absolutely", "supermarket"),
                maskedAnswer = listOf("apple", "market", "yesterday")
            ),
            ChallengeForm(
                title = "Dịch lại câu sau",
                questionContent = "Thủ đô của nước Đức là gì?",
                questionType = QuestionType.TEXT,
                answer = listOf("what", "is", "the", "capital", "of", "Germany"),
                answerType = AnswerType.WORD_PICKER_SEQUENCE,
                answerOptions = listOf("France", "which", "are", "then")
            ),
            ChallengeForm(
                title = "Nghe và điền lại câu",
                questionContent = "Today is a beautiful day",
                questionType = QuestionType.LISTENING,
                answer = listOf("today", "is", "a", "beautiful", "day"),
                answerType = AnswerType.WORD_PICKER_SEQUENCE,
                answerOptions = listOf("yesterday", "owl", "date", "beautify")
            ),
            ChallengeForm(
                title = "Nghe và điền lại câu",
                questionContent = "How are you",
                answer = "How are you",
                questionType = QuestionType.LISTENING,
                answerType = AnswerType.TYPING
            ),
            ChallengeForm(
                title = "Nhắc lại câu sau",
                questionContent = "Nice to meet you",
                questionType = QuestionType.TEXT,
                answer = "nice to meet you",
                answerType = AnswerType.TALKING,
            ),
            ChallengeForm(
                title = "Nghe và điền lại câu",
                questionContent = "You are a good boy",
                answer = "You are a good boy",
                questionType = QuestionType.LISTENING,
                answerType = AnswerType.TYPING
            )
        )
    }
}
