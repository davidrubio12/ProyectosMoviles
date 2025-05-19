package com.example.proyectodemoviles.ui.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.proyectodemoviles.R

private const val ARG_PARAM1 = "url"


class webFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var url: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView = inflater.inflate(R.layout.fragment_web, container, false)
        var myWebView = myView.findViewById<WebView>(R.id.myWebView)

        myWebView.settings.javaScriptEnabled = true
        myWebView.webViewClient = WebViewClient()

        myWebView.loadUrl(url!!)
        // Inflate the layout for this fragment
        return myView
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            webFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)

                }
            }
    }
}