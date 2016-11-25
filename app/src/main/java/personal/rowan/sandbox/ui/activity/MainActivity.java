package personal.rowan.sandbox.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.model.PokemonList;
import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.ui.adapter.PokemonListAdapter;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity
        extends BaseActivity {

    @Inject
    Retrofit mRetrofit;

    private PokemonListAdapter mAdapter;
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkComponent().inject(this);

        setViews();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }

    private void setViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_tb);
        setToolbar(toolbar, "Pokemon List");

        RecyclerView pokemonList = (RecyclerView) findViewById(R.id.activity_main_rv);
        pokemonList.setLayoutManager(new LinearLayoutManager(this));
        pokemonList.setAdapter(mAdapter = new PokemonListAdapter());
    }

    private void loadData() {
        showProgressDialog("Loading Pokemon", "This will only take a moment.");
        PokemonService pokemonService = mRetrofit.create(PokemonService.class);
        Observable<PokemonList> pokemon = pokemonService.getAllPokemon();
        mSubscription = pokemon.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PokemonList>() {
                    @Override
                    public void onCompleted() {
                        dismissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
                        showToastMessage("An error occurred.");
                    }

                    @Override
                    public void onNext(PokemonList pokemonList) {
                        mAdapter.setData(pokemonList.getResults());
                    }
                });
    }

    private void unsubscribe() {
        if(mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mSubscription = null;
    }

}
