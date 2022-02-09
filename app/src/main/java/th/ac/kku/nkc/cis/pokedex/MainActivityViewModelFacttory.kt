package th.ac.kku.nkc.cis.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import th.ac.kku.nkc.cis.pokedex.data.repository.PokemonRepo

class MainActivityViewModelFacttory constructor(private val repo: PokemonRepo)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            MainActivityViewModel(this.repo) as T
        }else {
            throw IllegalAccessException("ViewModel Not Found")
        }
    }

}