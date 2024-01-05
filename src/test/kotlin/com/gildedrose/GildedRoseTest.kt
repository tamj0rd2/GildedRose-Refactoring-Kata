package com.gildedrose

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isGreaterThan

internal class GildedRoseTest {

    @TestFactory
    fun `item quality sanity checks`(): List<DynamicTest> {
        data class TestCase(val item: Item, val dailyQualityExpectations: List<Int>)

        val testCases = listOf(
            TestCase(
                item = Item(name = "Aged Brie", sellIn = 2, quality = 0),
                dailyQualityExpectations = listOf(1, 2, 4, 6)
            ),
            TestCase(
                item = Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 2, quality = 10),
                dailyQualityExpectations = listOf(13, 16, 0)
            ),
            TestCase(
                item = Item(name = "Conjured GumGum Fruit", sellIn = 3, quality = 8),
                dailyQualityExpectations = listOf(6, 4, 2, 0, 0)
            ),
        )

        return testCases.map { tc ->
            DynamicTest.dynamicTest(tc.item.toString()) {
                val app = GildedRose(listOf(tc.item))

                val daysToCalculate = tc.dailyQualityExpectations.size
                expectThat(daysToCalculate).describedAs("number of days to calculate").isGreaterThan(0)

                val actualQualityOnEachDay = (1..daysToCalculate).map {
                    app.updateQuality()
                    tc.item.quality
                }

                expect {
                    tc.dailyQualityExpectations.forEachIndexed { i, expected ->
                        that(actualQualityOnEachDay[i]).describedAs("the quality on day $i").isEqualTo(expected)
                    }
                }
            }
        }
    }
}
