# CryptoCurrencyApp
# CryptoCurrencyApp for fetching CryptoCurrency data from an end-point, https://api.coinmarketcap.com/v1/ticker/?limit=50; and displaying the data on the UI, using a RecyclerView.
# Uses RoomDB for local caching, and depending on network state, fetches data either from the above api or from the local storage.
# Without internet connection, it shows the cached data, displaying a snackbar at the bottom with an option to retry loading the data
# Libraries used:
- Paging library for feeding data into the RecyclerView.
- Retrofit for networking 
- Architecture components
- Other Android Jetpack components

<img src="images/Screenshot_20200303-133933_CryptoCurrencyApp.jpg" width="250" >
<img src="images/Screenshot_20200303-133956_CryptoCurrencyApp.jpg" width="250" >
<img src="images/Screenshot_20200303-134007_CryptoCurrencyApp.jpg" width="250" >
<img src="images/Screenshot_20200303-134633_CryptoCurrencyApp.jpg" width="250" >
