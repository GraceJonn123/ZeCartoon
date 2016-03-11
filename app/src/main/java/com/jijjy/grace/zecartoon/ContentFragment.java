package com.jijjy.grace.zecartoon;

/**
 * Created by grace on 3/11/16.
 */
    import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

    /**
     * Created by Konstantin on 22.12.2014.
     */
    public class ContentFragment extends Fragment implements ScreenShotable {
        public static final String BRAVO= "Bravo";
        public static final String SCOOBY = "Scooby";
        public static final String MICKEY= "Mickey";
        public static final String SPONGE = "Sponge";
        public static final String RATATOUILLE= "Ratatouille";
        public static final String SIMBA = "Simba";
        public static final String SIMPSON = "Simpson";
        public static final String TWEETY = "Tweety";
        public static final String CLOSE = "Close";

        private View containerView;
        protected ImageView mImageView;
        protected int res;
        private Bitmap bitmap;

        public static ContentFragment newInstance(int resId) {
            ContentFragment contentFragment = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Integer.class.getName(), resId);
            contentFragment.setArguments(bundle);
            return contentFragment;
        }


        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            this.containerView = view.findViewById(R.id.container);
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            res = getArguments().getInt(Integer.class.getName());
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mImageView = (ImageView) rootView.findViewById(R.id.image_content);
            mImageView.setClickable(true);
            mImageView.setFocusable(true);
            mImageView.setImageResource(res);
            return rootView;
        }

        @Override
        public void takeScreenShot() {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                            containerView.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    containerView.draw(canvas);
                    ContentFragment.this.bitmap = bitmap;
                }
            };

            thread.start();

        }

        @Override
        public Bitmap getBitmap() {
            return bitmap;
        }
    }

