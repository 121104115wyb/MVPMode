package com.renogy.mvpmode.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.constant.MemoryConstants;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
import com.lxj.xpopup.interfaces.XPopupImageLoader;
import com.renogy.mvpmode.MVPApp;
import com.renogy.mvpmode.R;
import com.renogy.videocompress.VideoCompress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Create by 17474 on 2021/3/3.
 * Email： lishuwentimor1994@163.com
 * Describe：图片处理工具
 */
public class MediaResultUtils {
    //基础的目标文件地址
    private final static String baseDestPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static File file;

    private final static class LazyHolder {
        public final static MediaResultUtils INSTANCE = new MediaResultUtils();
    }

    public static MediaResultUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void startImgCompress(List<String> list, CompressTask.OnCompressCallBack onCompressCallBack) {
        ThreadUtils.executeBySingle(new CompressTask(list, onCompressCallBack));
    }

    public static class CompressTask extends ThreadUtils.SimpleTask<List<String>> {
        private final List<String> pathList;
        private final OnCompressCallBack onCompressCallBack;

        public CompressTask(List<String> pathList, OnCompressCallBack onCompressCallBack) {
            this.pathList = pathList;
            this.onCompressCallBack = onCompressCallBack;
        }

        @Override
        public List<String> doInBackground() throws Throwable {
            List<String> list = new ArrayList<>();
            for (String s : pathList) {
                list.add(compress(MVPApp.getInstance(), s).getPath());
            }
            return list;
        }


        @Override
        public void onSuccess(List<String> result) {
            if (onCompressCallBack != null) {
                onCompressCallBack.onCompressSuccess(result);
            }
        }

        @Override
        public void onFail(Throwable t) {
            super.onFail(t);
            if (onCompressCallBack != null) {
                onCompressCallBack.onCompressFailed(t.getLocalizedMessage());
            }
        }

        private File compress(Context mContext, String filepath) {
            //获取旋转角度
            int degree = ImageUtils.getRotateDegree(filepath);
            File appDir = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath());
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            //重命名图片
            File file = new File(appDir, System.currentTimeMillis() + ".png");
            //旋转图片，防止图片压缩后旋转
            Bitmap bitmap = ImageUtils.rotate(BitmapFactory.decodeFile(filepath), degree, 0, 0);
            //图片大小压缩
            Bitmap bitmapSize = ImageUtils.compressBySampleSize(bitmap, 640, 640);
            Log.d("filePath", "doInBackground: ----filePath:" + filepath);
            //质量压缩，我们默认为90
            Bitmap qualityBitmap = ImageUtils.getBitmap(ImageUtils.compressByQuality(bitmapSize, 90), 0);
            return ImageUtils.save(qualityBitmap, file, Bitmap.CompressFormat.JPEG) ? file : null;
        }

        //压缩回调
        public interface OnCompressCallBack {

            /**
             * 压缩成功
             *
             * @param list 图片列表
             */
            void onCompressSuccess(List<String> list);

