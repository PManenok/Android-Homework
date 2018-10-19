package by.itacademy.palina.task.home.hw7

import java.util.*


object UserListObject {
    var mapData = mutableMapOf<Long, UserData>(
            getPair("Anna", "Armstrong", "https://images.pexels.com/photos/46710/pexels-photo-46710.jpeg?auto=compress&cs=tinysrgb&h=350", Calendar.getInstance().time.time),
            getPair("Helen", "Burn", "https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg?cs=srgb&dl=artistic-blossom-bright-207962.jpg&fm=jpg", userID = Calendar.getInstance().time.time),
            getPair("Jane", "Brown", userID = Calendar.getInstance().time.time),
            getPair("John", "Smith", userID = Calendar.getInstance().time.time),
            getPair("Hanna", "Williams", "https://i.pinimg.com/originals/00/f9/12/00f9120dabc6cacccf5a4eb14fe10f3c.jpg", userID = Calendar.getInstance().time.time),
            getPair("Jane", "Smith", userID = Calendar.getInstance().time.time),
            getPair("Peter", "Parker", "https://us.123rf.com/450wm/chutimakuanamon/chutimakuanamon1706/chutimakuanamon170600223/80664851-spiderman-mask-illustration-red-color.jpg?ver=6", userID = Calendar.getInstance().time.time),
            getPair("Max", "Pain", userID = Calendar.getInstance().time.time),
            getPair("Gregorio", "Jones", userID = Calendar.getInstance().time.time),
            getPair("Susan", "Taylor", "https://www.bensound.com/bensound-img/november.jpg", userID = Calendar.getInstance().time.time),
            getPair("Sue", "Moore", "https://images.unsplash.com/photo-1529244927325-b3ef2247b9fb?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=37ecb282dbe66459c217ef28842c879a&w=1000&q=80", userID = Calendar.getInstance().time.time),
            getPair("Peter", "White", userID = Calendar.getInstance().time.time),
            getPair("Jack", "Lewis", "https://petapixel.com/assets/uploads/2018/07/dogphotosfeat-800x420.jpg", userID = Calendar.getInstance().time.time),
            getPair("Dana", "Scully", "https://thechive.files.wordpress.com/2018/07/medical-breakthroughs-you-just-might-see-in-your-lifetime-20-photos-6.jpg?quality=85&strip=info&w=600", userID = Calendar.getInstance().time.time))

    private fun getPair(name: String, surname: String, picLink: String = "", userID: Long): Pair<Long, UserData> {
        val user = UserData(name, surname, picLink, userID)
        Thread.sleep(1)
        return Pair(user.userID, user)
    }
}