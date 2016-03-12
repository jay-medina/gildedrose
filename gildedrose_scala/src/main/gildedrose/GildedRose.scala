package gildedrose

object Item {
  val NORMAL = "normal"
  val SULFURA = "Sulfuras, Hand of Ragnaros"
  val AGED_BRIE = "Aged Brie"
  val BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert"
}

class Item(val name: String, var sellIn: Int, var quality: Int)

object Update{
  def normal(item: Item) = {
    item.sellIn -= 1

    if (item.quality > 0) item.quality -= 1

    if (item.sellIn < 0 && item.quality > 0) item.quality -= 1
  }

  def sulfura(item: Item) = {
    item.quality = 80
  }

  def agedBrie(item: Item) = {
    require(item.quality <= 50, "quality cannot be greater than 50")
    item.sellIn -= 1

    if (item.quality < 50) item.quality += 1
  }

  def backstage(item: Item) = {
    require(item.quality <= 50, "quality cannot be greater than 50")
    item.sellIn -= 1

    if(item.quality < 50) {
      item.quality += 1
      if(item.sellIn < 11) item.quality += 1
      if(item.sellIn < 6) item.quality += 1
      if(item.sellIn < 0) item.quality = 0
    }

    if(item.quality > 50) item.quality = 50
  }
}


class GildedRose(val items: Array[Item]) {

  def updateQuality() {
    items.foreach(item => item.name match {
      case Item.AGED_BRIE => Update agedBrie item
      case Item.BACKSTAGE => Update backstage item
      case Item.NORMAL => Update normal item
      case Item.SULFURA => Update sulfura item
      case _ => Update normal item
    })
  }
}
