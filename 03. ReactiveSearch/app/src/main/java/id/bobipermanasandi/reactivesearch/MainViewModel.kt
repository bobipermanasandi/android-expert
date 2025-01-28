package id.bobipermanasandi.reactivesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.bobipermanasandi.reactivesearch.network.ApiConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel : ViewModel() {

    private val accessToken = "pk.eyJ1IjoiZGV2a290ZXMiLCJhIjoiY200Y3A5YmtlMGFuNTJyb21xZmRocWRmdiJ9.R-FTotmAoYSq5mv7uFABFw"
    val queryChannel = MutableStateFlow("")

    val searchResult = queryChannel
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            ApiConfig.provideApiService().getCountry(it, accessToken).features
        }
        .asLiveData()
}