import com.google.gson.annotations.SerializedName

data class ResponseData(
    val date: String,
    @SerializedName("usd", alternate = ["pkr", "gbp", "inr","sar","omr","aed","eur"])
    val conversionResult: String

)
