package com.example.odainterviewcase.common.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OdaInterviewCaseModel(
    val responseType: String?="",
    val queryString: String?="",
    val attributes: OdaInterviewCaseAttributes,
    val items: List<OdaInterviewCaseItem>
)

@JsonClass(generateAdapter = true)
data class OdaInterviewCaseAttributes(
    val items: Int,
    val page: Int,
    val hasMoreItems: Boolean?=false,
    val requestTypes: List<RequestType>?=null
)

@JsonClass(generateAdapter = true)
data class RequestType(
    val count: Int,
    val type: String,
    val displayName: String?=""
)

@JsonClass(generateAdapter = true)
data class OdaInterviewCaseItem(
    val id: Double?=0.0,
    val type: String,
    val attributes: OdaInterviewCaseItemAttributes?=null,
    val items: List<OdaInterviewCaseItem>?=null
)

@JsonClass(generateAdapter = true)
data class OdaInterviewCaseItemAttributes(
    val title: String?="",
    val full_name: String?="",
    val gross_price: Double?=0.0,
    val gross_unit_price: Double?=0.0,
    val unit_price_quantity_abbreviation: String?="",
    val currency: String?="",
    val availability: Availability?=null,
    val total_items: Double?=0.0,
    val name: String?="",
    val navigate_to: String?="",
    val display_name: String?="",
    val difficulty_string: String?="",
    val difficulty: String?,
    val cooking_duration_string: String?="",
    val is_liked_by_user: Boolean?=false,
    val like_count: Int?=0,
    val parent: Double?=0.0,
    val has_children: Boolean?=false
)

@JsonClass(generateAdapter = true)
data class Availability(
    val is_available: Boolean?=false,
    val description: String?="",
    val description_short: String?="",
    val code: String?=""
)