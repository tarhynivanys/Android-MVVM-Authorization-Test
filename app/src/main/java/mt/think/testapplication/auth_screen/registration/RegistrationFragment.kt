package mt.think.testapplication.auth_screen.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import mt.think.testapplication.MainActivity
import mt.think.testapplication.R
import mt.think.testapplication.auth_screen.AuthViewModel
import mt.think.testapplication.databinding.RegistrationFragmentBinding
import mt.think.testapplication.model.TextInputLayoutToEditText
import mt.think.testapplication.model.UserDataClass

class RegistrationFragment : Fragment() {

    private lateinit var binding: RegistrationFragmentBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.registration_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)

        binding.registrationViewModel = viewModel

        binding.fragmentRegistrationSubmitButton.setOnClickListener {
            if (validateFields()){
                //binding.registrationViewModel = viewModel
                viewModel.setModel(
                    UserDataClass(
                        login = binding.fragmentRegistrationEmail.text.toString(),
                        password = binding.fragmentRegistrationPassword.text.toString(),
                        firstName = binding.fragmentRegistrationFirstName.text.toString(),
                        lastName = binding.fragmentRegistrationLastName.text.toString()
                    )
                )

                (activity as MainActivity).viewPager.currentItem = 0
            }
        }

        initViewsToRemoveErrorsAfterTextChanged()

        return binding.root
    }

    private fun validateFields(): Boolean {
        var areFieldsValid = true

        if (binding.fragmentRegistrationFirstName.text.toString().isEmpty()
        ) {
            binding.fragmentRegistrationFirstNameLayout.error = "This field is required"
            areFieldsValid = false
        }

        if (binding.fragmentRegistrationLastName.text.toString().isEmpty()
        ) {
            binding.fragmentRegistrationLastNameLayout.error = "This field is required"
            areFieldsValid = false
        }

        if (binding.fragmentRegistrationEmail.text.toString().contains("@").not()
        ) {
            binding.fragmentRegistrationEmailLayout.error = "Email Invalid"
            areFieldsValid = false
        }

        if (binding.fragmentRegistrationEmail.text.toString().isEmpty()
        ) {
            binding.fragmentRegistrationEmail.error = "This field is required"
            areFieldsValid = false
        }

        if (binding.fragmentRegistrationPassword.text.toString().isEmpty()
        ) {
            binding.fragmentRegistrationPasswordLayout.error = "This field is required"
            areFieldsValid = false
        }

        if (binding.fragmentRegistrationPassword.text.toString()
            != binding.fragmentRegistrationConfirmPassword.text.toString()
        ) {
            binding.fragmentRegistrationPasswordLayout.error = "Passwords don't match"
            binding.fragmentRegistrationConfirmPasswordLayout.error = "Passwords don't match"
            areFieldsValid = false
        }

        return areFieldsValid
    }

    fun initViewsToRemoveErrorsAfterTextChanged() {
        val listOfFields = arrayListOf(
            TextInputLayoutToEditText(
                editText = binding.fragmentRegistrationFirstName,
                textInputLayout = binding.fragmentRegistrationFirstNameLayout
            ),
            TextInputLayoutToEditText(
                editText = binding.fragmentRegistrationLastName,
                textInputLayout = binding.fragmentRegistrationLastNameLayout
            ),
            TextInputLayoutToEditText(
                editText = binding.fragmentRegistrationEmail,
                textInputLayout = binding.fragmentRegistrationEmailLayout
            ),
            TextInputLayoutToEditText(
                editText = binding.fragmentRegistrationPassword,
                textInputLayout = binding.fragmentRegistrationPasswordLayout
            ),
            TextInputLayoutToEditText(
                editText = binding.fragmentRegistrationConfirmPassword,
                textInputLayout = binding.fragmentRegistrationConfirmPasswordLayout
            )
        )

        mt.think.testapplication.utils.initViewsToRemoveErrorsAfterTextChanged(listOfFields)
    }

}