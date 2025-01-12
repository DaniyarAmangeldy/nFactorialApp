package kz.nfactorial.nfactorialapp.extensions

import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

@MainThread
inline fun <reified VM : ViewModel> Fragment.viewModels(
    noinline viewModelInitializer: () -> VM
): Lazy<VM> {
    val owner by lazy(LazyThreadSafetyMode.NONE) { this }
    return createViewModelLazy(
        viewModelClass = VM::class,
        storeProducer = { owner.viewModelStore },
        extrasProducer = { CreationExtras.Empty },
        factoryProducer = {
            viewModelFactory {
                initializer { viewModelInitializer() }
            }
        }
    )
}

@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.viewModels(
    noinline creator: () -> VM
): ViewModelLazy<VM> {
    return ViewModelLazy(
        viewModelClass = VM::class,
        storeProducer = { viewModelStore },
        factoryProducer = { viewModelFactory { initializer { creator() } } }
    )
}