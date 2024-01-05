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

    private fun Item.updateAgedBrieQuality() {
        val amount = if (hasAlreadyReachedSellByDate) 2 else 1
        adjustQualityBy(amount)
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

    private fun Item.updateStandardItemQuality() {
        val amount = if (hasAlreadyReachedSellByDate) -2 else -1
        adjustQualityBy(amount)
    }

    private fun Item.updateQuality() {
        when (name) {
            "Aged Brie" -> updateAgedBrieQuality()
            "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePassQuality()
            else -> updateStandardItemQuality()
        }
    }
}
