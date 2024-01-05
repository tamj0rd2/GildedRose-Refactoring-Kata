package com.gildedrose

// DO NOT CHANGE THE ITEMS PROPERTY
class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            item.update()
        }
    }

    private fun Item.updateAgedBrie() {
        if (quality < 50) {
            quality += 1
        }
    }

    private fun Item.updateBackstagePass() {
        if (quality < 50) {
            quality += 1
        }

        if (sellIn < 11) {
            if (quality < 50) {
                quality += 1
            }
        }

        if (sellIn < 6) {
            if (quality < 50) {
                quality += 1
            }
        }
    }

    private fun Item.update() {
        if (name == "Sulfuras, Hand of Ragnaros") return

        when (name) {
            "Aged Brie" -> updateAgedBrie()
            "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePass()
            else -> {
                if (quality > 0) quality -= 1
            }
        }

        sellIn -= 1

        if (sellIn < 0) {
            when (name) {
                "Aged Brie" -> {
                    if (quality < 50) {
                        quality += 1
                    }
                }

                "Backstage passes to a TAFKAL80ETC concert" -> quality = 0

                else -> if (quality > 0) quality -= 1
            }
        }
    }
}
