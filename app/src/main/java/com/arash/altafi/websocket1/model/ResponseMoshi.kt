package com.arash.altafi.websocket1.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseMoshi(

	@Json(name="data")
	val data: DataMoshi? = null,

	@Json(name="channel")
	val channel: String? = null,

	@Json(name="event")
	val event: String? = null
)

@JsonClass(generateAdapter = true)
data class DataMoshi(

	@Json(name="buy_order_id")
	val buyOrderId: Long? = null,

	@Json(name="amount")
	val amount: Double? = null,

	@Json(name="price")
	val price: Int? = null,

	@Json(name="sell_order_id")
	val sellOrderId: Long? = null,

	@Json(name="microtimestamp")
	val microtimestamp: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="price_str")
	val priceStr: String? = null,

	@Json(name="type")
	val type: Int? = null,

	@Json(name="amount_str")
	val amountStr: String? = null,

	@Json(name="timestamp")
	val timestamp: String? = null
)
