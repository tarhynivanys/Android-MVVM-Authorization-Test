package mt.think.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import biz.laenger.android.vpbs.BottomSheetUtils
import kotlinx.android.synthetic.main.activity_main.*
import mt.think.testapplication.auth_screen.AuthViewModel
import mt.think.testapplication.auth_screen.login.LoginFragment
import mt.think.testapplication.auth_screen.registration.RegistrationFragment
import mt.think.testapplication.auth_screen.view_pager.MyFragmentPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MyFragmentPagerAdapter<Fragment>(fragmentManager = supportFragmentManager)
        adapter.addFragment(LoginFragment())
        adapter.addFragment(RegistrationFragment())
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        BottomSheetUtils.setupViewPager(viewPager)
        tabLayout.getTabAt(0)?.text = "Login"
        tabLayout.getTabAt(1)?.text = "Registration"

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        viewPager.currentItem = 1

    }
}