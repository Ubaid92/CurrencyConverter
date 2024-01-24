import com.google.gson.annotations.SerializedName

data class ResponseData(
    val date: String,
    @SerializedName("usd", alternate = ["pkr", "gbp", "inr"])
    val conversionResult: String

)
