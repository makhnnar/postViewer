package com.pedrogomez.postviewer.view.userposts.views

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.pedrogomez.postviewer.R
import com.pedrogomez.postviewer.databinding.ViewHitDetailBinding
import com.pedrogomez.postviewer.models.db.UserTable
import com.pedrogomez.postviewer.utils.extensions.print

class HitDetailView : ConstraintLayout {

    lateinit var binding : ViewHitDetailBinding

    var onDetailActions : OnDetailActions? = null

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        binding = ViewHitDetailBinding.inflate(
            LayoutInflater.from(context),
            this
        )
        val a = context.obtainStyledAttributes(
                attrs,
                R.styleable.HitDetailView,
                defStyle,
                0
        )

        a.recycle()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setData(userTable: UserTable){
        try{
            if (userTable.url!=null){
                binding.webView.loadUrl(userTable.url)
            }else if(userTable.story_url!=null){
                binding.webView.loadUrl(userTable.story_url)
            }
        }catch (e: Exception){
            "hitData: error".print()
        }
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        binding.webView.webViewClient =  object : WebViewClient(){

        }
        binding.btnBack.setOnClickListener {
            onDetailActions?.onBackPressed()
        }
        binding.tvBack.setOnClickListener {
            onDetailActions?.onBackPressed()
        }
    }

    interface OnDetailActions{
        fun onBackPressed()
    }
}