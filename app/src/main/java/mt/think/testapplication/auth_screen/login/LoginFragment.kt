package mt.think.testapplication.auth_screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import mt.think.testapplication.MainActivity
import mt.think.testapplication.R
import mt.think.testapplication.auth_screen.AuthViewModel
import mt.think.testapplication.databinding.LoginFragmentBinding
import mt.think.testapplication.model.TextInputLayoutToEditText
import mt.think.testapplication.model.UserDataClass
import mt.think.testapplication.utils.initViewsToRemoveErrorsAfterTextChanged

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)

        binding.fragmentLoginConnectButton.setOnClickListener {

            if (validateFields()){
                viewModel.emailBindable.value = binding.fragmentLoginEmail.text.toString()
                viewModel.performLogin(
                    UserDataClass(
                        login = binding.fragmentLoginEmail.text.toString(),
                        password = binding.fragmentLoginPassword.text.toString(),
                        firstName = null,
                        lastName = null
                    )
                )
                binding.loginViewModel = viewModel
            }

        }

        viewModel.tempBoolean.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, "Success!", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context, "Fail!", Toast.LENGTH_LONG).show()
            }
        })

        initViewsToRemoveErrorsAfterTextChanged()

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.loginViewModel = viewModel

    }

    private fun validateFields(): Boolean {
        var areFieldsValid = true

        if (binding.fragmentLoginEmail.text.toString().contains("@").not()
        ) {
            binding.fragmentLoginEmailLayout.error = "Email Invalid"
            areFieldsValid = false
        }

        if (binding.fragmentLoginEmail.text.toString().isEmpty()
        ) {
            binding.fragmentLoginEmailLayout.error = "This field is required"
            areFieldsValid = false
        }

        if (binding.fragmentLoginPassword.text.toString().isEmpty()
        ) {
            binding.fragmentLoginPasswordLayout.error = "This field is required"
            areFieldsValid = false
        }

        return areFieldsValid
    }

    fun initViewsToRemoveErrorsAfterTextChanged() {
        val listOfFields = arrayListOf(
            TextInputLayoutToEditText(
                editText = binding.fragmentLoginEmail,
                textInputLayout = binding.fragmentLoginEmailLayout
            ),
            TextInputLayoutToEditText(
                editText = binding.fragmentLoginPassword,
                textInputLayout = binding.fragmentLoginPasswordLayout
            )
        )

        initViewsToRemoveErrorsAfterTextChanged(listOfFields)
    }

}