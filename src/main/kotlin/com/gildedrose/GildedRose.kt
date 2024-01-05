package com.gildedrose

// DO NOT CHANGE THE ITEMS PROPERTY
class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            if (item.name == "Sulfuras, Hand of Ragnaros") continue

            item.updateQuality()
            item.sellIn -= 1
        }
    }

    companion object {
        const val MAXIMUM_QUALITY = 50
    }

    private val Item.hasAlreadyReachedSellByDate get() = sellIn <= 0

    private fun Item.adjustQualityBy(amount: Int) {
        val projectedQuality = quality + amount

        quality = when {
            projectedQuality > MAXIMUM_QUALITY -> MAXIMUM_QUALITY
            projectedQuality < 0 -> 0
            else -> projectedQuality
        }
    }

    private fun Item.updateAgedBrie() {
        val increment = if (hasAlreadyReachedSellByDate) 2 else 1
        adjustQualityBy(increment)
    }

    private fun Item.updateBackstagePass() {
        val increment = when {
            sellIn <= 5 -> 3
            sellIn <= 10 -> 2
            else -> 1
        }

        if (hasAlreadyReachedSellByDate) quality = 0
        else adjustQualityBy(increment)
    }

    private fun Item.updateQuality() {
        if (name == "Sulfuras, Hand of Ragnaros") return

        when (name) {
            "Aged Brie" -> updateAgedBrie()
            "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePass()
            else -> {
                adjustQualityBy(-1)
                if (hasAlreadyReachedSellByDate) adjustQualityBy(-1)
            }
        }
    }
}
