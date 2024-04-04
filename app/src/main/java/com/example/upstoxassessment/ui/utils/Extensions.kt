package com.example.upstoxassessment.ui.utils

import android.content.Context
import android.widget.TextView
import com.example.upstoxassessment.R

fun Double.stringFormatter(): String {
    return "₹" + String.format("%.2f", this)
}

fun Double.percentageFormatter(): String {
    return "(${String.format("%.2f", this)}%)"
}

fun TextView.setValue(value: Double, context: Context) {
    text = value.stringFormatter()
    setTextColor(if (value < 0) context.getColor(R.color.red) else context.getColor(R.color.green))
}

fun TextView.setValueWithAbsolutePercentage(
    value: Double,
    context: Context,
    absolutePercentage: Double
) {
    text = "${value.stringFormatter()}  ${absolutePercentage.percentageFormatter()}"
    setTextColor(if (value < 0) context.getColor(R.color.red) else context.getColor(R.color.green))
}