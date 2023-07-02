package com.grupo5.workwatchapp.network.dto.teams

data class ExpandableCardItem(
    val title: String,
    val secondaryText: String,
    val details: ItemDetail
){
    data class ItemDetail(val moreTxt: String)
}
