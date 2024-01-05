package com.gildedrose

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

    private val Item.hasReachedSellByDate get() = sellIn <= 0
    private val Item.isPastSellByDate get() = sellIn < 0

    private fun Item.adjustQualityBy(amount: Int) {
        val projectedQuality = quality + amount

        quality = when {
            projectedQuality > MAXIMUM_QUALITY -> MAXIMUM_QUALITY
            projectedQuality < 0 -> 0
            else -> projectedQuality
        }
    }

    private fun Item.updateAgedBrie() {
        val increment = if (hasReachedSellByDate) 2 else 1
        adjustQualityBy(increment)

        sellIn -= 1
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
                adjustQualityBy(-1)

                sellIn -= 1

                if (isPastSellByDate) adjustQualityBy(-1)
            }
        }
    }
}
