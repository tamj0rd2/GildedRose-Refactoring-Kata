package com.gildedrose

import kotlin.math.max
import kotlin.math.min

// DO NOT CHANGE THE ITEMS PROPERTY
class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            item.update()
        }
    }

    private val Item.isPastSellByDate get() = sellIn < 0

    private fun Item.updateAgedBrie() {
        sellIn -= 1

        val increment = if (isPastSellByDate) 2 else 1
        quality = min(quality + increment, 50)
    }

    private fun Item.updateBackstagePass() {
        if (quality < 50) quality += 1

        if (sellIn < 11 && quality < 50) quality += 1

        if (sellIn < 6 && quality < 50) quality += 1

        sellIn -= 1

        if (isPastSellByDate) quality = 0
    }

    private fun Item.update() {
        when (name) {
            "Aged Brie" -> updateAgedBrie()
            "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePass()
            "Sulfuras, Hand of Ragnaros" -> {}
            else -> {
                if (quality > 0) quality -= 1

                sellIn -= 1

                if (isPastSellByDate && quality > 0) quality -= 1
            }
        }
    }
}
