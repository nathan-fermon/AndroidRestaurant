package fr.isen.fermon.androidrestaurant.network

import java.io.Serializable

class RegisterResult(val data: User) {}

class User(val id: Int): Serializable {}