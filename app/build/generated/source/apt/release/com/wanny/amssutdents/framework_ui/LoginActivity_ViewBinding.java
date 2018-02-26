// Generated code from Butter Knife. Do not modify!
package com.wanny.amssutdents.framework_ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wanny.amssutdents.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding<T extends LoginActivity> implements Unbinder {
  protected T target;

  private View view2131230911;

  @UiThread
  public LoginActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.closImage = Utils.findRequiredViewAsType(source, R.id.clos_image, "field 'closImage'", ImageView.class);
    target.editText = Utils.findRequiredViewAsType(source, R.id.editText, "field 'editText'", EditText.class);
    view = Utils.findRequiredView(source, R.id.textView, "field 'textView' and method 'startLogin'");
    target.textView = Utils.castView(view, R.id.textView, "field 'textView'", TextView.class);
    view2131230911 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.startLogin(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.closImage = null;
    target.editText = null;
    target.textView = null;

    view2131230911.setOnClickListener(null);
    view2131230911 = null;

    this.target = null;
  }
}
