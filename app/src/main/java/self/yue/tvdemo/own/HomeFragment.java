package self.yue.tvdemo.own;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.RowsFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v4.app.ActivityOptionsCompat;

import java.util.Collections;
import java.util.List;

import self.yue.tvdemo.R;
import self.yue.tvdemo.basic.CardPresenter;
import self.yue.tvdemo.basic.DetailsActivity;
import self.yue.tvdemo.basic.Movie;
import self.yue.tvdemo.basic.MovieList;
import self.yue.tvdemo.own.presenter.GridItemPresenter;

/**
 * Created by dong on 17/09/2017.
 */

public class HomeFragment extends RowsFragment {
  private static final int NUM_ROWS = 6;
  private static final int NUM_COLS = 15;

  private ArrayObjectAdapter mRowsAdapter;

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    loadRows();
  }

  private void loadRows() {
    List<Movie> list = MovieList.setupMovies();

    mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
    CardPresenter cardPresenter = new CardPresenter();

    int i;
    for (i = 0; i < NUM_ROWS; i++) {
      if (i != 0) {
        Collections.shuffle(list);
      }
      ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(cardPresenter);
      for (int j = 0; j < NUM_COLS; j++) {
        listRowAdapter.add(list.get(j % 5));
      }
      HeaderItem header = new HeaderItem(i, MovieList.MOVIE_CATEGORY[i]);
      mRowsAdapter.add(new ListRow(header, listRowAdapter));
    }

    HeaderItem gridHeader = new HeaderItem(i, "PREFERENCES");

    GridItemPresenter mGridPresenter = new GridItemPresenter(getActivity());
    ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);
    gridRowAdapter.add(getString(R.string.vertical_grid_fragment));
    mRowsAdapter.add(new ListRow(gridHeader, gridRowAdapter));

    setAdapter(mRowsAdapter);

    setOnItemViewClickedListener(new OnItemViewClickedListener() {
      @Override
      public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        if (item instanceof Movie) {
          Movie movie = (Movie) item;
          Intent intent = new Intent(getActivity(), DetailsActivity.class);
          intent.putExtra(DetailsActivity.MOVIE, movie);

          Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                  getActivity(),
                  ((ImageCardView) itemViewHolder.view).getMainImageView(),
                  DetailsActivity.SHARED_ELEMENT_NAME).toBundle();
          getActivity().startActivity(intent, bundle);
        } else if (item instanceof String) {
          String title = (String) item;

          if (title.equals(getString(R.string.vertical_grid_fragment))) {
            startActivity(new Intent(getActivity(), VerticalGridActivity.class));
          }
        }
      }
    });
  }
}
