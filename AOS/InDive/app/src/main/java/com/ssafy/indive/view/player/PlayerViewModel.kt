package com.ssafy.indive.view.player

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.indive.model.response.ReplyResponse
import com.ssafy.indive.repository.MusicManagerRepository
import com.ssafy.indive.utils.Result
import com.ssafy.indive.utils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(private val musicManagerRepository: MusicManagerRepository) :
    ViewModel() {

    private val _likeSuccess = MutableStateFlow(false)
    val likeSuccess get() = _likeSuccess

    private val _deleteLikeSuccess = MutableStateFlow(false)
    val deleteLikeSuccess get() = _deleteLikeSuccess

    private val _replyListCnt = MutableStateFlow("")
    val replyListCnt get() = _replyListCnt.asStateFlow()

    private val _isLike = MutableStateFlow(false)
    val isLike get() = _isLike.asStateFlow()

    private val _likeCnt = MutableStateFlow("0")
    val likeCnt get() = _likeCnt.asStateFlow()


    fun likeMusic(musicSeq: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            musicManagerRepository.likeMusic(musicSeq).collectLatest {
                if (it is Result.Success) {
                    Log.d(TAG, "likeMusic: ${it.data.body()!!}")
                    _likeSuccess.value = it.data.body()!!
                    _isLike.value = true
                } else if (it is Result.Error) {
                    Log.d(TAG, "likeMusic: ${it.exception}")
                }
            }

        }
    }

    fun deleteLike(musicSeq: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            musicManagerRepository.deleteLike(musicSeq).collectLatest {
                if (it is Result.Success) {
                    Log.d(TAG, "deleteLike: ${it.data.body()!!}")
                    _deleteLikeSuccess.value = it.data.body()!!
                    _isLike.value = false
                } else if (it is Result.Error) {
                    Log.d(TAG, "deleteLike: ${it.exception}")
                }
            }

        }
    }


    fun getMusicReply(musicSeq: Long) {

        viewModelScope.launch(Dispatchers.IO) {
            musicManagerRepository.getMusicReply(musicSeq).collectLatest {
                if (it is Result.Success) {
                    Log.d(TAG, "getMusicReplyError: ${it.data.size}")
                    _replyListCnt.value = it.data.size.toString()

                } else if (it is Result.Error) {
                    Log.d(TAG, "getMusicReplyError: ${it.exception}")
                }
            }

        }
    }

    fun isLike(musicSeq: Long) {

        viewModelScope.launch(Dispatchers.IO) {
            musicManagerRepository.isLike(musicSeq).collectLatest {
                if (it is Result.Success) {
                    Log.d(TAG, "isLike: ${it.data.body()!!}")
                    _isLike.value = it.data.body()!!
                } else if (it is Result.Error) {
                    Log.d(TAG, "isLike: ${it.exception}")
                }
            }

        }
    }

    fun getLikeCnt(musicSeq: Long) {

        viewModelScope.launch(Dispatchers.IO) {
            musicManagerRepository.getLikeCount(musicSeq).collectLatest {
                if (it is Result.Success) {
                    Log.d(TAG, "likeCnt: ${it.data.body()!!}")
                    _likeCnt.value = it.data.body()!!.toString()
                } else if (it is Result.Error) {
                    Log.d(TAG, "likeCnt: ${it.exception}")
                }
            }

        }
    }

}