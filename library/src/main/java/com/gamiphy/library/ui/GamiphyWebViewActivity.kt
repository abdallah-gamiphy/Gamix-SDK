package com.gamiphy.library.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gamiphy.library.GamiBot
import com.gamiphy.library.helper.JavaScriptInterfaceHelper
import com.gamiphy.library.R
import com.gamiphy.library.models.User
import com.gamiphy.library.constant.GamiphyConstants
import com.gamiphy.library.models.GamiphyData
import com.gamiphy.library.constant.JavaScriptScripts
import com.gamiphy.library.constant.JavaScriptScripts.JAVASCRIPT_OBJ


class GamiphyWebViewActivity : AppCompatActivity(),
    GamiphyWebViewActions {
    private lateinit var webView: WebView
    private lateinit var closeBtn: ImageButton
    private lateinit var progressBar: ProgressBar
    private val gamiphyData = GamiphyData.getInstance()
    private var firstLogin: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        checkFirstStart()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamiphy_web_view)
        GamiBot.getInstance().registerGamiphyWebViewActions(this)
        initViews()
    }

    private fun checkFirstStart() {
        if (firstLogin) {
            moveTaskToBack(true);
            firstLogin = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        GamiBot.getInstance().unRegisterGamiphyWebViewActions(this)
    }

    override fun login(user: User) {
        gamiphyData.config?.user = user
        refresh()
    }

    override fun logout() {
        gamiphyData.config?.user = null
        refresh()
    }

    override fun onBackPressed() {
        moveTaskToBack(true);
    }

    override fun close() {
        onBackPressed()
    }

    override fun refresh() {
        initWebView(GamiphyConstants.BOT_API)
    }

    private fun initViews() {
        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)
        closeBtn = findViewById(R.id.close_btn)
        initWebView(GamiphyConstants.BOT_API)
    }

    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
    private fun initWebView(url: String) {
        val webSettings = webView.settings
        webView.webChromeClient = WebChromeClient()
        with(webSettings) {
            javaScriptEnabled = true
            domStorageEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            builtInZoomControls = false
            blockNetworkImage = false
            loadsImagesAutomatically = true
            supportMultipleWindows()

        }

        webView.loadDataWithBaseURL(url, GamiphyConstants.BOT_SCRIPT, "text/html", null, "")

        webView.addJavascriptInterface(
            JavaScriptInterfaceHelper(), JAVASCRIPT_OBJ
        )

        webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showLoading()
                showCloseBtn()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                hideLoading()
                hideCloseBtn()
                postTokenMessage()
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val requestUrl = request?.url.toString()
                view?.loadUrl(requestUrl)
                return false
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                hideLoading()
                hideCloseBtn()
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                hideLoading()
                hideCloseBtn()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                hideLoading()
                hideCloseBtn()
            }
        }
    }

    /**
     * init web view with token if exist or user.email
     * else open unSigned web view
     */
    private fun postTokenMessage() {
        executeJavaScript(JavaScriptScripts.init(gamiphyData.config!!))
    }

    private fun executeJavaScript(script: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.post { webView.evaluateJavascript(script, null) }
        } else {
            webView.post { webView.loadUrl(script, null) }
        }
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun showCloseBtn() {
        closeBtn.visibility = View.GONE
    }

    private fun hideCloseBtn() {
        closeBtn.visibility = View.VISIBLE
    }

    fun closeBot(view: View) {
        onBackPressed()
    }

    companion object {
        @JvmStatic
        fun newIntent(context: Context) =
            Intent(context, GamiphyWebViewActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
    }
}