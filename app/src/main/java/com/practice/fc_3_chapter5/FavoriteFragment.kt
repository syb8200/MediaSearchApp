package com.practice.fc_3_chapter5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.practice.fc_3_chapter5.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private var binding : FragmentFavoriteBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentFavoriteBinding.inflate(inflater, container, false).apply {
            binding = this
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}