package com.example.chippermovie.common.utils

import com.example.chippermovie.Constants

class UrlImage {

    companion object {
        fun getUrlImage(urlImage: String) = Constants.URL_IMAGE_DATABASE + urlImage
    }
}