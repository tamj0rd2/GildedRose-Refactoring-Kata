package com.gildedrose

import kotlin.math.min

// DO NOT CHANGE THE ITEMS PROPERTY
class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            item.update()
        }
    }

    companion object {
        const val MAXIMUM_QUALITY = 50
    }

    private val Item.isPastSellByDate get() = sellIn < 0

    private fun Item.adjustQualityBy(amount: Int) {
        quality = min(quality + amount, MAXIMUM_QUALITY)
    }

    private fun Item.updateAgedBrie() {
        sellIn -= 1

        val increment = if (isPastSellByDate) 2 else 1
        adjustQualityBy(increment)
    }

    private fun Item.updateBackstagePass() {
        val increment = when {
            sellIn <= 5 -> 3
            sellIn <= 10 -> 2
            else -> 1
        }

        sellIn -= 1

        if (isPastSellByDate) quality = 0
        else adjustQualityBy(increment)
    }

    private fun Item.update() {
        when (name) {
            "Aged Brie" -> updateAgedBrie()
            "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePass()
            "Sulfuras, Hand of Ragnaros" -> {}
            else -> {
                if (quality > 0) adjustQualityBy(-1)

                sellIn -= 1

                if (isPastSellByDate && quality > 0) adjustQualityBy(-1)
            }
        }
    }
}
