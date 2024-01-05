package com.gildedrose

// DO NOT CHANGE THE ITEMS PROPERTY
class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            item.update()
        }
    }

    private fun Item.update() {
        if (name != "Aged Brie" && name != "Backstage passes to a TAFKAL80ETC concert") {
            if (quality > 0) {
                if (name != "Sulfuras, Hand of Ragnaros") {
                    quality -= 1
                }
            }
        } else {
            if (quality < 50) {
                quality += 1

                if (name == "Backstage passes to a TAFKAL80ETC concert") {
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
            }
        }

        if (name != "Sulfuras, Hand of Ragnaros") {
            sellIn -= 1
        }

        if (sellIn < 0) {
            when (name) {
                "Aged Brie" -> {
                    if (quality < 50) {
                        quality += 1
                    }
                }

                "Backstage passes to a TAFKAL80ETC concert" -> {
                    quality -= quality
                }

                else -> {
                    if (quality > 0) {
                        if (name != "Sulfuras, Hand of Ragnaros") {
                            quality -= 1
                        }
                    }
                }
            }
        }
    }
}
