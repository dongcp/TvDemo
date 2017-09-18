package self.yue.tvdemo.own.presenter;

import android.content.Context;
import android.graphics.Color;
import android.support.v17.leanback.widget.Presenter;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import self.yue.tvdemo.R;

/**
 * Created by dong on 9/18/17.
 */

public class GridItemPresenter extends Presenter {
  private static final int GRID_ITEM_WIDTH = 200;
  private static final int GRID_ITEM_HEIGHT = 200;

  private Context mContext;

  public GridItemPresenter(Context context) {
    mContext = context;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent) {
    TextView view = new TextView(parent.getContext());
    view.setLayoutParams(new ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT));
    view.setFocusable(true);
    view.setFocusableInTouchMode(true);
    view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.default_background));
    view.setTextColor(Color.WHITE);
    view.setGravity(Gravity.CENTER);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, Object item) {
    ((TextView) viewHolder.view).setText((String) item);
  }

  @Override
  public void onUnbindViewHolder(ViewHolder viewHolder) {
  }
}
