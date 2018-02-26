package com.wanny.amssutdents.framework_utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类名 ：AppUtils.java
 * 功能 ：获取手机屏幕信息
 * 作者 ： wanny
 * 时间 ：2015-4-24上午10:52:55
 */
public class AppUtils {


    /**
     * 功能 ： 得到当前屏幕的宽度
     * 传递参数 ：Activity
     * 返回类型 ：int
     * 作者 ： wanny
     * 时间 ：2015-4-24上午10:58:33
     */
    public static int getScreenWidthPiex(Activity activity) {
        if (activity != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            LogUtil.log("scree_width", "====" + displayMetrics.widthPixels);
            return displayMetrics.widthPixels;
        } else {
            return 0;
        }
    }

    /**
     * 功能 ： 屏幕的高度
     * 传递参数 ：Activity
     * 返回类型 ：int
     * 作者 ： wanny
     * 时间 ：2015-4-24上午11:08:32
     */
    public static int getScreenHeightPiex(Activity activity) {
        if (activity != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            LogUtil.log("scree_height", "===" + displayMetrics.heightPixels);
            return displayMetrics.heightPixels;
        } else {
            return 0;
        }
    }

    /**
     * 功能 ： 获取屏幕的密度
     * 传递参数 ：传递的是activity
     * 返回类型 ：float
     * 作者 ： wanny
     * 时间 ：2015-4-24上午11:15:12
     */
    public static float getScreenDensity(Activity activity) {
        if (activity != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            LogUtil.log("screen_density", "==" + displayMetrics.density);
            return displayMetrics.density;
        } else {
            return 0f;
        }
    }

