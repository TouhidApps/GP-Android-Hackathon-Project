package com.touhidapps.hackathonproject.model

import com.google.gson.annotations.SerializedName

   
data class Networks (

   @SerializedName("name") var name : String,
   @SerializedName("id") var id : Int,
   @SerializedName("logo_path") var logoPath : String,
   @SerializedName("origin_country") var originCountry : String

)