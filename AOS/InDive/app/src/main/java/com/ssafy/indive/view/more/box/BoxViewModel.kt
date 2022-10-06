package com.ssafy.indive.view.more.box

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BoxViewModel @Inject constructor(

) : ViewModel(){

    private val _nftList : MutableStateFlow<List<String>> = MutableStateFlow(listOf())
    val nftList get() = _nftList.asStateFlow()



}