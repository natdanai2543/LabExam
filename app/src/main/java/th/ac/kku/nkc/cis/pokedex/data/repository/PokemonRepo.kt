package th.ac.kku.nkc.cis.pokedex.data.repository

import th.ac.kku.nkc.cis.pokedex.data.api.APIInterface

class PokemonRepo constructor(private val  apiInterface: APIInterface) {
    suspend fun getPokemonList() = apiInterface.getPokemons()

}