package com.gildedrose

import strikt.api.DescribeableBuilder
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class GildedRoseTest {

    @Test
    fun `aged brie`() {
        val item = Item(name = "Aged Brie", sellIn = 2, quality = 0)

        val app = GildedRose(listOf(item))

        app.updateQuality()
        expectThat(app.items[0]).isEqualTo(1, 1)

        app.updateQuality()
        expectThat(app.items[0]).isEqualTo(0, 2)

        app.updateQuality()
        expectThat(app.items[0]).isEqualTo(-1, 4)
    }

    @Test
    fun `backstage passes`() {
        val item = Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 2, quality = 10)

        val app = GildedRose(listOf(item))

        app.updateQuality()
        expectThat(app.items[0]).isEqualTo(1, 13)

        app.updateQuality()
        expectThat(app.items[0]).isEqualTo(0, 16)

        app.updateQuality()
        expectThat(app.items[0]).isEqualTo(-1, 0)
    }

    @Test
    fun `conjured items`() {
        val item = Item(name = "Conjured GumGum Fruit", sellIn = 3, quality = 8)

        val app = GildedRose(listOf(item))

        app.updateQuality()
        expectThat(app.items[0]).isEqualTo(2, 6)

        app.updateQuality()
        expectThat(app.items[0]).isEqualTo(1, 4)

        app.updateQuality()
        expectThat(app.items[0]).isEqualTo(0, 2)

        app.updateQuality()
        expectThat(app.items[0]).isEqualTo(-1, 0)

        app.updateQuality()
        expectThat(app.items[0]).isEqualTo(-2, 0)
    }
}

private fun DescribeableBuilder<Item>.isEqualTo(expectedSellIn: Int, expectedQuality: Int) = this.apply {
    get { sellIn }.isEqualTo(expectedSellIn)
    get { quality }.isEqualTo(expectedQuality)
}
