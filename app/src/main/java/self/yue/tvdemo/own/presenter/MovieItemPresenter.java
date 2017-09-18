package self.yue.tvdemo.own.presenter;

import android.content.Context;
import android.support.v17.leanback.widget.Presenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import self.yue.tvdemo.R;
import self.yue.tvdemo.basic.Movie;

/**
 * Created by dong on 9/18/17.
 */

public class MovieItemPresenter extends Presenter {
  private Context mContext;

  public MovieItemPresenter(Context context) {
    mContext = context;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent) {
    return new ViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_movie, parent, false));
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, Object item) {
    Movie movie = (Movie) item;
    ItemHolder holder = new ItemHolder(viewHolder.view);
    holder.tvTitle.setText(movie.getTitle());
    Glide.with(mContext)
            .load(movie.getBackgroundImageUrl())
            .centerCrop()
            .into(holder.ivCover);
  }

  @Override
  public void onUnbindViewHolder(ViewHolder viewHolder) {

  }

  class ItemHolder extends ViewHolder {
    public ImageView ivCover;
    public TextView tvTitle;

    public ItemHolder(View itemView) {
      super(itemView);
      ivCover = itemView.findViewById(R.id.iv_cover);
      tvTitle = itemView.findViewById(R.id.tv_title);
    }
  }
}
