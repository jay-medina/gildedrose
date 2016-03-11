package gildedrose

class Item (n: String, s: Int, q: Int) {
  val name: String = n
  var sellIn: Int = s
  var quality: Int = q

  def updateQuality() {

    if (!this.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
      if (this.quality > 0) {
        if (!this.name.equals("Sulfuras, Hand of Ragnaros")) {
          this.quality = this.quality - 1
        }
      }
    } else {
      if (this.quality < 50) {
        this.quality = this.quality + 1

        if (this.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
          if (this.sellIn < 11) {
            if (this.quality < 50) {
              this.quality = this.quality + 1
            }
          }

          if (this.sellIn < 6) {
            if (this.quality < 50) {
              this.quality = this.quality + 1
            }
          }
        }
      }
    }

    if (!this.name.equals("Sulfuras, Hand of Ragnaros")) {
      this.sellIn = this.sellIn - 1
    }


    if (this.sellIn < 0) {
        if (!this.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
          if (this.quality > 0) {
            if (!this.name.equals("Sulfuras, Hand of Ragnaros")) {
              this.quality = this.quality - 1
            }
          }
        } else {
          this.quality = this.quality - this.quality
        }
    }


  }
}

case class AgedBrie(s: Int, q: Int) extends Item("Aged Brie", s, q) {
  require(q < 50, "quality cannot be greater than 50")

  override def updateQuality() {
    this.sellIn -= 1
    if(this.quality < 50) {
      this.quality = this.quality + 1
    }
  }
}

class GildedRose(val items: Array[Item]) {

  def updateQuality() {
    for (i <- items) {
      i.updateQuality()
    }
  }
}
