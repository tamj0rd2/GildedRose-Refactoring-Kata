package com.gildedrose

// DO NOT CHANGE THIS CLASS
open class Item(var name: String, var sellIn: Int, var quality: Int) {
    override fun toString(): String {
        return this.name + ", " + this.sellIn + ", " + this.quality
    }
}

fun Item.updateQuality() {
    when {
        name == "Aged Brie" -> adjustQualityBasedOnSellByDate(1)
        name == "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePassQuality()
        name.startsWith("Conjured") -> adjustQualityBasedOnSellByDate(-2)
        else -> adjustQualityBasedOnSellByDate(-1)
    }
}

private val Item.hasAlreadyReachedSellByDate get() = sellIn <= 0

private fun Item.adjustQualityBy(amount: Int) {
    val projectedQuality = quality + amount

    quality = when {
        projectedQuality > GildedRose.MAXIMUM_QUALITY -> GildedRose.MAXIMUM_QUALITY
        projectedQuality < 0 -> 0
        else -> projectedQuality
    }
}

private fun Item.adjustQualityBasedOnSellByDate(amount: Int) {
    adjustQualityBy(if (hasAlreadyReachedSellByDate) amount * 2 else amount)
}

private fun Item.updateBackstagePassQuality() {
    if (hasAlreadyReachedSellByDate) {
        quality = 0
        return
    }

    val amount = when {
        sellIn <= 5 -> 3
        sellIn <= 10 -> 2
        else -> 1
    }
    adjustQualityBy(amount)
}
