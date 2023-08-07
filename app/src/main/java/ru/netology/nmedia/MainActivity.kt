package ru.netology.nmedia
import android.util.Log


import androidx.appcompat.app.AppCompatActivity
 //мпортировали этот файл ниже (кнопки)
import android.widget.ImageButton
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.View.VIEW_LOG_TAG
import android.widget.ImageView
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import kotlin.math.roundToInt
import kotlin.math.roundToLong


class MainActivity : AppCompatActivity() {


    //мы импортировали  buildFeatures в Gradle, чтобы работал этот метод


    //мы объявляем переменную binding, которая реализует класс АctivityMainBinding
    private var _binding: ActivityMainBinding? = null

    val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        //сохраняем экземпляр класса функцией inflate - передаем переменные в View из верстки
        // в этот класс ActivityMainBinding. layoutInflater - берет свойства из текущего класса
        //MainActivity и создает объекты в ActivityMainBinding
        _binding = ActivityMainBinding.inflate(layoutInflater)

        //root - корень, который содержит все элементы верстки. Мы уже получаем объекты не из
        //activity_main (как по дефолту), а из binding
        setContentView(binding.root)


        val post = Post(
            1,
            "Нетология. Меняем карьеру через образование",
            "Поехать в офис или поработать из дома? Что приготовить на завтрак? Как провести ближайшие выходные? Каждый день мы принимаем множество самых разных решений. Вместе с преподавателем курса «Психология: путь к себе» и автором Telegram-канала «Искусство психоанализа» Алексеем Пережогиным разбираемся, почему иногда бывает сложно сделать выбор и как найти оптимальный для себя вариант. Понять, чего вам хочется на самом деле, поможет бесплатный курс «Психология: путь к себе»: https://netolo.gy/bQES",
            "21 мая в 18:36",
        )


//with(binding) массово общаемся к полям переменной binding, чтобы не писать каждый раз
        // binding.autor.text (из верстки) и т.п. text - это поле объекта autor типа TextView
        //autor.text = post.author - присваиваем значения из класса Post

        with(binding) {
            autor.text = post.author
            published.text = post.published
            longreed.text = post.content


//countLikes.text - т.к. это текстовое поле (String), post.likes - Int
            // нужно привести к String
            countLikes.text = correctNumber(post.likes)
            countShare.text = correctNumber(post.share)

            // если post.likedByMe - true - выполняем условие
            if (post.likedByMe) {
                //меняет картинку
                like.setImageResource(R.drawable.red_favorite_24)
            } else {
                like.setImageResource(R.drawable.favorite_border_24)
            }



            like.setOnClickListener {
                //если пост был тру станет фолс и наоборот
                post.likedByMe = !post.likedByMe

                // если post.likedByMe - true - выполняем условие
                if (post.likedByMe) {
                    post.likes++
                    like.setImageResource(R.drawable.red_favorite_24)
                } else {
                    like.setImageResource(R.drawable.favorite_border_24)
                    post.likes--
                }
                //тут мы меняем значение поля  countLikes.text - при нажатии меняем на новое значение
                countLikes.text = correctNumber(post.likes)
                Log.d("MainActivityPlain","сработал лайк")



//тоже решение без данных поста
                /* like.setOnClickListener {
            if (it is ImageButton) {
                it.setImageResource(R.drawable.red_favorite_24)
                }
                //тут решение старым способом
                /*   findViewById<ImageButton>(R.id.like).setOnClickListener {
             if (it is ImageButton){
                 it.setImageResource(R.drawable.red_favorite_24)
 */
            }
        }*/
            }

            share.setOnClickListener {
                post.share++
                countShare.text = correctNumber(post.share)
                Log.d("MainActivityPlain","сработал шаре")
            }


            root.setOnClickListener {
                println("Сработало")
                Log.d("MainActivityPlain","сработал рут")
            }

            avatar.setOnClickListener {
                Log.d("MainActivityPlain","сработалf аватарка")
            }

        }
    }

    fun correctNumber(post: Int): String {
        when (post) {
            in 0..999 -> return post.toString()
            in 1000..9999 -> if (post%1000<=99) {
                return (post / 1000).toString() + "K"
            } else {
                return ((post / 100).toDouble() / 10).toString() + "K"
            }

            in 10000..999_999 -> return (post / 1000).toString() + "К"
            else -> if (post % 1000_000 <= 99_999) {
                return (post / 1000_000).toString() + "М"
            } else {
                return ((post / 100_000).toDouble() / 10).toString() + "М"
            }

        }
    }
}





