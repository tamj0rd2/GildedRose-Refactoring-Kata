package com.gildedrose

import strikt.api.DescribeableBuilder
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class GildedRoseTest {

    @Test
    fun `aged brie`() {
        val agedBrie = Item(name = "Aged Brie", sellIn = 2, quality = 0)

        val items = listOf(agedBrie)
        val app = GildedRose(items)

        app.updateQuality()
        expectThat(app.items[0]).isEqualTo(1, 1)

        app.updateQuality()
        expectThat(app.items[0]).isEqualTo(0, 2)

        app.updateQuality()
        expectThat(app.items[0]).isEqualTo(-1, 4)
    }
}

private fun DescribeableBuilder<Item>.isEqualTo(expectedSellIn: Int, expectedQuality: Int) = this.apply {
    get { sellIn }.isEqualTo(expectedSellIn)
    get { quality }.isEqualTo(expectedQuality)
}
