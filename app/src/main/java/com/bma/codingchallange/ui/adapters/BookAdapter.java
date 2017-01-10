package com.bma.codingchallange.ui.adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bma.codingchallange.R;
import com.bma.codingchallange.model.Book;
import com.bma.codingchallange.model.Characters;
import com.bma.codingchallange.ui.activities.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

public class BookAdapter<T extends Object> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> mListBooks;
    private ViewGroup mViewGroup;

    FragmentActivity mActivity;

    public BookAdapter(List<T> mListBooks, FragmentActivity activity) {
        this.mListBooks = mListBooks;
        this.mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_item, parent, false);
        mViewGroup = parent;
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (mListBooks.get(position) instanceof Book) {

            Book item = (Book) mListBooks.get(position);

            if (!TextUtils.isEmpty(item.getName())) {
                ((ViewHolder) holder).tvBookTitle.setText(item.getName());
            }


            ((ViewHolder) holder).tvBookNr.setText(mViewGroup.getContext().getString(R.string.book_string) + " " + position);

            ((ViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mActivity != null && mActivity instanceof MainActivity) {
                        ((MainActivity) mActivity).fragmentManager.displayCharacters(item.getName());
                    }

                }
            });
        } else if (mListBooks.get(position) instanceof Characters) {
            Characters item = (Characters) mListBooks.get(position);

            if (item.getTitles() != null && item.getTitles().size() > 0 && !TextUtils.isEmpty(item.getTitles().get(0).getTitle())) {
                ((ViewHolder) holder).tvBookTitle.setText(item.getTitles().get(0).getTitle());
            } else if (!TextUtils.isEmpty(item.getUrl())) {
                ((ViewHolder) holder).tvBookTitle.setText(item.getUrl());
            }

            ((ViewHolder) holder).tvBookNr.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if (mListBooks == null) {
            return 0;
        } else {
            return mListBooks.size();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvBookTitle)
        TextView tvBookTitle;
        @BindView(R.id.tvBookNr)
        TextView tvBookNr;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
