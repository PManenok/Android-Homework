package by.itacademy.palina.task.classwork.cw4

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import by.itacademy.palina.task.R

class CW4 : AppCompatActivity() {
    //ViewPager
    //ViewPagerAdapter setup viewpager
    //TabLayout

    private var isFirstFragment = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cw4)

        val textView = findViewById<TextView>(R.id.cw4titleTextView)
        textView.setOnClickListener {
            changeFragment()
        }
        changeFragment()
    }

    private fun changeFragment() {
        val fragmentManager = supportFragmentManager
        val transactionFragment = fragmentManager.beginTransaction()
        //fragmentManager.findFragmentByTag("OneFragment").isDetached
        if (isFirstFragment) {
            transactionFragment.replace(R.id.cw4container, TwoFragment.getInstance("Hello"), "TwoFragment")
            isFirstFragment = false
        } else {
            transactionFragment.replace(R.id.cw4container, OneFragment.getInstance(), "OneFragment")
            isFirstFragment = true
        }
        transactionFragment.addToBackStack(null)
        transactionFragment.commit()
    }
}