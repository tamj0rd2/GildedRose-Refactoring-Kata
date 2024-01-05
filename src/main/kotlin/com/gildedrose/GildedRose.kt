package com.gildedrose

// DO NOT CHANGE THE ITEMS PROPERTY
class GildedRose(var items: List<Item>) {
    companion object {
        const val MAXIMUM_QUALITY = 50
    }

    fun updateQuality() {
        for (item in items) {
            if (item.name == "Sulfuras, Hand of Ragnaros") continue

            item.updateQuality()
            item.sellIn -= 1
        }
    }
}
