package com.example.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.quiz.databinding.FragmentGlideBinding

class GlideFragment : Fragment(R.layout.fragment_glide) {

    lateinit var bind: FragmentGlideBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_glide, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load("https://picsum.photos/seed/picsum/200/300")
            .into(bind.one)

        Glide.with(this)
            .load("https://picsum.photos/seed/picsum/200/300")
            .into(bind.two)

    }

}