package com.jjyoun.tiptime

open class Item(val name: String, val price: Int)

class Noodles: Item("Noodles", 10){
    override fun toString():String{
        return name
    }
}
//val topping1: String, val topping2: String.. 이렇게 적거나
//val toppings: List<String>으로 적거나

class Vegetables(vararg val toppings: String): Item("Vegetables", 5){
    //vararg는 변수의 수를 같은 타입의 인자로 pass하게 해준다..
    //vararg: 가변인자
    //list 대신 사용할 수 있다....
    override fun toString():String{
        return name + " " + toppings.joinToString()
        //joinToString() 메소드: 모든 토핑들을 single String으로 join한다
    }
}

class Order(val orderNumber: Int){
    private val itemList = mutableListOf<Item>()

    fun addItem(newItem: Item){
        itemList.add(newItem)
    }
    fun addAll(newItems: List<Item>){
        itemList.addAll(newItems)
    }
    fun print(){
        println("Order #${orderNumber}")
        var total = 0
        for (item in itemList){
            println("${item}: $${item.price}")
            total += item.price
        }
        println("Total: $${total}")
    }
}

fun main() {
    val order1 = Order(1)
    order1.addItem(Noodles())
    order1.print()

    println()

    val order2 = Order(2)
    order2.addItem(Noodles())
    order2.addItem(Vegetables())
    order2.print()

    println()

    val order3 = Order(3)
    val items = listOf(Noodles(), Vegetables("Carrots", "Beans", "Celery"))
    order3.addAll(items)
    order3.print()
}