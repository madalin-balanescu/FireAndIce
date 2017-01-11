package com.bma.codingchallange.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bma.codingchallange.R;
import com.bma.codingchallange.model.Book;
import com.bma.codingchallange.model.Characters;
import com.bma.codingchallange.model.TitlesObject;
import com.bma.codingchallange.network.ServerConstants;
import com.bma.codingchallange.ui.activities.MainActivity;
import com.bma.codingchallange.ui.adapters.BookAdapter;
import com.bma.codingchallange.utils.EndlessRecyclerViewScrollListener;
import com.bma.codingchallange.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

public class CharactersFragment extends Fragment {

    private final static int rowNumber = 2;

    @BindView(R.id.rvCharacters)
    RecyclerView rvCharacters;

    MainActivity mainActivity;

    private final static int distance = 20;

    private String mCharacterId = "";

    private static String CHARACTER_ID = "characterId";

    private Book currentBook;
    private int index = 10;

    private int sequenceSize = 10;

    public List<Characters> currentCharactersElements;

    private boolean isLastElement = false;

    public static CharactersFragment newInstance(String characterId) {
        CharactersFragment mFragment = new CharactersFragment();
        Bundle args = new Bundle();
        args.putString(CHARACTER_ID, characterId);
        mFragment.setArguments(args);
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_characters, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainActivity = (MainActivity) getActivity();

        setUI();
    }

    public void notifyAdapter(){
        rvCharacters.getAdapter().notifyDataSetChanged();
    }

    public void updateTheTitles(List<Characters> listOfCharacters){

        for (Characters character : listOfCharacters) {
            for (Characters item : currentCharactersElements) {
                if (item.getUrl().equals(character.getUrl())) {

                    if (character.getTitles() != null && character.getTitles().size() > 0) {
                        item.setTitles(character.getTitles());
                    } else {
                        RealmList<TitlesObject> emptyList = new RealmList<TitlesObject>();
                        emptyList.add(new TitlesObject(getString(R.string.empty_title)));
                        item.setTitles(emptyList);
                    }
                }

            }
        }

        mainActivity.realmHelper.saveList(currentCharactersElements);
    }

    private void setUI() {
        if (getArguments() != null) {

            rvCharacters.addItemDecoration(new SpacesItemDecoration(distance));

            mCharacterId = getArguments().getString(CHARACTER_ID, "");

            currentBook = mainActivity.realmHelper.getBookByName(mCharacterId);

            if (currentBook != null && currentBook.getCharacters() != null && currentBook.getCharacters().size() > 0) {

                getElementSequence();

                setAdapter(currentCharactersElements);
            }
        }
    }


    private void getElementSequence() {

        if (!isLastElement) {

            List<Observable<Characters>> apiCalls = new ArrayList<>();

            int start = 0;

            if (index == sequenceSize) {
                currentCharactersElements = new ArrayList<>();
                isLastElement = false;
            } else {
                start = index - sequenceSize;
            }

            for (int i = start; i < index; i++) {
                if (i < currentBook.getCharacters().size()) {
                    currentCharactersElements.add(mainActivity.realmHelper.getRealmInstance().copyFromRealm(currentBook.getCharacters().get(i)));
                    String url = currentBook.getCharacters().get(i).getUrl();
                    url = url.replace(ServerConstants.BASE_URL + ServerConstants.CHARACTERS, "");
                    apiCalls.add(mainActivity.networkManager.getCharacters(Integer.valueOf(url)));

                } else {
                    isLastElement = true;
                }
            }

            index = index + sequenceSize;

            mainActivity.mainPresenter.getTitleForCharacters(apiCalls);
        }


    }

    private void setAdapter(List<Characters> mCharacterList) {

        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(rowNumber,
                StaggeredGridLayoutManager.VERTICAL);

        rvCharacters.setLayoutManager(mLayoutManager);

        rvCharacters.setAdapter(new BookAdapter(mCharacterList, getActivity()));

        rvCharacters.addOnScrollListener(new EndlessRecyclerViewScrollListener((StaggeredGridLayoutManager)
                rvCharacters.getLayoutManager(), sequenceSize) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {

                getElementSequence();
                rvCharacters.getAdapter().notifyDataSetChanged();

            }
        });

    }




}