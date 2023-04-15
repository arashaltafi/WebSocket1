package com.arash.altafi.websocket1.model

import com.google.gson.annotations.SerializedName

data class ResponseGson(

	@field:SerializedName("data")
	val data: DataGson? = null,

	@field:SerializedName("channel")
	val channel: String? = null,

	@field:SerializedName("event")
	val event: String? = null
)

data class DataGson(

	@field:SerializedName("buy_order_id")
	val buyOrderId: Long? = null,

	@field:SerializedName("amount")
	val amount: Double? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("sell_order_id")
	val sellOrderId: Long? = null,

	@field:SerializedName("microtimestamp")
	val microtimestamp: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("price_str")
	val priceStr: String? = null,

	@field:SerializedName("type")
	val type: Int? = null,

	@field:SerializedName("amount_str")
	val amountStr: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)
