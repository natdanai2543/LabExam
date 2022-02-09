package th.ac.kku.nkc.cis.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import th.ac.kku.nkc.cis.pokedex.data.api.APIInterface
import th.ac.kku.nkc.cis.pokedex.data.repository.PokemonRepo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recycleViewAdapter = RecycleViewAdapter()
        var recyclerView = findViewById<RecyclerView>(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recycleViewAdapter

        val retrofitService = APIInterface.create()
        val pokemonRepo = PokemonRepo(retrofitService)
        var viewModel = ViewModelProvider(this,
        MainActivityViewModelFacttory(pokemonRepo))
            .get(MainActivityViewModel::class.java)

        /*
        viewModel.pokemonList.observe(this,{
            recycleViewAdapter.setPokemonListItem(it)
        })

        */
        viewModel.pokemonListItem.observe(this,{
            recycleViewAdapter.setPokemonList(it)
        })
        viewModel.loading.observe(this,{
            val pgBar = findViewById<ProgressBar>(R.id.progressBar)
            pgBar.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.loadPokemonData()

        /*
        //get data from api
        val apiInterface = APIInterface.create().getPokemons()

        apiInterface.enqueue(object : Callback<PokemonAPIResult>{
            override fun onResponse(
                call: Call<PokemonAPIResult>,
                response: Response<PokemonAPIResult>
            ) {
                if(response?.body() != null){
                    recycleViewAdapter.setPokemonList(response.body()!!)

                }
            }

            override fun onFailure(call: Call<PokemonAPIResult>, t: Throwable) {

            }
            
        })

         */
    }
}
