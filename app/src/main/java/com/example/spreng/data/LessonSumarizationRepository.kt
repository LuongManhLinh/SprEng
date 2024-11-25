package com.example.spreng.data

import com.example.spreng.form.LessonSummarizationForm

interface LessonSummarizationRepository {
    fun getAllLessonSummarization(): List<LessonSummarizationForm>
}

class DemoLessonSummarizationRepository : LessonSummarizationRepository {
    override fun getAllLessonSummarization(): List<LessonSummarizationForm> {
        return listOf(
            LessonSummarizationForm(1, "Environment", "Learn about various environmental issues and how to address them effectively", true, 3),
            LessonSummarizationForm(2, "Vehicle", "Explore different types of vehicles, their uses, and maintenance tips", true, 3),
            LessonSummarizationForm(3, "Travelling", "Get essential tips and advice for travelling across Europe safely and enjoyably", true,3),
            LessonSummarizationForm(4, "Food", "Discover popular dishes from around the world and their cultural significance", true, 3),
            LessonSummarizationForm(5, "Culture", "Understand the diverse cultures around the world and their unique traditions", true, 2),
            LessonSummarizationForm(6, "History", "Learn about important historical events and their impact on the present", true, 2),
            LessonSummarizationForm(7, "Technology", "Stay updated with the latest advancements in technology and their applications", true, 2),
            LessonSummarizationForm(8, "Health", "Maintain a healthy lifestyle with tips on diet, exercise, and mental well-being", true, 1),
            LessonSummarizationForm(9, "Sports", "Learn about different types of sports, their rules, and famous athletes", false),
            LessonSummarizationForm(10, "Music", "Explore various genres of music and their evolution over time", false),
            LessonSummarizationForm(11, "Art", "Discover famous artworks and artists, and their contributions to the art world", false),
            LessonSummarizationForm(12, "Science", "Understand basic scientific concepts and their real-world applications", false),
            LessonSummarizationForm(13, "Literature", "Dive into classic literary works and their significance in literature", false),
            LessonSummarizationForm(14, "Geography", "Learn about the geographical features of the world and their importance", false),
            LessonSummarizationForm(15, "Economy", "Understand economic principles and how they affect global markets", false),
            LessonSummarizationForm(16, "Politics", "Explore different political systems and ideologies around the world", false),
            LessonSummarizationForm(17, "Education", "Learn about various educational systems and their approaches to learning", false),
            LessonSummarizationForm(18, "Fashion", "Stay updated with the latest trends in fashion and their cultural impact", false),
            LessonSummarizationForm(19, "Media", "Understand the role of media in society and its influence on public opinion", false),
            LessonSummarizationForm(20, "Travel", "Plan a trip effectively with tips on budgeting, packing, and itineraries", false)
        )
    }

}