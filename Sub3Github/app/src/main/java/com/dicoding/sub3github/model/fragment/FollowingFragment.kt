package com.dicoding.sub3github.model.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.sub3github.DetailActivity
import com.dicoding.sub3github.R
import com.dicoding.sub3github.databinding.FragmentFollowBinding
import com.dicoding.sub3github.model.user.UserViewAdapter

class FollowingFragment : Fragment(R.layout.fragment_follow) {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewModel: FollowersViewModel
    private lateinit var mAdapter: UserViewAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mArgs = arguments
        username = mArgs?.getString(DetailActivity.EXTRA_USERNAME).toString()


        _binding = FragmentFollowBinding.bind(view)
        mAdapter = UserViewAdapter()
        mAdapter.notifyDataSetChanged()

        binding.apply {
            rvGithubUser.setHasFixedSize(true)
            rvGithubUser.layoutManager = LinearLayoutManager(activity)
            rvGithubUser.adapter = mAdapter
        }
        mViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowersViewModel::class.java)
        mViewModel.setFollowers(username)
        mViewModel.getFollowers().observe(viewLifecycleOwner, {
            mAdapter.setList(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}