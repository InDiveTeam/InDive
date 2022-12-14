package com.ssafy.indive.view.login.join

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ssafy.indive.R
import com.ssafy.indive.base.BaseFragment
import com.ssafy.indive.databinding.FragmentJoinBinding
import com.ssafy.indive.model.dto.MemberJoin
import com.ssafy.indive.view.login.MemberViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


private const val TAG = "JoinFragment"

@AndroidEntryPoint
class JoinFragment : BaseFragment<FragmentJoinBinding>(R.layout.fragment_join) {

    private val memberViewModel: MemberViewModel by activityViewModels()
    private lateinit var key: String
    private var isEmailCheck: Boolean = false
    private var isPassCheck: Boolean = false
    private var isEmailCodeCheck: Boolean = false

    override fun init() {
        binding.apply {
            memberVM = memberViewModel
            etEmail.addTextChangedListener(textChangeListener)
            etPassCheck.addTextChangedListener(passChangeListener)
        }
        initClickListener()
        initViewModelCallback()
    }

    private fun initViewModelCallback() {

        memberViewModel.emailCheck.observe(viewLifecycleOwner) {
            isEmailCheck = false
            if (it == null) {
                binding.tvEmailCheck.visibility = View.GONE
            } else {
                if (it) {
                    binding.tvEmailCheck.visibility = View.VISIBLE
                } else {
                    binding.tvEmailCheck.visibility = View.GONE
                    isEmailCheck = true
                }
            }
        }
    }

    private fun initClickListener() {
        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            btnEmail.setOnClickListener {
                if (isEmailCheck) {
                    key = createKey()

                    // test???
                    etEmailCode.setText(key)


//                    val email = etEmail.text.toString()
//                    if (isEmail(email)) {
//                        GMailSender().sendEmail(email, key)
//                        etEmailCode.setText(key)
//                        Toast.makeText(context, "???????????? ??????????????? ???????????????.", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(context, "????????? ????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
//                    }
                } else {
                    Toast.makeText(context, "????????? ????????? ??????????????????.", Toast.LENGTH_SHORT).show()
                }

            }

            btnEmailCode.setOnClickListener {
                if (etEmailCode.text.toString() == key) {
                    Toast.makeText(context, "????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
                    isEmailCodeCheck = true
                    etEmailCode.isEnabled = false
                    etEmail.isEnabled = false
                } else {
                    Toast.makeText(context, "??????????????? ??????????????????.", Toast.LENGTH_SHORT).show()
                }
            }



            btnJoin.setOnClickListener {
                if (isEmailCheck && isEmailCodeCheck && isPassCheck) {
                    findNavController().navigate(R.id.action_joinFragment_to_walletFragment)
                } else {
                    Toast.makeText(context, "??????????????? ??????????????????.", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private val textChangeListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            memberViewModel.memberEmailCheck(binding.etEmail.text.toString())
        }
    }

    private val passChangeListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        @SuppressLint("ResourceType")
        override fun afterTextChanged(p0: Editable?) {
            binding.tvPassMsg.visibility = View.VISIBLE
            if (binding.etPass.text.toString() == binding.etPassCheck.text.toString()) {
                binding.apply {
                    tvPassMsg.text = "???????????? ??????"
                    tvPassMsg.setTextColor(ContextCompat.getColor(context!!, R.color.main_blue))
                    isPassCheck = true;
                }
            } else {
                binding.apply {
                    tvPassMsg.text = "???????????? ?????????"
                    tvPassMsg.setTextColor(ContextCompat.getColor(context!!, R.color.red))
                    isPassCheck = false
                }
            }
        }
    }

    private fun isEmail(email: String?): Boolean {
        var returnValue = false
        val regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$"
        val p: Pattern = Pattern.compile(regex)
        val m: Matcher = p.matcher(email)
        if (m.matches()) {
            returnValue = true
        }
        return returnValue
    }

    private fun createKey(): String {
        // ?????? ?????? ??????
        val key = StringBuffer()
        val rnd = Random()

        for (i in 0..7) { // ???????????? 8??????
            val index = rnd.nextInt(3) // 0~2 ?????? ??????
            when (index) {
                0 -> key.append((rnd.nextInt(26) + 97).toChar())
                1 -> key.append((rnd.nextInt(26) + 65).toChar())
                2 -> key.append(rnd.nextInt(10))
            }
        }
        return key.toString()
    }
}