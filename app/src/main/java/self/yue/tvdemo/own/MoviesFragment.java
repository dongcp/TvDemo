package self.yue.tvdemo.own;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.VerticalGridFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.VerticalGridPresenter;

import java.util.Collections;
import java.util.List;

import self.yue.tvdemo.basic.Movie;
import self.yue.tvdemo.basic.MovieList;
import self.yue.tvdemo.own.presenter.MovieItemPresenter;

/**
 * Created by dong on 9/18/17.
 */

public class MoviesFragment extends VerticalGridFragment {
  private static final int NUM_COLS = 5;
  private static final int NUM_ROWS = 20;

  private ArrayObjectAdapter mMoviesAdapter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    VerticalGridPresenter verticalGridPresenter = new VerticalGridPresenter();
    verticalGridPresenter.setNumberOfColumns(NUM_COLS);
    setGridPresenter(verticalGridPresenter);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    mMoviesAdapter = new ArrayObjectAdapter(new MovieItemPresenter(getActivity()));
    setAdapter(mMoviesAdapter);
    loadRows();
  }

  private void loadRows() {
    List<Movie> list = MovieList.setupMovies();

    int i;
    for (i = 0; i < NUM_ROWS; i++) {
      if (i != 0) {
        Collections.shuffle(list);
      }
      for (int j = 0; j < NUM_COLS; j++) {
        mMoviesAdapter.add(list.get(j % 5));
      }
    }

    setOnItemViewClickedListener(new OnItemViewClickedListener() {
      @Override
      public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {

      }
    });

    setOnItemViewSelectedListener(new OnItemViewSelectedListener() {
      @Override
      public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {

      }
    });
  }
}