    /**
     * 功能 ： 获取屏幕的Point属性
     * 传递参数 ：传递的是activity
     * 返回类型 ：Point
     * 作者 ： wanny
     * 时间 ：2015-4-24上午11:15:12
     */
    public static Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        LogUtil.log("Screen---Width = " + w_screen + " Height = " + h_screen + " densityDpi = " + dm.densityDpi);
        return new Point(w_screen, h_screen);
    }


    /**
     * 功能 ： 获取屏幕的高度属性
     * 传递参数 ：传递的是activity
     * 返回类型 ：Point
     * 作者 ： wanny
     * 时间 ：2015-4-24上午11:15:12
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
//        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
//        LogUtil.log("Screen---Width = " + w_screen + " Height = " + h_screen + " densityDpi = " + dm.densityDpi);
        return h_screen;
    }

    /**
     * 功能 ： 获取屏幕的宽度属性
     * 传递参数 ：传递的是activity
     * 返回类型 ：Point
     * 作者 ： wanny
     * 时间 ：2015-4-24上午11:15:12
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
//        int h_screen = dm.heightPixels;
//        LogUtil.log("Screen---Width = " + w_screen + " Height = " + h_screen + " densityDpi = " + dm.densityDpi);
        return w_screen;
    }


    public static boolean isImg(String filepath) {
        if (filepath != null) {
            int typeindex = filepath.lastIndexOf(".");
            if (typeindex != -1) {
                String filetype = filepath.substring(typeindex + 1).toLowerCase();
                LogUtil.log("filetype:" + filetype);
                if (filetype != null) {
                    if (filetype.equals("jpg") || filetype.equals("gif") || filetype.equals("png")
                            || filetype.equals("jpeg") || filetype.equals("bmp") || filetype.equals("wbmp")
                            || filetype.equals("ico") || filetype.equals("jpe")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 获取屏幕长宽比
     *
     * @param context
     * @return
     */
    public static float getScreenRate(Context context) {
        Point P = getScreenMetrics(context);
        float H = P.y;
        float W = P.x;
        return (H / W);
    }


    //获取以为小数
    public static double changeDouble(Double dou) {
        NumberFormat nf = new DecimalFormat("#.0");
        dou = Double.parseDouble(nf.format(dou));
        return dou;
    }

    //获取以为小数
    public static double changeDoubleTwo(Double dou) {
        NumberFormat nf = new DecimalFormat("#.00");
        dou = Double.parseDouble(nf.format(dou));
        return dou;
    }


    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }


    public static boolean isPhone(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("(^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$)|(^((\\(\\d{3}\\))|(\\d{3}\\-))?(1[358]\\d{9})$)"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }


    public static boolean is400(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("/^(4|8)00(\\d{4,8})$/"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }


    /**
     * 功能 ： 设置edittext的光标
     * 传递参数 ：
     * 返回类型 ：void
     * 作者 ： wanny
     * 时间 ：2015年5月5日下午4:32:15
     */
    public static void setFocusable(EditText editText) {
        if (editText.isFocusable() == false) {
            editText.setEnabled(true);
            editText.setFocusableInTouchMode(true);
            editText.setFocusable(true);
            editText.requestFocus();
            Editable editable = editText.getText();
            Selection.setSelection(editable, editable.length());
        }

    }

    public static void setFocusableFalse(EditText editText) {
        if (editText.isFocusable() == true) {
            editText.setEnabled(false);
            editText.setFocusableInTouchMode(false);
            editText.setFocusable(false);
        }
    }

    public static void setFocuseableAuto(AutoCompleteTextView auton) {
        if (auton.isFocusable() == false) {
            auton.setEnabled(true);
            auton.setFocusableInTouchMode(true);
            auton.setFocusable(true);
            auton.requestFocus();
            Editable editable = auton.getText();
            Selection.setSelection(editable, editable.length());
        }
    }

    public static void setUnFocusableAuto(AutoCompleteTextView auton) {
        if (auton.isFocusable() == true) {
            auton.setEnabled(false);
            auton.setFocusableInTouchMode(false);
            auton.setFocusable(false);
        }

    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    @Deprecated
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dp转换成px
     *
     * @param dp
     * @return
     */
    public static int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * 将px转换成dp
     *
     * @param px
     * @return
     */
    public static float pxToDp(float px) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * @param activity
     * @param dp
     * @return
     */
    public static int dp2px(Activity activity, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                activity.getResources().getDisplayMetrics());
    }

    /**
     * 功能 ： 获取状态栏的高度
     * 传递参数 ：
     * 返回类型 ：int
     * 作者 ： wanny
     * 时间 ：2015年6月8日上午9:15:03
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 隐藏控件的显示
     *
     * @param view
     */
    public static void notShowView(View view) {
        if (view != null) {
            if (view.getVisibility() == View.VISIBLE) {
                view.setVisibility(View.GONE);
            } else {
                return;
            }
        }
    }

    public static void notVisible(View view) {
        if (view != null) {
            if (view.getVisibility() == View.VISIBLE) {
                view.setVisibility(View.INVISIBLE);
            } else {
                return;
            }
        }
    }

//    source=1安卓2苹果;version=APP版本号;hid=App硬件


    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
//
//    /**
//     * 获取设备的IMIE编号
//     *
//     * @param context
//     * @return
//     */
//    public static String getIMIE(Context context) {
//        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        return TelephonyMgr.getDeviceId();
//    }

    public static void showView(View view) {
        if (view != null) {
            if (view.getVisibility() != View.VISIBLE) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 功能 ： 强制弹出键盘
     * 传递参数 ：传递的是Context ,   控件对象
     * 返回类型 ：null
     * 作者 ： wanny
     * 时间 ：2015-4-24上午11:15:12
     */
    public static void showInputKeyWord(Context context, EditText view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        if (!isOpen) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }

    }

    /**
     * 返回statusbar的高度
     *
     * @param context
     * @return
     */
    public static int getStatBarHeight(Context context) {
        int result = 0;
        int staturBarHeight = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (staturBarHeight > 0) {
            result = context.getResources().getDimensionPixelSize(staturBarHeight);
        }
        return result;
    }


    /**
     * 功能 ： 强制隐藏键盘
     * 传递参数 ：传递的是activity， 控件对象
     * 返回类型 ：null
     * 作者 ： wanny
     * 时间 ：2015-4-24上午11:15:12
     */

    public static void hideInputKeyWord(Context context, EditText view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        if (isOpen) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void hideInputKeyWord(Context context) {
        InputMethodManager inputMgr = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMgr.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
    }

    public static void showInputKeyWord(Context context) {
        InputMethodManager inputMgr = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }


    //影藏键盘
    public static void hideSoftKeyboard(Activity context) {
        InputMethodManager inputMgr = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (context.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (context.getCurrentFocus() != null)
                inputMgr.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 设置textview的字体格式，中间字体大小和颜色都是自定义，字体和格式是模式设置的。三段文字中间的才加效果
     *
     * @param context
     * @param activity
     * @param text          文本
     * @param startLenth    普通文字的长度
     * @param colorCenterId 特殊设置颜色
     * @param lengthend     末尾文本长度
     * @param colorEndId    末尾末尾字体颜色
     * @param textSize      特殊字体大小
     * @param isBlod        是否加粗
     * @return
     */
    public static SpannableStringBuilder setStyleSingale(Context context, Activity activity, String text, int startLenth, int colorCenterId, int lengthend, int colorEndId, int textSize, boolean isBlod) {
        if (text == null || text.equals("") || text.length() < startLenth || text.length() < lengthend) {
            return null;
        }
        SpannableStringBuilder ss = new SpannableStringBuilder(text);
        ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, colorEndId)), 0,
                startLenth, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        int px = AppUtils.dp2px(activity, textSize);
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(px);
        ss.setSpan(span, startLenth, ss.length() - lengthend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, colorCenterId)), startLenth, ss.length() - lengthend, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        if (isBlod) {
            ss.setSpan(new StyleSpan(Typeface.BOLD), startLenth, ss.length() - lengthend, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, colorEndId)), ss.length() - lengthend, ss.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return ss;
    }

    public static float caulate(String time1, String time2) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = new GregorianCalendar();
            Date d1 = df.parse(time1);
            Date d2 = df.parse(time2);
            return (d1.getTime() - d2.getTime()) / (60 * 60 * 1000 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0f;
    }


    /**
     * 设置的是二段前面一段后面一段，前面的一段是不变的，后面的一段是字体要发生变化，加色
     *
     * @param context
     * @param activity
     * @param text          文本内容
     * @param frontLength   特殊字符前面的文字长度
     * @param colorCenterId 特殊设置颜色
     * @param colorEndId    文本默认颜色
     * @param textSize      特殊设置文本长度
     * @return
     */
    public static SpannableStringBuilder setStyleSingale(Context context, Activity activity, String text, int frontLength, int colorCenterId, int colorEndId, int textSize) {
        if (text == null || text.equals("") || text.length() < frontLength) {
            return null;
        }
        SpannableStringBuilder ss = new SpannableStringBuilder(text);
        ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, colorEndId)), 0,
                frontLength, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        int px = AppUtils.dp2px(activity, textSize);
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(px);
        ss.setSpan(span, frontLength, ss.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, colorCenterId)), frontLength,
                ss.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return ss;
    }


    /**
     *
     * @param context
     * @param activity
     * @param text          文本内容
     * @param lengthcenter  特殊设置长度
     * @param colorCenterId 特殊设置颜色
     * @param colorEndId    文本默认颜色
     * @return
     */
    public static SpannableStringBuilder setStyleFront(Context context, Activity activity, String text, int lengthcenter, int colorCenterId, int colorEndId) {
        if (text == null || text.equals("") || text.length() < lengthcenter) {
            return null;
        }
        SpannableStringBuilder ss = new SpannableStringBuilder(text);

        ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, colorCenterId)), 0,
                lengthcenter, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
