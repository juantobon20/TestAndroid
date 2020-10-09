package com.appinc.cocoshop.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appinc.cocoshop.models.Login
import com.appinc.cocoshop.rest.LoginRest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentUserVM : ViewModel() {

    private val listData = MutableLiveData<List<Login>>()
    private val isBusy = MutableLiveData<Boolean>()
    private val loginRest = LoginRest()

    private var logins: List<Login>? = null

    init {
        this.isBusy.value = true
        this.GetAsyncAll()

    }

    fun GetAsyncAll() {
        viewModelScope.launch {
            logins = withContext(Dispatchers.IO) {
                loginRest.Get()
            }
            isBusy.value = false
            listData.value = logins
        }
    }

    fun GetLoginsLiveData(): LiveData<List<Login>> = this.listData
    fun GetIsBusy(): LiveData<Boolean> = this.isBusy
}