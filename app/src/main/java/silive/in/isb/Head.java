package silive.in.isb;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Head extends Service {
    WindowManager.LayoutParams params;
    private WindowManager windowManager;
    private ImageView chatHead, image;

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        chatHead = new ImageView(this);
        chatHead.setImageResource(R.mipmap.ic_launcher);
        image = new ImageView(this);
        image.setImageResource(R.drawable.cls);
        image.setVisibility(View.GONE);

       final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.END;
        chatHead.setLayoutParams(layoutParams);

        /*params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
*/
        layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        /*layoutParams.x = 0;
        layoutParams.y = 100;*/

        //this code is for dragging the chat head
        chatHead.setOnTouchListener(new View.OnTouchListener() {
            private int initialX, close_x, close_y;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //initialX = params.x;
                        //initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        image.setVisibility(View.GONE);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        image.setVisibility(View.VISIBLE);
                        params.x = initialX
                                + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY
                                + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(chatHead, layoutParams);
                        return true;
                }
                return false;
            }
        });
        windowManager.addView(chatHead, layoutParams);
        windowManager.addView(image, layoutParams);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (chatHead != null)
            windowManager.removeView(chatHead);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
