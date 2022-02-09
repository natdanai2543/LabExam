package th.ac.kku.nkc.cis.pokedex.data.api.model

data class PokemonAPIResult(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)