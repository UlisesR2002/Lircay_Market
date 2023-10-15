package com.example.lircaymarket.entity

object DataManager
{
    init
    {
        println("Singleton DataManager invoked.")
    }

    private var users = arrayListOf<User>()
    var Market = arrayListOf<Market>()
    var pantry = arrayListOf<Pantry>()
    var shoppinglist = arrayListOf<Shoppinglist>()
    var totalPrice = Int

    fun AddUser(user: User)
    {
        users.add(user)
    }

    fun GetUsers(): ArrayList<User>
    {
        return users;
    }
}