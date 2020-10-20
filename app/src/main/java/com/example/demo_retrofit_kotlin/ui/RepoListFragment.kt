package com.example.demo_retrofit_kotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.demo_retrofit_kotlin.R
import com.example.demo_retrofit_kotlin.adapter.RepoListAdapter
import com.example.demo_retrofit_kotlin.databinding.FragmentRepoListBinding
import com.example.demo_retrofit_kotlin.item.RepoListViewModel


class RepoListFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentRepoListBinding
    private lateinit var adapter: RepoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRepoListBinding.inflate(inflater,container,false).apply {
            viewmodel = ViewModelProviders.of(this@RepoListFragment).get(RepoListViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }
        return viewDataBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RepoListFragment().apply {}
    }
}