package com.example.lircaymarket.entity

object SaveData
{
    private var users = arrayListOf<User>()
    var Market = arrayListOf<Market>()
    var pantry = arrayListOf<Pantry>()
    var shoppinglist = arrayListOf<Shoppinglist>()

    fun AddUser(user: User)
    {
        users.add(user)
    }

    fun GetUsers(): ArrayList<User>
    {
        return users;
    }
}