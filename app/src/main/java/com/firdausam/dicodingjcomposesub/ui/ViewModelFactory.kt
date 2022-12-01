package com.firdausam.dicodingjcomposesub.ui

import android.content.Context
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.firdausam.dicodingjcomposesub.di.Injection
import com.firdausam.dicodingjcomposesub.domain.repository.AnimeRepository
import com.firdausam.dicodingjcomposesub.ui.screen.detail.DetailViewModel
import com.firdausam.dicodingjcomposesub.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: AnimeRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModelClass: ${modelClass.name} isn't assignable")
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            val mBundle = bundleOf("id" to extras[KeyId])
            return DetailViewModel(repository, SavedStateHandle.createHandle(null, mBundle)) as T
        }
        return create(modelClass)
    }

    companion object {
        fun getInstance(context: Context): ViewModelFactory =
            ViewModelFactory(Injection.provideRepository(context))
    }

    object KeyId : CreationExtras.Key<Int>
}