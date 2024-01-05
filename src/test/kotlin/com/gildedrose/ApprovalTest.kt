package com.gildedrose

import com.oneeyedmen.okeydoke.Approver
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension
import org.junit.jupiter.api.extension.RegisterExtension
import java.io.File
import kotlin.test.Test

class ApprovalTest {

    @Test
    fun `sample data`(approver: Approver) {
        val items = listOf(
            Item("+5 Dexterity Vest", 10, 20),
            Item("Aged Brie", 2, 0),
            Item("Aged Brie", 15, 0),
            Item("Elixir of the Mongoose", 5, 7),
            Item("Sulfuras, Hand of Ragnaros", 0, 80),
            Item("Sulfuras, Hand of Ragnaros", -1, 80),
            Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            Item("Conjured Mana Cake", 3, 6)
        )

        val app = GildedRose(items)
        val days = 30

        val longestNameLength = items.maxOf { it.name.length }

        val result = buildList {
            for (i in 0..days) {
                add("=========== Day $i ==============")
                add("${"Name".padEnd(longestNameLength)} | Sell in | Quality")
                for (item in items) {
                    add("${item.name.padEnd(longestNameLength)} | ${item.sellIn.toString().padStart(7)} | ${item.quality.toString().padStart(7)}")
                }
                add("")
                app.updateQuality()
            }
        }.joinToString("\n")

        approver.assertApproved(result) // check that the result is as approved
    }

    companion object {
        @JvmField
        @RegisterExtension
        @Suppress("unused")
        val approvals: ApprovalsExtension = ApprovalsExtension(File("src/test/resources"))
    }
}