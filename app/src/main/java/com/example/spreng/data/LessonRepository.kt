package com.example.spreng.data

import com.example.spreng.form.AnswerType
import com.example.spreng.form.ChallengeForm
import com.example.spreng.form.QuestionType

interface LessonRepository {
    fun getLesson(lessonId: Int): List<ChallengeForm>
}

//bài 1
val environmentalChallenges = listOf(
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Water is essential for life",
        questionType = QuestionType.LISTENING,
        answer = "Water is essential for life",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Ô nhiễm không khí là một vấn đề lớn ở các thành phố lớn",
        questionType = QuestionType.TEXT,
        answer = listOf("air", "pollution", "is", "a", "major", "issue", "in", "large", "cities"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("big", "problem", "cause", "cars")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Recycling helps protect the environment",
        questionType = QuestionType.LISTENING,
        answer = listOf("recycling", "helps", "protect", "the", "environment"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Rừng bị tàn phá gây ra sự mất mát nghiêm trọng của đa dạng sinh học",
        questionType = QuestionType.TEXT,
        answer = listOf("forests", " being", " destroyed", " leads", " to", " significant", " loss", " of", " biodiversity"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("pollution", "animals", "climate", "destruction"),
        maskedAnswer = listOf("forests", " biodiversity")
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Everyone can help fight climate change",
        questionType = QuestionType.TEXT,
        answer = "everyone can help fight climate change",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Để bảo vệ hành tinh, chúng ta cần giảm lượng rác thải ra môi trường",
        questionType = QuestionType.TEXT,
        answer = listOf("to", " protect", " our", " planet,", " we", " must", " reduce", " waste", " production"
        ),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("energy", "production", "waste", "water"),
        maskedAnswer = listOf(" waste", " production")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Chúng ta nên sử dụng năng lượng tái tạo nhiều hơn",
        questionType = QuestionType.TEXT,
        answer = listOf("we", "should", "use", "renewable", "energy", "more"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("fossil", "fuels", "pollution", "sources")
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "We need to plant more trees",
        questionType = QuestionType.TEXT,
        answer = "we need to plant more trees",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Global warming affects everyone",
        questionType = QuestionType.LISTENING,
        answer = "Global warming affects everyone",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Reducing plastic waste is important",
        questionType = QuestionType.LISTENING,
        answer = listOf("reducing", "plastic", "waste", "is", "important"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    )
)

// Bai 2
val vehicleChallenges = listOf(
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Carpooling helps reduce traffic",
        questionType = QuestionType.TEXT,
        answer = "carpooling helps reduce traffic",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Driving responsibly can save lives",
        questionType = QuestionType.TEXT,
        answer = "driving responsibly can save lives",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Motorcycles are popular in many Asian countries",
        questionType = QuestionType.LISTENING,
        answer = listOf("motorcycles", "are", "popular", "in", "many", "Asian", "countries"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Trains are a fast and efficient form of transport",
        questionType = QuestionType.LISTENING,
        answer = listOf("trains", "are", "a", "fast", "and", "efficient", "form", "of", "transport"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Public transportation saves energy",
        questionType = QuestionType.LISTENING,
        answer = "Public transportation saves energy",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Electric cars help reduce air pollution",
        questionType = QuestionType.LISTENING,
        answer = "Electric cars help reduce air pollution",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Xe điện đang trở thành xu hướng trong ngành công nghiệp ô tô.",
        questionType = QuestionType.TEXT,
        answer = listOf("electric", " vehicles", " are", " becoming", " a", " trend", " in", " the", " automotive", " industry"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("gasoline", "engines", "technology", "motorcycles"),
        maskedAnswer = listOf("electric", " automotive")
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Xe bus công cộng là một cách tuyệt vời để giảm ùn tắc giao thông.",
        questionType = QuestionType.TEXT,
        answer = listOf("public", " buses", " are", " a", " great", " way", " to", " reduce", " traffic", " congestion"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("cars", "motorbikes", "trucks", "congestion"),
        maskedAnswer = listOf("public", " congestion")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Ô tô tự lái có thể giảm thiểu tai nạn giao thông",
        questionType = QuestionType.TEXT,
        answer = listOf("self-driving", "cars", "can", "reduce", "traffic", "accidents"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("bikes", "increase", "safety", "crashes")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Xe đạp là phương tiện thân thiện với môi trường",
        questionType = QuestionType.TEXT,
        answer = listOf("bicycles", "are", "an", "environmentally", "friendly", "means", "of", "transportation"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("cars", "wasteful", "eco", "fuel")
    )
)


//bài 3
val travellingChallenges = listOf(
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Du lịch giúp bạn mở rộng tầm nhìn và trải nghiệm những nền văn hóa mới",
        questionType = QuestionType.TEXT,
        answer = listOf("traveling", " broadens", " your", " horizons", " and", " lets", " you", " experience", " new", " cultures"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("explore", "limits", "local", "adventures"),
        maskedAnswer = listOf("traveling", " horizons")
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Kế hoạch trước khi đi du lịch giúp bạn tiết kiệm thời gian và tiền bạc",
        questionType = QuestionType.TEXT,
        answer = listOf("planning", " before", " a", " trip", " saves", " time", " and", " money"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("budget", "adventure", "spending", "money"),
        maskedAnswer = listOf("planning", " time")
    ),

    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Du lịch bằng máy bay là cách nhanh nhất để di chuyển giữa các quốc gia",
        questionType = QuestionType.TEXT,
        answer = listOf("traveling", "by", "plane", "is", "the", "fastest", "way", "to", "move", "between", "countries"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("train", "slower", "destinations", "journey")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Ba lô du lịch rất tiện lợi cho những chuyến đi dài ngày",
        questionType = QuestionType.TEXT,
        answer = listOf("backpacks", "are", "very", "convenient", "for", "long", "trips"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("short", "carry", "bags", "expensive")
    ),

    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Booking hotels early is always a good idea",
        questionType = QuestionType.LISTENING,
        answer = listOf("booking", "hotels", "early", "is", "always", "a", "good", "idea"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Exploring local food is part of the travel experience",
        questionType = QuestionType.LISTENING,
        answer = listOf("exploring", "local", "food", "is", "part", "of", "the", "travel", "experience"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Adventure trips are exciting and challenging",
        questionType = QuestionType.LISTENING,
        answer = "Adventure trips are exciting and challenging",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Travel insurance is important for international trips",
        questionType = QuestionType.LISTENING,
        answer = "Travel insurance is important for international trips",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Traveling with friends makes the journey more fun",
        questionType = QuestionType.TEXT,
        answer = "traveling with friends makes the journey more fun",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Taking photos helps you remember your trips better",
        questionType = QuestionType.TEXT,
        answer = "taking photos helps you remember your trips better",
        answerType = AnswerType.TALKING
    )
)

//bai 4
val foodChallenges = listOf(
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Ăn uống lành mạnh giúp bạn duy trì một cơ thể khỏe mạnh",
        questionType = QuestionType.TEXT,
        answer = listOf("eating", " healthy", " helps", " you", " maintain", " a", " strong", " body"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("junk", "food", "exercise", "body"),
        maskedAnswer = listOf("eating", " healthy")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Rau củ là nguồn vitamin và chất xơ dồi dào",
        questionType = QuestionType.TEXT,
        answer = listOf("vegetables", "are", "a", "great", "source", "of", "vitamins", "and", "fiber"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("protein", "meat", "fruits", "sugar")
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Bữa sáng là bữa ăn quan trọng nhất trong ngày",
        questionType = QuestionType.TEXT,
        answer = listOf("breakfast", " is", " the", " most", " important", " meal", " of", " the", " day"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("lunch", "snack", "healthy", "important"),
        maskedAnswer = listOf("breakfast", " important")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Ăn quá nhiều đồ ăn nhanh không tốt cho sức khỏe",
        questionType = QuestionType.TEXT,
        answer = listOf("eating", "too", "much", "fast", "food", "is", "unhealthy"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("good", "snacks", "balanced", "salads")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Cooking at home is healthier than eating out",
        questionType = QuestionType.LISTENING,
        answer = listOf("cooking", "at", "home", "is", "healthier", "than", "eating", "out"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Drinking water is important for digestion",
        questionType = QuestionType.LISTENING,
        answer = "Drinking water is important for digestion",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Fruits and vegetables are essential for a healthy diet",
        questionType = QuestionType.LISTENING,
        answer = listOf("fruits", "and", "vegetables", "are", "essential", "for", "a", "healthy", "diet"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Sugar consumption should be limited",
        questionType = QuestionType.LISTENING,
        answer = "Sugar consumption should be limited",
        answerType = AnswerType.TYPING
    ),

    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Balanced meals include protein, carbs, and fat",
        questionType = QuestionType.TEXT,
        answer = "balanced meals include protein, carbs, and fat",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Eating fruits daily keeps you healthy",
        questionType = QuestionType.TEXT,
        answer = "eating fruits daily keeps you healthy",
        answerType = AnswerType.TALKING
    )
)

//bai 5
val cultureChallenges = listOf(
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Tết Nguyên Đán là dịp lễ quan trọng nhất ở Việt Nam",
        questionType = QuestionType.TEXT,
        answer = listOf("lunar", " new", " year", " is", " the", " most", " important", " holiday", " in", " Vietnam"
        ),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("Christmas", "Vietnam", "festival", "year"),
        maskedAnswer = listOf("lunar", " year")
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Music brings people together across cultures",
        questionType = QuestionType.LISTENING,
        answer = "Music brings people together across cultures",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Ẩm thực là một phần quan trọng của văn hóa mỗi quốc gia",
        questionType = QuestionType.TEXT,
        answer = listOf("cuisine", "is", "a", "significant", "part", "of", "every", "country's", "culture"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("food", "travel", "music", "tradition")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Mỗi quốc gia có những lễ hội độc đáo riêng",
        questionType = QuestionType.TEXT,
        answer = listOf("every", "country", "has", "its", "own", "unique", "festivals"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("traditions", "common", "celebrations", "own")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Traditional costumes preserve cultural identity",
        questionType = QuestionType.LISTENING,
        answer = listOf("traditional", "costumes", "preserve", "cultural", "identity"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Những điệu múa truyền thống là một phần không thể thiếu của văn hóa dân gian",
        questionType = QuestionType.TEXT,
        answer = listOf("traditional", " dances", " are", " an", " essential", " part", " of", " folklore"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("modern", "music", "heritage", "art"),
        maskedAnswer = listOf("traditional", " folklore")
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Language is the heart of every culture",
        questionType = QuestionType.LISTENING,
        answer = "Language is the heart of every culture",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Sharing cultural traditions promotes understanding",
        questionType = QuestionType.TEXT,
        answer = "sharing cultural traditions promotes understanding",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Art reflects the values of a culture",
        questionType = QuestionType.LISTENING,
        answer = listOf("art", "reflects", "the", "values", "of", "a", "culture"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Festivals are a way to celebrate heritage",
        questionType = QuestionType.TEXT,
        answer = "festivals are a way to celebrate heritage",
        answerType = AnswerType.TALKING
    )
)


// bài 6
val historyChallenges = listOf(
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "The invention of the printing press revolutionized knowledge",
        questionType = QuestionType.TEXT,
        answer = "the invention of the printing press revolutionized knowledge",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Chiến tranh thế giới thứ hai bắt đầu vào năm 1939",
        questionType = QuestionType.TEXT,
        answer = listOf("world", " war", " ii", " began", " in", " 1939"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("1950", "battle", "history", "1939"),
        maskedAnswer = listOf("world", " 1939")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Cách mạng công nghiệp đã thay đổi thế giới",
        questionType = QuestionType.TEXT,
        answer = listOf("the", "industrial", "revolution", "changed", "the", "world"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("war", "revolution", "changed", "industry")
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Ai Cập cổ đại nổi tiếng với các kim tự tháp",
        questionType = QuestionType.TEXT,
        answer = listOf("ancient", " egypt", " is", " famous", " for", " its", " pyramids"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("temples", "ancient", "modern", "culture"),
        maskedAnswer = listOf("ancient", " pyramids")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "La Mã cổ đại là nền văn minh lớn ở châu Âu",
        questionType = QuestionType.TEXT,
        answer = listOf("ancient", "Rome", "was", "a", "major", "civilization", "in", "Europe"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("history", "Rome", "Europe", "civilization")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "The Renaissance was a cultural movement in Europe",
        questionType = QuestionType.LISTENING,
        answer = listOf("the", "renaissance", "was", "a", "cultural", "movement", "in", "europe"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "The French Revolution began in 1789",
        questionType = QuestionType.LISTENING,
        answer = "The French Revolution began in 1789",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "The discovery of America changed history forever",
        questionType = QuestionType.LISTENING,
        answer = "The discovery of America changed history forever",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "World War I ended in 1918",
        questionType = QuestionType.TEXT,
        answer = "world war i ended in 1918",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "The Great Wall of China was built to protect the empire",
        questionType = QuestionType.LISTENING,
        answer = listOf("the", "great", "wall", "of", "china", "was", "built", "to", "protect", "the", "empire"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    )
)

//bai 7
val technologyChallenges = listOf(
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Virtual reality creates immersive experiences",
        questionType = QuestionType.TEXT,
        answer = "virtual reality creates immersive experiences",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Internet giúp kết nối mọi người trên toàn cầu",
        questionType = QuestionType.TEXT,
        answer = listOf("the", " internet", " connects", " people", " worldwide"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("connects", "global", "technology", "computers"),
        maskedAnswer = listOf("internet", " worldwide")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Điện thoại thông minh đã trở thành một phần của cuộc sống hàng ngày",
        questionType = QuestionType.TEXT,
        answer = listOf("smartphones", "have", "become", "a", "part", "of", "daily", "life"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("phones", "technology", "life", "smartphones")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Công nghệ 5G đang cải thiện tốc độ kết nối internet",
        questionType = QuestionType.TEXT,
        answer = listOf("5G", "technology", "is", "improving", "internet", "speed"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("speed", "5G", "connection", "improving")
    ),

    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Trí tuệ nhân tạo đang thay đổi cách con người làm việc",
        questionType = QuestionType.TEXT,
        answer = listOf("artificial", " intelligence", " is", " changing", " how", " people", " work"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("robots", "people", "intelligence", "learning"),
        maskedAnswer = listOf("artificial", " intelligence")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Technology makes learning more accessible",
        questionType = QuestionType.LISTENING,
        answer = listOf("technology", "makes", "learning", "more", "accessible"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Social media has transformed communication",
        questionType = QuestionType.LISTENING,
        answer = "Social media has transformed communication",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Cloud computing allows data storage online",
        questionType = QuestionType.LISTENING,
        answer = "Cloud computing allows data storage online",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Robots can perform tasks faster than humans",
        questionType = QuestionType.LISTENING,
        answer = listOf("robots", "can", "perform", "tasks", "faster", "than", "humans"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Self-driving cars could reduce traffic accidents",
        questionType = QuestionType.TEXT,
        answer = "self-driving cars could reduce traffic accidents",
        answerType = AnswerType.TALKING
    )
)

//bai 8
val healthChallenges = listOf(
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Washing hands prevents the spread of germs",
        questionType = QuestionType.TEXT,
        answer = "washing hands prevents the spread of germs",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Chế độ ăn uống lành mạnh giảm nguy cơ bệnh tật",
        questionType = QuestionType.TEXT,
        answer = listOf("healthy", " eating", " reduces", " disease", " risks"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("risks", "eating", "increases", "reduces"),
        maskedAnswer = listOf("healthy", " reduces")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Ngủ đủ giấc giúp cơ thể hồi phục tốt hơn",
        questionType = QuestionType.TEXT,
        answer = listOf("getting", "enough", "sleep", "helps", "the", "body", "recover", "better"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("better", "recover", "sleep", "enough")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Uống nước đủ lượng cần thiết cho sức khỏe",
        questionType = QuestionType.TEXT,
        answer = listOf("drinking", "enough", "water", "is", "essential", "for", "health"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("essential", "drinking", "water", "health")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Mental health is as important as physical health",
        questionType = QuestionType.LISTENING,
        answer = listOf("mental", "health", "is", "as", "important", "as", "physical", "health"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Tập thể dục thường xuyên giúp tăng cường sức khỏe",
        questionType = QuestionType.TEXT,
        answer = listOf("regular", " exercise", " improves", " health"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("health", "routine", "diet", "improves"),
        maskedAnswer = listOf()
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Vaccinations help prevent serious diseases",
        questionType = QuestionType.LISTENING,
        answer = listOf("vaccinations", "help", "prevent", "serious", "diseases"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "A balanced diet is crucial for good health",
        questionType = QuestionType.LISTENING,
        answer = "A balanced diet is crucial for good health",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Meditation helps reduce stress and anxiety",
        questionType = QuestionType.TEXT,
        answer = "meditation helps reduce stress and anxiety",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Stress management can improve quality of life",
        questionType = QuestionType.LISTENING,
        answer = "Stress management can improve quality of life",
        answerType = AnswerType.TYPING
    )
)

//bai 9
val sportsChallenges = listOf(
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Bóng đá là môn thể thao phổ biến nhất trên thế giới",
        questionType = QuestionType.TEXT,
        answer = listOf("soccer", " is", " the", " most", " popular", " sport", " in", " the", " world"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("sport", "game", "soccer", "popular"),
        maskedAnswer = listOf("soccer", " sport")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Bơi lội là một cách tuyệt vời để rèn luyện cơ thể",
        questionType = QuestionType.TEXT,
        answer = listOf("swimming", "is", "a", "great", "way", "to", "exercise", "your", "body"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("exercise", "great", "swimming", "body")
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Chạy bộ cải thiện sức khỏe tim mạch",
        questionType = QuestionType.TEXT,
        answer = listOf("running", " improves", " cardiovascular", " health"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("cardiovascular", "running", "exercise", "health"),
        maskedAnswer = listOf("running", " health")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Cầu lông là một môn thể thao nhanh và thú vị",
        questionType = QuestionType.TEXT,
        answer = listOf("badminton", "is", "a", "fast", "and", "exciting", "sport"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("sport", "exciting", "fast", "badminton")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Basketball requires both skill and teamwork",
        questionType = QuestionType.LISTENING,
        answer = listOf("basketball", "requires", "both", "skill", "and", "teamwork"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Yoga improves flexibility and reduces stress",
        questionType = QuestionType.TEXT,
        answer = "yoga improves flexibility and reduces stress",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Cycling is an excellent way to stay fit",
        questionType = QuestionType.LISTENING,
        answer = "Cycling is an excellent way to stay fit",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Tennis is a popular individual and doubles sport",
        questionType = QuestionType.LISTENING,
        answer = listOf("tennis", "is", "a", "popular", "individual", "and", "doubles", "sport"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Cricket is a very popular sport in many countries",
        questionType = QuestionType.LISTENING,
        answer = "Cricket is a very popular sport in many countries",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Martial arts teach discipline and self-defense",
        questionType = QuestionType.TEXT,
        answer = "martial arts teach discipline and self-defense",
        answerType = AnswerType.TALKING
    )
)

//bai 10
val musicChallenges = listOf(
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Âm nhạc cổ điển có lịch sử lâu đời",
        questionType = QuestionType.TEXT,
        answer = listOf("classical", " music", " has", " a", " long", " history"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("classical", "history", "modern", "music"),
        maskedAnswer = listOf("classical", " history")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Pop music là thể loại phổ biến nhất hiện nay",
        questionType = QuestionType.TEXT,
        answer = listOf("pop", "music", "is", "the", "most", "popular", "genre", "nowadays"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("popular", "pop", "music", "genre")
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Chơi nhạc cụ giúp rèn luyện trí nhớ",
        questionType = QuestionType.TEXT,
        answer = listOf("playing", " instruments", " improves", " memory"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("instruments", "memory", "music", "playing"),
        maskedAnswer = listOf("playing", " memory")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Jazz nổi tiếng với tính ngẫu hứng của nó",
        questionType = QuestionType.TEXT,
        answer = listOf("jazz", "is", "famous", "for", "its", "improvisation"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("improvisation", "famous", "jazz", "music")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Rock music is known for its strong beats",
        questionType = QuestionType.LISTENING,
        answer = listOf("rock", "music", "is", "known", "for", "its", "strong", "beats"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Electronic music is created using technology",
        questionType = QuestionType.LISTENING,
        answer = "Electronic music is created using technology",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Music therapy helps people manage stress",
        questionType = QuestionType.LISTENING,
        answer = "Music therapy helps people manage stress",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Singing is a way to express emotions",
        questionType = QuestionType.TEXT,
        answer = "singing is a way to express emotions",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Folk music reflects the culture of a region",
        questionType = QuestionType.LISTENING,
        answer = listOf("folk", "music", "reflects", "the", "culture", "of", "a", "region"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Composing music requires creativity and skill",
        questionType = QuestionType.TEXT,
        answer = "composing music requires creativity and skill",
        answerType = AnswerType.TALKING
    )

)

//bai 11
val artChallenges = listOf(
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Hội họa là một hình thức biểu đạt sáng tạo",
        questionType = QuestionType.TEXT,
        answer = listOf("painting", " is", " a", " form", " of", " creative", " expression"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("creative", "painting", "art", "expression"),
        maskedAnswer = listOf("painting", " creative")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Street art often conveys social messages",
        questionType = QuestionType.LISTENING,
        answer = listOf("street", "art", "often", "conveys", "social", "messages"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Nhiếp ảnh là nghệ thuật kể chuyện bằng hình ảnh",
        questionType = QuestionType.TEXT,
        answer = listOf("photography", "is", "the", "art", "of", "storytelling", "through", "images"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("photography", "art", "images", "storytelling")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Thư pháp kết hợp sự sáng tạo và sự chính xác",
        questionType = QuestionType.TEXT,
        answer = listOf("calligraphy", "combines", "creativity", "and", "precision"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("precision", "calligraphy", "creativity", "art")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Abstract art focuses on shapes and colors",
        questionType = QuestionType.LISTENING,
        answer = listOf("abstract", "art", "focuses", "on", "shapes", "and", "colors"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),

    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Điêu khắc đòi hỏi kỹ năng và sự kiên nhẫn",
        questionType = QuestionType.TEXT,
        answer = listOf("sculpting", " requires", " skill", " and", " patience"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("patience", "sculpting", "drawing", "requires"),
        maskedAnswer = listOf("sculpting", " patience")
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Digital art is created using modern tools",
        questionType = QuestionType.LISTENING,
        answer = "Digital art is created using modern tools",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Sculptures often depict historical figures",
        questionType = QuestionType.TEXT,
        answer = "sculptures often depict historical figures",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Sketching is a basic step in many art forms",
        questionType = QuestionType.LISTENING,
        answer = "Sketching is a basic step in many art forms",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Art installations can transform public spaces",
        questionType = QuestionType.TEXT,
        answer = "art installations can transform public spaces",
        answerType = AnswerType.TALKING
    )
)

//bai 12
val scienceChallenges = listOf(
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Physics reveals the secrets of the universe",
        questionType = QuestionType.LISTENING,
        answer = "Physics reveals the secrets of the universe",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Hóa học nghiên cứu các nguyên tố và hợp chất",
        questionType = QuestionType.TEXT,
        answer = listOf("chemistry", " studies", " elements", " and", " compounds"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("biology", "chemistry", "compounds", "elements"),
        maskedAnswer = listOf("chemistry", " elements")
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Thiên văn học giúp chúng ta hiểu về vũ trụ",
        questionType = QuestionType.TEXT,
        answer = listOf("astronomy", " helps", " us", " understand", " the", " universe"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("astronomy", "physics", "universe", "stars"),
        maskedAnswer = listOf("astronomy", " universe")
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Physics reveals the secrets of the universe",
        questionType = QuestionType.LISTENING,
        answer = "Physics reveals the secrets of the universe",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Vật lý học khám phá các định luật tự nhiên",
        questionType = QuestionType.TEXT,
        answer = listOf("physics", "explores", "the", "laws", "of", "nature"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("laws", "physics", "nature", "explores")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Genetics studies inheritance and variation",
        questionType = QuestionType.LISTENING,
        answer = listOf("genetics", "studies", "inheritance", "and", "variation"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "The scientific method involves experiments",
        questionType = QuestionType.LISTENING,
        answer = listOf("the", "scientific", "method", "involves", "experiments"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Sinh học nghiên cứu các sinh vật sống",
        questionType = QuestionType.TEXT,
        answer = listOf("biology", "studies", "living", "organisms"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("living", "organisms", "biology", "studies")
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Evolution explains how species change over time",
        questionType = QuestionType.LISTENING,
        answer = "Evolution explains how species change over time",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Scientists conduct research to expand knowledge",
        questionType = QuestionType.TEXT,
        answer = "scientists conduct research to expand knowledge",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "The study of the stars is called astronomy",
        questionType = QuestionType.TEXT,
        answer = "the study of the stars is called astronomy",
        answerType = AnswerType.TALKING
    )
)

//bai 13
val literatureChallenges = listOf(
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Văn học phản ánh hiện thực xã hội",
        questionType = QuestionType.TEXT,
        answer = listOf("literature", " reflects", " social", " realities"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("literature", "realities", "history", "social"),
        maskedAnswer = listOf("literature", " realities")
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "A novel is a long work of fiction",
        questionType = QuestionType.LISTENING,
        answer = "A novel is a long work of fiction",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Tiểu thuyết thường khám phá nội tâm nhân vật",
        questionType = QuestionType.TEXT,
        answer = listOf("novels", " often", " explore", " characters'", " inner", " lives"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("poetry", "novels", "inner", "explore"),
        maskedAnswer = listOf("novels", " inner")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Thơ ca sử dụng ngôn từ để truyền tải cảm xúc",
        questionType = QuestionType.TEXT,
        answer = listOf("poetry", "uses", "language", "to", "convey", "emotions"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("poetry", "language", "emotions", "convey")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Truyện cổ tích mang lại bài học đạo đức",
        questionType = QuestionType.TEXT,
        answer = listOf("fairy", "tales", "provide", "moral", "lessons"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("lessons", "fairy", "provide", "moral")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Epic poetry tells grand stories of heroes",
        questionType = QuestionType.LISTENING,
        answer = listOf("epic", "poetry", "tells", "grand", "stories", "of", "heroes"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Poetry often uses metaphors and symbols",
        questionType = QuestionType.TEXT,
        answer = "poetry often uses metaphors and symbols",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Literature connects us to different cultures",
        questionType = QuestionType.LISTENING,
        answer = "Literature connects us to different cultures",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Classic literature is still studied today",
        questionType = QuestionType.TEXT,
        answer = "classic literature is still studied today",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Shakespeare wrote famous plays and sonnets",
        questionType = QuestionType.LISTENING,
        answer = listOf("shakespeare", "wrote", "famous", "plays", "and", "sonnets"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    )
)

//bai 14
val geographyChallenges = listOf(
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Dãy Himalaya có đỉnh Everest cao nhất thế giới",
        questionType = QuestionType.TEXT,
        answer = listOf("the", " himalayas", " contain", " mount", " everest,", " the", " world's", " highest", " peak"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("himalayas", "peak", "highest", "mount"),
        maskedAnswer = listOf(" himalayas", " everest")
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "The Great Barrier Reef is located in Australia",
        questionType = QuestionType.LISTENING,
        answer = "The Great Barrier Reef is located in Australia",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Châu Phi có rất nhiều sa mạc lớn",
        questionType = QuestionType.TEXT,
        answer = listOf("africa", "has", "many", "large", "deserts"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("africa", "deserts", "large", "many")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Châu Âu có nhiều quốc gia với văn hóa đa dạng",
        questionType = QuestionType.TEXT,
        answer = listOf("europe", "has", "many", "countries", "with", "diverse", "cultures"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("cultures", "europe", "countries", "diverse")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "The Sahara Desert is the largest desert on Earth",
        questionType = QuestionType.LISTENING,
        answer = listOf("the", "sahara", "desert", "is", "the", "largest", "desert", "on", "earth"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Rivers shape the land over time",
        questionType = QuestionType.TEXT,
        answer = "rivers shape the land over time",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Oceans cover most of the Earth's surface",
        questionType = QuestionType.LISTENING,
        answer = listOf("oceans", "cover", "most", "of", "the", "earth's", "surface"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Mountains often create unique ecosystems",
        questionType = QuestionType.LISTENING,
        answer = "Mountains often create unique ecosystems",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Sông Amazon là con sông dài nhất ở Nam Mỹ",
        questionType = QuestionType.TEXT,
        answer = listOf("the", " amazon", " river", " is", " the", " longest", " river", " in", " south", " america"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("africa", "america", "amazon", "river"),
        maskedAnswer = listOf(" amazon", " america")
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Continents drift due to tectonic plate movement",
        questionType = QuestionType.TEXT,
        answer = "continents drift due to tectonic plate movement",
        answerType = AnswerType.TALKING
    )
)

//bai 15
val economyChallenges = listOf(
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Cung và cầu ảnh hưởng đến giá cả hàng hóa",
        questionType = QuestionType.TEXT,
        answer = listOf("supply", "and", "demand", "affect", "the", "price", "of", "goods"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("goods", "supply", "demand", "price")
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Nền kinh tế toàn cầu phụ thuộc vào giao thương quốc tế",
        questionType = QuestionType.TEXT,
        answer = listOf("the", " global", " economy", " relies", " on", " international", " trade"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("economy", "trade", "local", "global"),
        maskedAnswer = listOf(" global", " trade")
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "A strong economy benefits everyone",
        questionType = QuestionType.LISTENING,
        answer = "A strong economy benefits everyone",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Lạm phát làm giảm sức mua của tiền tệ",
        questionType = QuestionType.TEXT,
        answer = listOf("inflation", " reduces", " the", " purchasing", " power", " of", " money"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("money", "inflation", "reduces", "purchasing"),
        maskedAnswer = listOf("inflation", " money")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Thất nghiệp là một thách thức lớn đối với bất kỳ nền kinh tế nào",
        questionType = QuestionType.TEXT,
        answer = listOf("unemployment", "is", "a", "major", "challenge", "for", "any", "economy"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("challenge", "economy", "major", "unemployment")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Investments drive economic growth",
        questionType = QuestionType.LISTENING,
        answer = listOf("investments", "drive", "economic", "growth"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Savings help individuals prepare for emergencies",
        questionType = QuestionType.TEXT,
        answer = "savings help individuals prepare for emergencies",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Taxes fund public services",
        questionType = QuestionType.LISTENING,
        answer = listOf("taxes", "fund", "public", "services"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Trade agreements encourage economic cooperation",
        questionType = QuestionType.LISTENING,
        answer = "Trade agreements encourage economic cooperation",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Globalization connects economies worldwide",
        questionType = QuestionType.TEXT,
        answer = "globalization connects economies worldwide",
        answerType = AnswerType.TALKING
    )
)

//bai 16
val politicsChallenges = listOf(
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Dân chủ cho phép mọi người có quyền bầu cử",
        questionType = QuestionType.TEXT,
        answer = listOf("democracy", " allows", " people", " to", " vote"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("democracy", "vote", "allows", "rights"),
        maskedAnswer = listOf("democracy", " vote")
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Freedom of speech is essential in a democracy",
        questionType = QuestionType.TEXT,
        answer = "freedom of speech is essential in a democracy",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Hiến pháp là nền tảng của luật pháp quốc gia",
        questionType = QuestionType.TEXT,
        answer = listOf("the", " constitution", " is", " the", " foundation", " of", " national", " law"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("constitution", "foundation", "law", "national"),
        maskedAnswer = listOf(" constitution", " national")
    ),


    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Chính phủ quản lý tài nguyên quốc gia",
        questionType = QuestionType.TEXT,
        answer = listOf("the", "government", "manages", "national", "resources"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("government", "national", "resources", "manages")
    ),

    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Transparency builds trust in government",
        questionType = QuestionType.TEXT,
        answer = "transparency builds trust in government",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Elections determine political leaders",
        questionType = QuestionType.LISTENING,
        answer = listOf("elections", "determine", "political", "leaders"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Các chính sách tốt giúp cải thiện xã hội",
        questionType = QuestionType.TEXT,
        answer = listOf("good", "policies", "help", "improve", "society"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("improve", "society", "good", "policies")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Debates are a key part of politics",
        questionType = QuestionType.LISTENING,
        answer = listOf("debates", "are", "a", "key", "part", "of", "politics"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Laws protect citizens and ensure order",
        questionType = QuestionType.LISTENING,
        answer = "Laws protect citizens and ensure order",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Leaders are elected to represent the people",
        questionType = QuestionType.LISTENING,
        answer = "Leaders are elected to represent the people",
        answerType = AnswerType.TYPING
    ),
)

//bai 17
val educationChallenges = listOf(
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Giáo dục là chìa khóa để mở ra tương lai",
        questionType = QuestionType.TEXT,
        answer = listOf("education", " is", " the", " key", " to", " unlocking", " the", " future"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("education", "key", "future", "unlocking"),
        maskedAnswer = listOf("education", " future")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Education promotes equality in society",
        questionType = QuestionType.LISTENING,
        answer = listOf("education", "promotes", "equality", "in", "society"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Học tập suốt đời giúp cải thiện kỹ năng và kiến thức",
        questionType = QuestionType.TEXT,
        answer = listOf("lifelong", " learning", " improves", " skills", " and", " knowledge"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("learning", "skills", "knowledge", "lifelong"),
        maskedAnswer = listOf("lifelong", " knowledge")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Giáo viên đóng vai trò quan trọng trong giáo dục",
        questionType = QuestionType.TEXT,
        answer = listOf("teachers", "play", "a", "vital", "role", "in", "education"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("education", "teachers", "role", "play")
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Learning opens doors to new opportunities",
        questionType = QuestionType.LISTENING,
        answer = "Learning opens doors to new opportunities",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Học sinh cần rèn luyện thói quen học tập tốt",
        questionType = QuestionType.TEXT,
        answer = listOf("students", "need", "to", "develop", "good", "study", "habits"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("study", "habits", "students", "good")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Access to education is a basic right",
        questionType = QuestionType.LISTENING,
        answer = listOf("access", "to", "education", "is", "a", "basic", "right"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Students should be encouraged to ask questions",
        questionType = QuestionType.TEXT,
        answer = "students should be encouraged to ask questions",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Education empowers individuals to succeed",
        questionType = QuestionType.LISTENING,
        answer = "Education empowers individuals to succeed",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Learning is a lifelong process",
        questionType = QuestionType.TEXT,
        answer = "learning is a lifelong process",
        answerType = AnswerType.TALKING
    )
)

//bai 18
val fashionChallenges = listOf(
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Fast fashion has environmental consequences",
        questionType = QuestionType.LISTENING,
        answer = "Fast fashion has environmental consequences",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Thời trang phản ánh cá tính và phong cách của mỗi người",
        questionType = QuestionType.TEXT,
        answer = listOf("fashion", " reflects", " individuality", " and", " style"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("style", "fashion", "reflects", "individuality"),
        maskedAnswer = listOf("fashion", " style")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Thời trang có ảnh hưởng lớn đến văn hóa đại chúng",
        questionType = QuestionType.TEXT,
        answer = listOf("fashion", "has", "a", "huge", "impact", "on", "pop", "culture"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("fashion", "impact", "pop", "culture")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Sustainability is important in modern fashion",
        questionType = QuestionType.LISTENING,
        answer = listOf("sustainability", "is", "important", "in", "modern", "fashion"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Xu hướng thời trang thay đổi theo từng mùa",
        questionType = QuestionType.TEXT,
        answer = listOf("fashion", " trends", " change", " with", " the", " seasons"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("trends", "fashion", "change", "seasons"),
        maskedAnswer = listOf("fashion", " seasons")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Luxury brands set global fashion standards",
        questionType = QuestionType.LISTENING,
        answer = listOf("luxury", "brands", "set", "global", "fashion", "standards"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Accessories complete an outfit",
        questionType = QuestionType.LISTENING,
        answer = "Accessories complete an outfit",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Vintage clothing is becoming more popular",
        questionType = QuestionType.TEXT,
        answer = "vintage clothing is becoming more popular",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Các nhà thiết kế sáng tạo ra các xu hướng mới",
        questionType = QuestionType.TEXT,
        answer = listOf("designers", "create", "new", "fashion", "trends"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("new", "designers", "create", "trends")
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Fashion shows inspire creativity in design",
        questionType = QuestionType.TEXT,
        answer = "fashion shows inspire creativity in design",
        answerType = AnswerType.TALKING
    )
)


//bai 19
val mediaChallenges = listOf(
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Mạng xã hội đã thay đổi cách mọi người giao tiếp",
        questionType = QuestionType.TEXT,
        answer = listOf("social", " media", " has", " transformed", " how", " people", " communicate"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("social", "media", "communicate", "people"),
        maskedAnswer = listOf("social", " media")
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Tin tức trực tuyến phổ biến hơn truyền thông in ấn",
        questionType = QuestionType.TEXT,
        answer = listOf("online", "news", "is", "more", "popular", "than", "print", "media"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("news", "online", "media", "popular")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Media can influence public opinion",
        questionType = QuestionType.LISTENING,
        answer = listOf("media", "can", "influence", "public", "opinion"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Quảng cáo là một phần lớn của truyền thông",
        questionType = QuestionType.TEXT,
        answer = listOf("advertising", "is", "a", "big", "part", "of", "media"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("advertising", "big", "media", "part")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Social media platforms encourage sharing",
        questionType = QuestionType.LISTENING,
        answer = listOf("social", "media", "platforms", "encourage", "sharing"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Truyền thông đóng vai trò quan trọng trong việc kết nối con người",
        questionType = QuestionType.TEXT,
        answer = listOf("media", " plays", " a", " vital", " role", " in", " connecting", " people"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("media", "role", "connecting", "people"),
        maskedAnswer = listOf("media", " role")
    ),

    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Journalism is essential for democracy",
        questionType = QuestionType.LISTENING,
        answer = "Journalism is essential for democracy",
        answerType = AnswerType.TYPING
    ),

    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "News coverage can bring awareness to social issues",
        questionType = QuestionType.TEXT,
        answer = "news coverage can bring awareness to social issues",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Media literacy is important in the digital age",
        questionType = QuestionType.TEXT,
        answer = "media literacy is important in the digital age",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Media shapes cultural perceptions",
        questionType = QuestionType.LISTENING,
        answer = "Media shapes cultural perceptions",
        answerType = AnswerType.TYPING
    )
)

// bai 20
val travelChallenges = listOf(
    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Du lịch quốc tế đòi hỏi kế hoạch cẩn thận",
        questionType = QuestionType.TEXT,
        answer = listOf("international", "travel", "requires", "careful", "planning"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("international", "travel", "planning", "careful")
    ),

    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Du lịch giúp mọi người thư giãn và nạp lại năng lượng",
        questionType = QuestionType.TEXT,
        answer = listOf("travel", " helps", " people", " relax", " and", " recharge"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("travel", "relax", "people", "helps"),
        maskedAnswer = listOf("travel", " relax")
    ),

    ChallengeForm(
        title = "Sắp xếp lại câu sau",
        questionContent = "Khám phá các nền văn hóa khác nhau rất thú vị",
        questionType = QuestionType.TEXT,
        answer = listOf("exploring", "different", "cultures", "is", "fascinating"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf("different", "cultures", "exploring", "fascinating")
    ),

    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Traveling by train can be very scenic",
        questionType = QuestionType.LISTENING,
        answer = listOf("traveling", "by", "train", "can", "be", "very", "scenic"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),
    ChallengeForm(
        title = "Hoàn thiện câu sau",
        questionContent = "Du lịch mở mang kiến thức về thế giới",
        questionType = QuestionType.TEXT,
        answer = listOf("travel", " broadens", " knowledge", " about", " the", " world"),
        answerType = AnswerType.WORD_PICKER_FILLING,
        answerOptions = listOf("travel", "knowledge", "broadens", "world"),
        maskedAnswer = listOf("travel", " knowledge")
    ),
    ChallengeForm(
        title = "Nghe và sắp xếp lại câu sau",
        questionContent = "Tourism boosts the local economy",
        questionType = QuestionType.LISTENING,
        answer = listOf("tourism", "boosts", "the", "local", "economy"),
        answerType = AnswerType.WORD_PICKER_SEQUENCE,
        answerOptions = listOf()
    ),

    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Air travel is the fastest way to get around",
        questionType = QuestionType.LISTENING,
        answer = "Air travel is the fastest way to get around",
        answerType = AnswerType.TYPING
    ),

    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "I love visiting historical landmarks when I travel",
        questionType = QuestionType.TEXT,
        answer = "i love visiting historical landmarks when i travel",
        answerType = AnswerType.TALKING
    ),
    ChallengeForm(
        title = "Nghe và điền lại câu",
        questionContent = "Packing light makes traveling easier",
        questionType = QuestionType.LISTENING,
        answer = "Packing light makes traveling easier",
        answerType = AnswerType.TYPING
    ),
    ChallengeForm(
        title = "Nhắc lại câu sau",
        questionContent = "Traveling with friends creates unforgettable memories",
        questionType = QuestionType.TEXT,
        answer = "traveling with friends creates unforgettable memories",
        answerType = AnswerType.TALKING
    )
)

val lessons = listOf(
    environmentalChallenges,
    vehicleChallenges,
    travellingChallenges,
    foodChallenges,
    cultureChallenges,
    historyChallenges,
    technologyChallenges,
    healthChallenges,
    sportsChallenges,
    musicChallenges,
    artChallenges,
    scienceChallenges,
    literatureChallenges,
    geographyChallenges,
    economyChallenges,
    politicsChallenges,
    educationChallenges,
    fashionChallenges,
    mediaChallenges,
    travelChallenges

)

class DemoLessonRepository : LessonRepository {
    override fun getLesson(lessonId: Int): List<ChallengeForm> {
        return lessons[lessonId]
    }
}
