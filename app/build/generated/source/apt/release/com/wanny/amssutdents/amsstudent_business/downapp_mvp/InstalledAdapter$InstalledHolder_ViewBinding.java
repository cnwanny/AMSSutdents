// Generated code from Butter Knife. Do not modify!
package com.wanny.amssutdents.amsstudent_business.downapp_mvp;

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

public class InstalledAdapter$InstalledHolder_ViewBinding<T extends InstalledAdapter.InstalledHolder> implements Unbinder {
  protected T target;

  @UiThread
  public InstalledAdapter$InstalledHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.iconItemImage = Utils.findRequiredViewAsType(source, R.id.icon_item_image, "field 'iconItemImage'", ImageView.class);
    target.appItemName = Utils.findRequiredViewAsType(source, R.id.app_item_name, "field 'appItemName'", TextView.class);
    target.appItemDetail = Utils.findRequiredViewAsType(source, R.id.app_item_detail, "field 'appItemDetail'", TextView.class);
    target.appItemUninstall = Utils.findRequiredViewAsType(source, R.id.app_item_uninstall, "field 'appItemUninstall'", TextView.class);
    target.appItemEnable = Utils.findRequiredViewAsType(source, R.id.app_item_enable, "field 'appItemEnable'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iconItemImage = null;
    target.appItemName = null;
    target.appItemDetail = null;
    target.appItemUninstall = null;
    target.appItemEnable = null;

    this.target = null;
  }
}
