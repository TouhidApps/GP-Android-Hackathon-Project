package com.touhidapps.hackathonproject.model

import com.google.gson.annotations.SerializedName

   
data class ProductionCountries (

   @SerializedName("iso_3166_1") var iso31661 : String,
   @SerializedName("name") var name : String

)