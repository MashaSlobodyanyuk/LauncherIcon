package ru.netology.nmedia.repository

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory : PostRepository {
   private var post = Post(
       1,
       "Нетология. Меняем карьеру через образование",
       "Поехать в офис или поработать из дома? Что приготовить на завтрак? Как провести ближайшие выходные? Каждый день мы принимаем множество самых разных решений. Вместе с преподавателем курса «Психология: путь к себе» и автором Telegram-канала «Искусство психоанализа» Алексеем Пережогиным разбираемся, почему иногда бывает сложно сделать выбор и как найти оптимальный для себя вариант. Понять, чего вам хочется на самом деле, поможет бесплатный курс «Психология: путь к себе»: https://netolo.gy/bQES",
       "21 мая в 18:36",
   )

    private val data = MutableLiveData(post)

    override fun get() = data

    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe, likes = if(post.likedByMe) post.likes-1 else post.likes+1)
        //записываем в пост в LiveData
        data.value = post
    }

    override fun share() {
       post = post.copy(share = post.share+1)
        //записываем в пост в LiveData
        data.value= post
    }
}