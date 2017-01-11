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
import com.bma.codingchallange.log.LPLog;
import com.bma.codingchallange.model.Book;
import com.bma.codingchallange.ui.activities.MainActivity;
import com.bma.codingchallange.ui.adapters.BookAdapter;
import com.bma.codingchallange.utils.ConnectionCheck;
import com.bma.codingchallange.utils.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

public class BooksFragment extends Fragment {

    @BindView(R.id.rvBooks)
    RecyclerView rvBooks;

    MainActivity mainActivity;

    private final static int distance = 20;
    private final static int rowNumber = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_book, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainActivity = (MainActivity) getActivity();

        setUI();
    }

    private void setUI() {

        rvBooks.addItemDecoration(new SpacesItemDecoration(distance));

        List<Book> mAlbumList = mainActivity.realmHelper.getBooks();

        if (mAlbumList != null && mAlbumList.size() > 0) {
            setAdapter(mAlbumList);
        }

        if (ConnectionCheck.isConnectionAvailable(getActivity())) {
            mainActivity.mainPresenter.getBooks();
        }

    }




    public void setAdapter(List<Book> books) {

        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(rowNumber,
                StaggeredGridLayoutManager.VERTICAL);

        rvBooks.setLayoutManager(mLayoutManager);

        rvBooks.setAdapter(new BookAdapter(books, getActivity()));

    }

}
