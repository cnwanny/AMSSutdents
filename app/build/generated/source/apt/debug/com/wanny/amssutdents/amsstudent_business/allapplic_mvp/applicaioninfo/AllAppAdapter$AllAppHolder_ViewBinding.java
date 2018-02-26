// Generated code from Butter Knife. Do not modify!
package com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wanny.amssutdents.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AllAppAdapter$AllAppHolder_ViewBinding<T extends AllAppAdapter.AllAppHolder> implements Unbinder {
  protected T target;

  @UiThread
  public AllAppAdapter$AllAppHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.appicon = Utils.findRequiredViewAsType(source, R.id.appicon, "field 'appicon'", ImageView.class);
    target.appname = Utils.findRequiredViewAsType(source, R.id.appname, "field 'appname'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.appicon = null;
    target.appname = null;

    this.target = null;
  }
}