//		ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,colorEndId)), 0,
//				lengthcenter, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
//		int px = AppUtils.dp2px(activity, textSize);
//		AbsoluteSizeSpan span = new AbsoluteSizeSpan(px);
//		ss.setSpan(span, lengthcenter,  ss.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, colorEndId)), lengthcenter,
                ss.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return ss;
    }


    /**
     * 判断是不是同一天
     *
     * @param timeOne
     * @param timeTwo
     * @return boolean
     */
    public static boolean isSameDay(String timeOne, String timeTwo) {
        String one = timeOne.substring(0, 10);
        String two = timeTwo.substring(0, 10);
        if (one.equals(two)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkApkExist(Context context, String packageName){
        if (TextUtils.isEmpty(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName,
                            PackageManager.MATCH_UNINSTALLED_PACKAGES);
            LogUtil.log(info.toString());
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.log(e.toString());
            return false;
        }
    }




    /**
     * 返回当前软件版本的version
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }


    /**
     //     * 该功能是来监听网络发生变化 后执行的操作
     //     * @param context
     //     */
//    //added by wang 20170221
//    public static final int MY_BACKGROUND_JOB = 0;
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public static void scheduleJob(Context context) {
//        JobScheduler js =
//                (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        JobInfo job = new JobInfo.Builder(
//                MY_BACKGROUND_JOB,
//                new ComponentName(context, NetJobService.class))
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//                .setRequiresCharging(true)
//                .build();
//        js.schedule(job);
//        //网络发生变化
//        LogUtil.log("网络发生变化","开始执行");
//    }

    //added end by wang

}
