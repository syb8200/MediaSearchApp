package com.practice.fc_3_chapter5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.practice.fc_3_chapter5.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    // Fragment는 binding 객체를 onDestroy 될 때 해제시켜줘야 하기 때문에 nullable로 설정
    private var binding : FragmentSearchBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSearchBinding.inflate(inflater, container, false).apply {
            binding = this
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}