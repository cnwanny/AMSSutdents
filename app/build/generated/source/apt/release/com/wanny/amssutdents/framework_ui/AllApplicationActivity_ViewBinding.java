// Generated code from Butter Knife. Do not modify!
package com.wanny.amssutdents.framework_ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wanny.amssutdents.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AllApplicationActivity_ViewBinding<T extends AllApplicationActivity> implements Unbinder {
  protected T target;

  private View view2131230840;

  @UiThread
  public AllApplicationActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mySchool = Utils.findRequiredViewAsType(source, R.id.my_school, "field 'mySchool'", TextView.class);
    target.myRank = Utils.findRequiredViewAsType(source, R.id.my_rank, "field 'myRank'", TextView.class);
    target.myClass = Utils.findRequiredViewAsType(source, R.id.my_class, "field 'myClass'", TextView.class);
    target.myIdentifyId = Utils.findRequiredViewAsType(source, R.id.my_identifyId, "field 'myIdentifyId'", TextView.class);
    target.myWeather = Utils.findRequiredViewAsType(source, R.id.my_weather, "field 'myWeather'", TextView.class);
    view = Utils.findRequiredView(source, R.id.more, "field 'more' and method 'showPop'");
    target.more = Utils.castView(view, R.id.more, "field 'more'", ImageView.class);
    view2131230840 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showPop(p0);
      }
    });
    target.allappRecycler = Utils.findRequiredViewAsType(source, R.id.allapp_recycler, "field 'allappRecycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mySchool = null;
    target.myRank = null;
    target.myClass = null;
    target.myIdentifyId = null;
    target.myWeather = null;
    target.more = null;
    target.allappRecycler = null;

    view2131230840.setOnClickListener(null);
    view2131230840 = null;

    this.target = null;
  }
}
