// Generated code from Butter Knife. Do not modify!
package com.wanny.amssutdents.framework_ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wanny.amssutdents.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DownLoadActivity_ViewBinding<T extends DownLoadActivity> implements Unbinder {
  protected T target;

  private View view2131230795;

  private View view2131230796;

  private View view2131230797;

  @UiThread
  public DownLoadActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.down_close, "field 'downClose' and method 'closeActivity'");
    target.downClose = Utils.castView(view, R.id.down_close, "field 'downClose'", ImageView.class);
    view2131230795 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.closeActivity(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.down_install, "field 'downInstall' and method 'setInstall'");
    target.downInstall = Utils.castView(view, R.id.down_install, "field 'downInstall'", TextView.class);
    view2131230796 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setInstall(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.down_notinstall, "field 'downNotinstall' and method 'setNoInstall'");
    target.downNotinstall = Utils.castView(view, R.id.down_notinstall, "field 'downNotinstall'", TextView.class);
    view2131230797 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setNoInstall(p0);
      }
    });
    target.installHeader = Utils.findRequiredViewAsType(source, R.id.install_header, "field 'installHeader'", RelativeLayout.class);
    target.installedRecycle = Utils.findRequiredViewAsType(source, R.id.installed_recycle, "field 'installedRecycle'", RecyclerView.class);
    target.notinstallRecycle = Utils.findRequiredViewAsType(source, R.id.notinstall_recycle, "field 'notinstallRecycle'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.downClose = null;
    target.downInstall = null;
    target.downNotinstall = null;
    target.installHeader = null;
    target.installedRecycle = null;
    target.notinstallRecycle = null;

    view2131230795.setOnClickListener(null);
    view2131230795 = null;
    view2131230796.setOnClickListener(null);
    view2131230796 = null;
    view2131230797.setOnClickListener(null);
    view2131230797 = null;

    this.target = null;
  }
}
