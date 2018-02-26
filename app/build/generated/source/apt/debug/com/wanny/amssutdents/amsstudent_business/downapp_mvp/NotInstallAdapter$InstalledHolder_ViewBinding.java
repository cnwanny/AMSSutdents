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

public class NotInstallAdapter$InstalledHolder_ViewBinding<T extends NotInstallAdapter.InstalledHolder> implements Unbinder {
  protected T target;

  @UiThread
  public NotInstallAdapter$InstalledHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.iconNotitemImage = Utils.findRequiredViewAsType(source, R.id.icon_notitem_image, "field 'iconNotitemImage'", ImageView.class);
    target.appNotitemName = Utils.findRequiredViewAsType(source, R.id.app_notitem_name, "field 'appNotitemName'", TextView.class);
    target.appNotitemDetail = Utils.findRequiredViewAsType(source, R.id.app_notitem_detail, "field 'appNotitemDetail'", TextView.class);
    target.appItemDownload = Utils.findRequiredViewAsType(source, R.id.app_item_download, "field 'appItemDownload'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iconNotitemImage = null;
    target.appNotitemName = null;
    target.appNotitemDetail = null;
    target.appItemDownload = null;

    this.target = null;
  }
}
