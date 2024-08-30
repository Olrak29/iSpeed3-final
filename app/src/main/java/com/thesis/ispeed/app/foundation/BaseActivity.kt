package com.thesis.ispeed.app.foundation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.thesis.ispeed.app.util.NavigationUtil
import com.thesis.ispeed.app.util.SpeedTestHandler
import com.thesis.ispeed.app.util.ValidationUtil
import com.thesis.ispeed.app.util.ViewUtil
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : AppCompatActivity() {

    lateinit var binding: VB

    @Inject
    lateinit var validationUtil: ValidationUtil

    @Inject
    lateinit var navigationUtil: NavigationUtil

    @Inject
    lateinit var viewUtil: ViewUtil

    @Inject
    lateinit var speedTestHandler: SpeedTestHandler

    protected open fun onActivityCreated() { initViewBinding() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivityCreated()
    }

    private fun initViewBinding() {
        binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
    }
}