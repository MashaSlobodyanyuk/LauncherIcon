package ru.netology.nmedia.activity
import android.annotation.SuppressLint
import android.util.Log


import androidx.appcompat.app.AppCompatActivity
 //мпортировали этот файл ниже (кнопки)
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel
import ru.netology.nmedia.R


class MainActivity : AppCompatActivity() {


    //мы импортировали  buildFeatures в Gradle, чтобы работал этот метод


    //мы объявляем переменную binding, которая реализует класс АctivityMainBinding
    private var _binding: ActivityMainBinding? = null

    val binding: ActivityMainBinding
        get() = _binding!!

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        //сохраняем экземпляр класса функцией inflate - передаем переменные в View из верстки
        // в этот класс ActivityMainBinding. layoutInflater - берет свойства из текущего класса
        //MainActivity и создает объекты в ActivityMainBinding
        _binding = ActivityMainBinding.inflate(layoutInflater)

        //root - корень, который содержит все элементы верстки. Мы уже получаем объекты не из
        //activity_main (как по дефолту), а из binding
        setContentView(binding.root)


        val viewModel: PostViewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->


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
                like.setImageResource(if (post.likedByMe) R.drawable.red_favorite_24 else R.drawable.favorite_border_24)

            }
        }
        binding.like.setOnClickListener {
            viewModel.like()
        }



        binding.share.setOnClickListener {
            viewModel.share()
        }
    }

        fun correctNumber(post: Int): String {
            when (post) {
                in 0..999 -> return post.toString()
                in 1000..9999 -> if (post % 1000 <= 99) {
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






