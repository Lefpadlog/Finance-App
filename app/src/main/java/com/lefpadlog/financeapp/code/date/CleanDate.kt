package com.lefpadlog.financeapp.code.date

fun cleanDate(originalDate: String) : String {
    val cleanedDate = originalDate.replace(Regex("[/-]"), ".")
    val day = cleanedDate.split(".")[0].padStart(2, '0').replace("00", "01")
    val month = cleanedDate.split(".")[1].padStart(2, '0').replace("00", "01")
    val year = cleanedDate.split(".")[2].padStart(4, '0').replace("0000", "0001")
    return "$day.$month.$year"
}