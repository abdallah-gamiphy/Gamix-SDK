package com.gamiphy.library.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gamiphy.library.GamiBot
import com.gamiphy.library.actions.GamiphyWebViewActions
import com.gamiphy.library.R
import com.gamiphy.library.utils.GamiphyConstants
import com.gamiphy.library.utils.GamiphyData
import com.gamiphy.library.utils.JavaScriptScripts
import com.gamiphy.library.utils.JavaScriptScripts.JAVASCRIPT_OBJ


class GamiphyWebViewActivity : AppCompatActivity(),
    GamiphyWebViewActions {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private val gamiBot = GamiBot.getInstance()
    private val gamiphyData = GamiphyData.getInstance()
    private var firstLogin: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        checkFirstStart()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamiphy_web_view)
        gamiBot.registerGamiphyWebViewActions(this)
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
        gamiBot.unRegisterGamiphyWebViewActions(this)
    }

    override fun login() {
        postTokenMessage()
        refresh()
    }

    override fun logout() {
        gamiphyData.user = null
        executeJavaScript(JavaScriptScripts.logout())
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

    override fun markTaskDone(eventName: String) {
//        executeJavaScript(JavaScriptScripts.trackEvent(eventName))
    }

    override fun markRedeemDone(rewardId: String) {
//        executeJavaScript(JavaScriptScripts.redeemReward(rewardId))
    }

    private fun initViews() {
        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)
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

        webView.loadDataWithBaseURL(
            "https://sdk.dev.gamiphy.co",
            "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <script src='$url'></script>\n" +
                    "</head>\n" +
                    "</html>"
            , "text/html", null, ""
        )

        webView.addJavascriptInterface(
            JavaScriptInterface(), JAVASCRIPT_OBJ
        )

        webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showLoading()

            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                hideLoading()
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
            }


            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                hideLoading()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                hideLoading()
            }
        }
    }

    /**
     * init web view with token if exist or user.email
     * else open unSigned web view
     */
    private fun postTokenMessage() {
        executeJavaScript(JavaScriptScripts.init(gamiphyData.botId, gamiphyData.user))
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

    private inner class JavaScriptInterface {
        @JavascriptInterface
        fun isLoggedIn(isLogIn: Boolean) {
            Log.d(GamiphyWebViewActivity::class.java.simpleName, " isLoggedIn =====<>>>>$isLogIn")
            gamiBot.notifyAuthTrigger(isLogIn)
        }

        @JavascriptInterface
        fun eventFromWeb(event: String) {
            Log.d(GamiphyWebViewActivity::class.java.simpleName, "=====<>>>>$event")
        }
    }

    private fun share(text: String?, link: String?) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "$text \n $link")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    companion object {

        @JvmStatic
        fun newIntent(context: Context) =
            Intent(context, GamiphyWebViewActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
    }
}