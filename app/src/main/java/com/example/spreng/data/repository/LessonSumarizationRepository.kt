package com.example.spreng.data.repository

import android.util.Log
import com.example.spreng.data.form.LessonSummarizationForm

interface LessonSummarizationRepository {
    fun getAllLessonSummarization(numCompleteLesson: Int): List<LessonSummarizationForm>
}

class DemoLessonSummarizationRepository : LessonSummarizationRepository {

    override fun getAllLessonSummarization(numCompleteLesson: Int): List<LessonSummarizationForm> {
        Log.i("ListLesson", numCompleteLesson.toString())
        return listOf(
            LessonSummarizationForm(1, "Environment", "Learn about various environmental issues and how to address them effectively", numCompleteLesson >= 1, 3),
            LessonSummarizationForm(2, "Vehicle", "Explore different types of vehicles, their uses, and maintenance tips", numCompleteLesson >= 2, 2),
            LessonSummarizationForm(3, "Travelling", "Get essential tips and advice for travelling across Europe safely and enjoyably", numCompleteLesson >= 3, 3),
            LessonSummarizationForm(4, "Food", "Discover popular dishes from around the world and their cultural significance", numCompleteLesson >= 4, 1),
            LessonSummarizationForm(5, "Culture", "Understand the diverse cultures around the world and their unique traditions", numCompleteLesson >= 5),
            LessonSummarizationForm(6, "History", "Learn about important historical events and their impact on the present", numCompleteLesson >= 6, 2),
            LessonSummarizationForm(7, "Technology", "Stay updated with the latest advancements in technology and their applications", numCompleteLesson >= 7),
            LessonSummarizationForm(8, "Health", "Maintain a healthy lifestyle with tips on diet, exercise, and mental well-being", numCompleteLesson >= 8),
            LessonSummarizationForm(9, "Sports", "Learn about different types of sports, their rules, and famous athletes", numCompleteLesson >= 9),
            LessonSummarizationForm(10, "Music", "Explore various genres of music and their evolution over time", numCompleteLesson >= 10),
            LessonSummarizationForm(11, "Art", "Discover famous artworks and artists, and their contributions to the art world", numCompleteLesson >= 11),
            LessonSummarizationForm(12, "Science", "Understand basic scientific concepts and their real-world applications", numCompleteLesson >= 12),
            LessonSummarizationForm(13, "Literature", "Dive into classic literary works and their significance in literature", numCompleteLesson >= 13),
            LessonSummarizationForm(14, "Geography", "Learn about the geographical features of the world and their importance", numCompleteLesson >= 14),
            LessonSummarizationForm(15, "Economy", "Understand economic principles and how they affect global markets", numCompleteLesson >= 15),
            LessonSummarizationForm(16, "Politics", "Explore different political systems and ideologies around the world", numCompleteLesson >= 16),
            LessonSummarizationForm(17, "Education", "Learn about various educational systems and their approaches to learning", numCompleteLesson >= 17),
            LessonSummarizationForm(18, "Fashion", "Stay updated with the latest trends in fashion and their cultural impact", numCompleteLesson >= 18),
            LessonSummarizationForm(19, "Media", "Understand the role of media in society and its influence on public opinion", numCompleteLesson >= 19),
            LessonSummarizationForm(20, "Travel", "Plan a trip effectively with tips on budgeting, packing, and itineraries", numCompleteLesson >= 20)
        )
    }
}