            /**
             * 压缩失败
             *
             * @param msg 错误信息
             */
            void onCompressFailed(String msg);

        }
    }

    //当你点击图片的时候执行以下代码：
    // 多图片场景（你有多张图片需要浏览）
    public void showImageList(Activity activity,
            ImageView imageView, int position, List<Object> list,
            RecyclerView recyclerView) {
        //srcView参数表示你点击的那个ImageView，动画从它开始，结束时回到它的位置。
        new XPopup.Builder(activity).asImageViewer(imageView, position, list, (OnSrcViewUpdateListener) (popupView, position1) -> {
            // 作用是当Pager切换了图片，需要更新源View
            popupView.updateSrcView((ImageView) recyclerView.getChildAt(position1).findViewById(R.id.postItemImg));
        }, imageLoader).isShowSaveButton(false).show();
    }


    /**
     * 图片浏览窗，单个图片
     *
     * @param activity  上下文
     * @param imageView 视图
     * @param url       图片的地址
     */
    public void showImage(Activity activity, ImageView imageView, String url) {
        new XPopup.Builder(activity)
                .asImageViewer(imageView, url, imageLoader)
                .isShowSaveButton(false)
                .show();
    }

    // 图片加载器，XPopup不负责加载图片，需要你实现一个图片加载器传给我，这里以Glide为例（可直接复制到项目中）:
    private final XPopupImageLoader imageLoader = new XPopupImageLoader() {
        //加载图片
        @Override
        public void loadImage(int position, @NonNull Object uri, @NonNull ImageView imageView) {
            Glide.with(imageView).load(uri.toString()).apply(new RequestOptions().override(Target.SIZE_ORIGINAL)).into(imageView);
        }

        //当使用save按钮时，我们可以把图片下载下来
        //必须实现这个方法，返回uri对应的缓存文件，可参照下面的实现，内部保存图片会用到。如果你不需要保存图片这个功能，可以返回null。
        @Override
        public File getImageFile(@NonNull Context context, @NonNull Object uri) {
            return null;
        }
    };


    /**
     * 视频压缩，低质量压缩和中等质量压缩对大视频的压缩画质相差很大，但是所占空间相差不大
     * 视频小于5M我们不要压缩
     * 视频在6-100M我们采用中等质量压缩
     * 当压缩后的视频，大于10M或者大于20M时，我们应该重新压缩一遍
     * 大于100M我们不做任何处理，提示用户视频太大
     *
     * @param localPath 本地的path
     */
    public void startVideoCompress(String localPath, VideoCompressListener compressListener) {
        if (TextUtils.isEmpty(localPath)) {
            if (compressListener != null) {
                compressListener.onFail("media path is empty");
            }
            LogUtils.eTag("VideoCompress", "----media path is empty");
            return;
        }
        if (isOutRange(localPath, 100)) {
            if (compressListener != null) {
                compressListener.onFail("file size exceeds 100M");
            }
            LogUtils.eTag("VideoCompress", "----file size exceeds 100M");
            return;
        }
        //不超过6M我们不去压缩视频
        if (!isOutRange(localPath, 6)) {
            if (compressListener != null) {
                compressListener.onSuccess(localPath);
            }
            LogUtils.eTag("VideoCompress", "----不超过6M");
            return;
        }
        String destPath = baseDestPath + "VID_" + TimeUtils.getNowMills() + ".mp4";
        VideoCompress.VideoCompressTask task = VideoCompress.compressVideoMedium(localPath, destPath, new VideoCompress.CompressListener() {
            @Override
            public void onStart() {
                LogUtils.eTag("VideoCompress", "----onStart" + localPath);
                //Start Compress
                ///storage/emulated/0/Download/MyVID_1615462798247.mp4 size:400.743KB  低质量压缩
                //size:7.015MB 高质量压缩，变大6倍
                if (compressListener != null) compressListener.onStart();
            }

            @Override
            public void onSuccess() {
                LogUtils.eTag("VideoCompress", "----onSuccess" + destPath);
                if (compressListener != null) compressListener.onSuccess(destPath);
            }

            @Override
            public void onFail() {
                LogUtils.eTag("VideoCompress", "----onFail");
                if (compressListener != null) compressListener.onFail("compress failed");
            }

            @Override
            public void onProgress(float percent) {
                LogUtils.eTag("VideoCompress", "----onProgress:" + percent + "%");
                if (compressListener != null) compressListener.onProgress(percent);
            }
        });
    }

    /**
     * 是否超出文件限制的大小
     *
     * @param localPath 本地地址
     * @param rangSize  设置范围大小
     * @return 超过 true or 不超过 false
     */
    private boolean isOutRange(String localPath, int rangSize) {
        if (TextUtils.isEmpty(localPath)) return false;
        long totalSize = FileUtils.getLength(localPath);
        return totalSize > MemoryConstants.MB * rangSize;
    }

    //视频压缩回调
    public interface VideoCompressListener {
        void onStart();

        void onSuccess(String destPath);

        void onFail(String msg);

        void onProgress(float percent);
    }


    /**
     * Create a file Uri for saving an image or video
     */
    public static Uri getOutputMediaFileUri(Context context, int type) {
        Uri uri = null;
        //适配Android N
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", getOutputMediaFile(type));
        } else {
            return Uri.fromFile(getOutputMediaFile(type));
        }
    }

    /**
     * Create a File for saving an image or video
     */
    public static File getOutputMediaFile(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "image");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }
        file = mediaFile;
        return mediaFile;
    }


    /**
     * 获取视频的第一帧图片
     */
    public static void getImageForVideo(String videoPath, OnLoadVideoImageListener listener) {
        LoadVideoImageTask task = new LoadVideoImageTask(listener);
        task.execute(videoPath);
    }

    public static class LoadVideoImageTask extends AsyncTask<String, Integer, File> {
        private OnLoadVideoImageListener listener;

        public LoadVideoImageTask(OnLoadVideoImageListener listener) {
            this.listener = listener;
        }

        @Override
        protected File doInBackground(String... params) {
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            String path = params[0];
            if (path.startsWith("http"))
                //获取网络视频第一帧图片
                mmr.setDataSource(path, new HashMap());
            else
                //本地视频
                mmr.setDataSource(path);
            Bitmap bitmap = mmr.getFrameAtTime();
            //保存图片
            File f = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if (f != null && f.exists()) {
                f.delete();
            }
            try {
                Bitmap newBitmap = resizeBitmap(480, 480, bitmap);
                FileOutputStream out = new FileOutputStream(f);
                newBitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mmr.release();
            return f;
        }

        @Override
        protected void onPostExecute(File file) {
            super.onPostExecute(file);
            if (listener != null) {
                listener.onLoadImage(file);
            }
        }
    }

    public interface OnLoadVideoImageListener {
        void onLoadImage(File file);
    }

    public static Bitmap resizeBitmap(float newWidth, float newHeight, Bitmap bitmap) {

        Matrix matrix = new Matrix();
        matrix.postScale(newWidth / bitmap.getWidth(),
                newHeight / bitmap.getHeight());
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
        return newBitmap;
    }

}
