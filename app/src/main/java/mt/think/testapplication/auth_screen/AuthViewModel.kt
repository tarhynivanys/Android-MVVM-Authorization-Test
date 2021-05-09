package mt.think.testapplication.auth_screen

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mt.think.testapplication.model.Repository
import mt.think.testapplication.model.UserDataClass

class AuthViewModel : ViewModel() {

    private var _userData = MutableLiveData<UserDataClass>()
    val userData: LiveData<UserDataClass>
        get() = _userData

    //@Bindable
    val emailBindable = MutableLiveData<String>()

    //@Bindable
    val passwordBindable = MutableLiveData<String>()

    val tempBoolean = MutableLiveData<Boolean>()

    init {

    }

    fun setModel(data: UserDataClass) {
        _userData.value = data
        Repository.accounts.add(data)
    }

    fun performLogin(data: UserDataClass) {

        _userData.value = data

        tempBoolean.value = false

        Repository.accounts.forEach { account ->
            if (account.login == data.login
                && account.password == data.password
            ) {
                Log.e("LALA", "${data.login}\n${data.password}")
                tempBoolean.value = true
            }

        }

    }


}