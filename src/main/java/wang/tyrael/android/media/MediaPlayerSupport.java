package wang.tyrael.android.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.VideoView;

import java.io.IOException;

import wang.tyrael.android.application.ApplicationHolder;
import wang.tyrael.report.ReportManager;

/**
 * 只做通用功能，不做业务相关功能。
 * 1. 提供一个静音功能。
 *TODO 做一个状态记录功能
 * Created by wangchao on 2015/12/15.
 */
public class MediaPlayerSupport {
    public static final int STATE_COMPLETED = 1;
    public static final int STATE_PREPARED = 2;
    public static final int STATE_IDLE = 3;

    public static final int STATE_ERROR = -1;


    private static final AudioManager sfAudioManager = (AudioManager) ApplicationHolder.getApplication().getSystemService(Context.AUDIO_SERVICE);
    private static final String TAG = "MediaPlayerSupport";
    private static final Context context = ApplicationHolder.getApplication();
    private static final String packageName = context.getPackageName();

    private MediaPlayer mPlayer;
    private VideoView mVideoView;
//    private
    private MediaPlayer.OnPreparedListener mPreparedListener;
    private MediaPlayer.OnCompletionListener mCompetionListener;
    private MediaPlayer.OnErrorListener mErrorListener;

    private volatile boolean mMute;
    /**
     * 状态管理只是够用。
     * 目前没有完整的描述mediaplayer的所有状态
     */
    private volatile int state = STATE_IDLE;

    public MediaPlayerSupport(VideoView vv){
        mVideoView = vv;
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                state = STATE_PREPARED;
                mPlayer = mediaPlayer;
                //如果在第一次播放前，外部设置了静音，则静音没有生效，要在这里检查一下
                if(mMute){
                    mPlayer.setVolume(0, 0);
                }
                if(mPreparedListener != null){
                    mPreparedListener.onPrepared(mediaPlayer);
                }
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                state = STATE_COMPLETED;
                if (mCompetionListener != null) {
                    mCompetionListener.onCompletion(mediaPlayer);
                }
            }
        });
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                state = STATE_ERROR;
                if(mErrorListener != null){
                    mErrorListener.onError(mediaPlayer, i, i1);
                }
                //重置一下
                //http://bugly.qq.com/detail?app=900008912&pid=1&ii=556#stack
                try{
                    mediaPlayer.reset();
                    state = STATE_IDLE;
                }catch (IllegalStateException ise){
                    ReportManager.getInstance().reportException(null, ise);
                }
                ReportManager.getInstance().reportFail(TAG, "视频播放失败。 maincode:" + i + " extra:" + i1);
                return true;
            }
        });
    }

    public MediaPlayerSupport(MediaPlayer player) {
        this.mPlayer = player;
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                state = STATE_PREPARED;
                mPlayer = mediaPlayer;
                //如果在第一次播放前，外部设置了静音，则静音没有生效，要在这里检查一下
                if(mMute){
                    mPlayer.setVolume(0, 0);
                }
                if(mPreparedListener != null){
                    mPreparedListener.onPrepared(mediaPlayer);
                }
            }
        });
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                state = STATE_COMPLETED;
                if (mCompetionListener != null) {
                    mCompetionListener.onCompletion(mediaPlayer);
                }
            }
        });
        mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                state = STATE_ERROR;
                if(mErrorListener != null){
                    mErrorListener.onError(mediaPlayer, i, i1);
                }
                //重置一下
                //http://bugly.qq.com/detail?app=900008912&pid=1&ii=556#stack
                try{
                    mediaPlayer.reset();
                    state = STATE_IDLE;
                }catch (IllegalStateException ise){
                    ReportManager.getInstance().reportException(null, ise);
                }
                ReportManager.getInstance().reportFail(TAG, "播放失败。 maincode:" + i + " extra:" + i1);
                return true;
            }
        });
    }

    public MediaPlayerSupport() {
        this(new MediaPlayer());
    }

    public void prepareRaw(int rawid) {
        //优化 根据状态决定是否要reset
        mPlayer.reset();
        Uri uri = Uri.parse("android.resource://" + packageName + "/" + rawid);
        try {
            mPlayer.setDataSource(context, uri);
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public MediaPlayer getPlayer(){
        return mPlayer;
    }

    public VideoView getVideoView(){
        return mVideoView;
    }


    public void mute(boolean mute){
        //需要检查player的状态
        try {
            mMute = mute;
            if(mPlayer == null){
                return;
            }
            if (mute) {
                mPlayer.setVolume(0, 0);
            } else {
                int volumeLevel = sfAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                int maxVolume = sfAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                float volume = volumeLevel / (float) maxVolume;
                mPlayer.setVolume(volume, volume);
            }
        }catch (IllegalStateException e){
            ReportManager.getInstance().reportException(null, e);
            //ToastUtil.show("播放出现问题");
        }
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener listener){
        mPreparedListener = listener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener listener){
        mErrorListener = listener;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener listener){
        mCompetionListener = listener;
    }
}
