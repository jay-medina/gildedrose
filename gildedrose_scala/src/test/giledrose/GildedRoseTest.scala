package giledrose

import gildedrose._
import org.scalatest.FunSuite

class GildedRoseTest extends FunSuite{

  var gildedRose: GildedRose = _
  var item: Item = _

  test("Normal item sellIn date and quality should decrease") {
    item = new Item("Normal", 10, 5)
    new GildedRose(Array(item)).updateQuality()

    assert(item.sellIn == 9)
    assert(item.quality == 4)
  }

  test("Once the sell In date passes, the quality decreases twice as fast") {
    item = new Item("Normal", 0, 20)
    gildedRose = new GildedRose(Array(item))

    gildedRose.updateQuality()
    assert(item.sellIn == -1)
    assert(item.quality == 18)

    gildedRose.updateQuality()
    assert(item.sellIn == -2)
    assert(item.quality == 16)
  }

  test("The quality of a normal item is never negative") {
    item = new Item(Item.NORMAL, 2, 1)
    gildedRose = new GildedRose(Array(item))

    gildedRose.updateQuality()
    assert(item.sellIn == 1)
    assert(item.quality == 0)

    gildedRose.updateQuality()
    assert(item.sellIn == 0)
    assert(item.quality == 0)
  }

  test("Aged Brie sellIn date should decrease") {
    item = new Item(Item.AGED_BRIE, 10, 5)

    new GildedRose(Array(item)).updateQuality()

    assert(item.sellIn == 9)
  }

  test("Aged Brie quality should increase") {
    item = new Item(Item.AGED_BRIE, 10, 5)

    new GildedRose(Array(item)).updateQuality()

    assert(item.quality == 6)
  }

  test("Aged Brie quality should never be more than 50") {
    item = new Item(Item.AGED_BRIE, 10, 49)

    val gildedRose = new GildedRose(Array(item))

    gildedRose.updateQuality()
    assert(item.quality == 50)

    gildedRose.updateQuality()
    assert(item.quality == 50)
  }

  test("Aged Brie quality can never pass in value greater than 50") {
    intercept[IllegalArgumentException] {
      item = new Item(Item.AGED_BRIE, 10, 51)
      new GildedRose(Array(item)).updateQuality()
    }
  }

  test("Backstage sellIn date should decrease") {
    item = new Item(Item.BACKSTAGE, 15, 5)
    new GildedRose(Array(item)).updateQuality()

    assert(item.sellIn == 14)
  }

  test("Backstage quality should increase by 1 for sellin date > 10") {
    item = new Item(Item.BACKSTAGE, 15, 5)
    new GildedRose(Array(item)).updateQuality()

    assert(item.quality == 6)
  }

  test("Backstage quality should increase by 2 for sellin date <= 10") {
    item = new Item(Item.BACKSTAGE, 10, 5)
    new GildedRose(Array(item)).updateQuality()

    assert(item.quality == 7)
  }

  test("Backstage quality should increase by 3 for sellin date <= 5") {
    item = new Item(Item.BACKSTAGE, 5, 5)
    new GildedRose(Array(item)).updateQuality()

    assert(item.quality == 8)
  }

  test("Backstage quality should be 0 for sellin date <= 0") {
    item = new Item(Item.BACKSTAGE, 1, 5)
    gildedRose = new GildedRose(Array(item))
    gildedRose.updateQuality()
    assert(item.quality == 8)

    gildedRose.updateQuality()
    assert(item.quality == 0)
  }

  test("Backstage quality should never be more than 50") {

    item = new Item(Item.BACKSTAGE, 10, 49)

    val gildedRose = new GildedRose(Array(item))

    gildedRose.updateQuality()
    assert(item.quality == 50)

    gildedRose.updateQuality()
    assert(item.quality == 50)
  }

  test("Backstage quality can never pass in value greater than 50") {
    intercept[IllegalArgumentException] {
      item = new Item(Item.BACKSTAGE, 10, 51)
      new GildedRose(Array(item)).updateQuality()
    }
  }

  test("Sulfuras, Hand of Ragnaros sellIn and quality should stay the same") {
    item = new Item(Item.SULFURA, 0, 80)
    new GildedRose(Array(item)).updateQuality()

    assert(item.sellIn == 0)
    assert(item.quality == 80)
  }

}
