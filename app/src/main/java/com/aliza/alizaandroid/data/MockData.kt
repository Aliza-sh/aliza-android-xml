package com.aliza.alizaandroid.data

class UserManager {

    private val userData: MutableList<UserModel> = mutableListOf()

    init {
        userData.add(UserModel(1, "Ali", 25))
        userData.add(UserModel(2, "Reza", 22))
        userData.add(UserModel(3, "Sara", 20))
        userData.add(UserModel(4, "Zahra", 15))
        userData.add(UserModel(5, "Sahar", 52))
        userData.add(UserModel(6, "Peyman", 20))
        userData.add(UserModel(7, "Naser", 12))
        userData.add(UserModel(8, "Sina", 5))
        userData.add(UserModel(9, "Masood", 77))
        userData.add(UserModel(10, "Alireza", 32))
        userData.add(UserModel(11, "Mohamad", 42))
    }

    fun getUsers(): List<UserModel> {
        return userData.toList()
    }

    fun getMaxId(): Int {
        return userData.maxOfOrNull { it.id } ?: 0
    }

    fun addUser(user: UserModel): Boolean {
        return userData.add(user)
    }

    fun removeUser(userId: Int): Boolean {
        return userData.removeIf { it.id == userId }
    }

    fun updateUser(updatedUser: UserModel): Boolean {
        val index = userData.indexOfFirst { it.id == updatedUser.id }
        return if (index != -1) {
            userData[index] = updatedUser
            true
        } else {
            false
        }
    }
}


/*It is only for testing the adapter.

fun loadUserData(): MutableList<UserModel> {

    val userData: MutableList<UserModel> = mutableListOf()
    userData.add(UserModel(1,"Ali",25))
    userData.add(UserModel(2,"Reza",22))
    userData.add(UserModel(3,"Sara",20))
    userData.add(UserModel(4,"Zahra",23))
    userData.add(UserModel(5,"Sahar",23))
    userData.add(UserModel(6,"Peyman",23))
    userData.add(UserModel(7,"Naser",23))
    userData.add(UserModel(8,"Sina",23))
    userData.add(UserModel(9,"Masood",23))
    userData.add(UserModel(10,"Alireza",23))
    userData.add(UserModel(11,"Mohamad",23))

    return userData
}
OR
fun loadUserData(): MutableList<UserModel> {
    return mutableListOf(
        UserModel("Ali", 25),
        UserModel("Reza", 22),
        UserModel("Sara", 20),
        UserModel("Zahra", 23),
        UserModel("Sahar", 23),
        UserModel("Peyman", 23),
        UserModel("Naser", 23),
        UserModel("Sina", 23),
        UserModel("Masood", 23),
        UserModel("Alireza", 23),
        UserModel("Mohamad", 23)
    )
}*/
