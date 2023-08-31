package cl.samf.superheroescomics.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cl.samf.superheroescomics.data.Repository
import cl.samf.superheroescomics.data.local.HeroeDataBase
import cl.samf.superheroescomics.data.remote.HeroeRetrofit
import kotlinx.coroutines.launch

class HeroeViewModel (application: Application): AndroidViewModel(application) {

    private val repository : Repository

    fun heroeLiveData() = repository.getHeroe()

    fun heroeIdLiveData (id: Int) = repository.getDetailsIdHeroe(id)


    init {
        val heroeApi = HeroeRetrofit.getRetrofitClient()
        val heroeDataBase = HeroeDataBase.getDataBase(application).getHeroeDao()
        repository = Repository(heroeApi, heroeDataBase)
    }
    fun getHeroe() = viewModelScope.launch {
        repository.loadHeroe()
    }

    fun getDetailsHeroe(id: Int) = viewModelScope.launch {
        repository.detailsHeroe(id)
    }

}
