package com.jwwangjw.example.layoutmanagergroup.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.jwwangjw.example.layoutmanagergroup.R;
import com.dingmouren.layoutmanagergroup.viewpager.OnViewPagerListener;
import com.dingmouren.layoutmanagergroup.viewpager.ViewPagerLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wjw
 * github: https://github.com/jwwangjw
 * email: 1508417398@qq.com
 */
public class ViewPagerLayoutManagerActivity extends AppCompatActivity {
    private static final String TAG = "ViewPagerActivity";
    private RecyclerView  mRecyclerView;
    private MyAdapter mAdapter;
    private ViewPagerLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_layout_manager);

        initView();

        initListener();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler);

        mLayoutManager = new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL);
        mAdapter = new MyAdapter();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initListener(){
        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {

            @Override
            public void onInitComplete() {
                Log.e(TAG,"onInitComplete");
                playVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext,int position) {
                Log.e(TAG,"释放位置:"+position +" 下一页:"+isNext);
                int index = 0;
                if (isNext){
                    index = 0;
                }else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position,boolean isBottom) {
                Log.e(TAG,"选中位置:"+position+"  是否是滑动到底部:"+isBottom);
                playVideo(0);
            }


        });
    }

    private void playVideo(int position) {
        View itemView = mRecyclerView.getChildAt(0);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final RelativeLayout rootView = itemView.findViewById(R.id.root_view);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        videoView.start();
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                mp.setLooping(true);
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });


        imgPlay.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    imgPlay.animate().alpha(1f).start();
                    videoView.pause();
                    isPlaying = false;
                }else {
                    imgPlay.animate().alpha(0f).start();
                    videoView.start();
                    isPlaying = true;
                }
            }
        });
    }

    private void releaseVideo(int index){
        View itemView = mRecyclerView.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        videoView.stopPlayback();
        imgThumb.animate().alpha(1).start();
        imgPlay.animate().alpha(0f).start();
    }
    public static ArrayList<String> GSONDEMO(){
        String json="[{\"_id\":\"5e9830b0ce330a0248e89d86\",\"feedurl\":\"http://jzvd.nathen.cn/video/1137e480-170bac9c523-0007-1823-c86-de200.mp4\",\"nickname\":\"王火火\",\"description\":\"这是第一条Feed数据\",\"likecount\":10000,\"avatar\":\"http://jzvd.nathen.cn/snapshot/f402a0e012b14d41ad07939746844c5e00005.jpg\"},{\"_id\":\"5e9833dec47d14020e85f416\",\"feedurl\":\"http://jzvd.nathen.cn/video/e0bd348-170bac9c3b8-0007-1823-c86-de200.mp4\",\"nickname\":\"LILILI\",\"description\":\"这是一条一起学猫叫的视频\",\"likecount\":120000,\"avatar\":\"http://jzvd.nathen.cn/snapshot/8bd6d06878fc4676a62290cbe8b5511f00005.jpg\"},{\"_id\":\"5e9833e0c47d14020e85f418\",\"feedurl\":\"http://jzvd.nathen.cn/video/2f03c005-170bac9abac-0007-1823-c86-de200.mp4\",\"nickname\":\"新闻启示录\",\"description\":\"赶紧把这个转发给你们的女朋友吧，这才是对她们最负责的AI\",\"likecount\":45000000,\"avatar\":\"http://jzvd.nathen.cn/snapshot/371ddcdf7bbe46b682913f3d3353192000005.jpg\"},{\"_id\":\"5e9833e0a21527020d426e91\",\"feedurl\":\"http://jzvd.nathen.cn/video/7bf938c-170bac9c18a-0007-1823-c86-de200.mp4\",\"nickname\":\"综艺大咖秀\",\"description\":\"男明星身高暴击\",\"likecount\":98777000,\"avatar\":\"http://jzvd.nathen.cn/snapshot/dabe6ca3c71942fd926a86c8996d750f00005.jpg\"},{\"_id\":\"5e9833e1c47d14020e85f43a\",\"feedurl\":\"http://jzvd.nathen.cn/video/47788f38-170bac9ab8a-0007-1823-c86-de200.mp4\",\"nickname\":\"南翔不爱吃饭\",\"description\":\"挑战手抓饼的一百种吃法第七天\",\"likecount\":500000,\"avatar\":\"http://jzvd.nathen.cn/snapshot/edac56544e2f43bb827bd0e819db381000005.jpg\"},{\"_id\":\"5e983406a21527020d426f1f\",\"feedurl\":\"http://jzvd.nathen.cn/video/2d6ffe8f-170bac9ab87-0007-1823-c86-de200.mp4\",\"nickname\":\"王者主播那些事儿\",\"description\":\"你有试过蔡文姬打野吗？\",\"likecount\":1000000,\"avatar\":\"http://jzvd.nathen.cn/snapshot/531f1e488eb84b898ae9ca7f6ba758ed00005.jpg\"},{\"_id\":\"5e98340da21527020d426f43\",\"feedurl\":\"http://jzvd.nathen.cn/video/633e0ce-170bac9ab65-0007-1823-c86-de200.mp4\",\"nickname\":\"十秒学做菜\",\"description\":\"两款爱吃的三明治分享\",\"likecount\":1010102,\"avatar\":\"http://jzvd.nathen.cn/snapshot/ad0331e78393457d88ded2257d9e47c800005.jpg\"},{\"_id\":\"5e983415a21527020d426f7b\",\"feedurl\":\"http://jzvd.nathen.cn/video/2d6ffe8f-170bac9ab87-0007-1823-c86-de200.mp4\",\"nickname\":\"九零后老母亲\",\"description\":\"从孕期到产后，老公一直要我用这个勺子喝汤\",\"likecount\":94321,\"avatar\":\"http://jzvd.nathen.cn/snapshot/6ae53110f7fd470683587746f027698400005.jpg\"},{\"_id\":\"5e98341da21527020d426f97\",\"feedurl\":\"http://jzvd.nathen.cn/video/51f7552c-170bac98718-0007-1823-c86-de200.mp4\",\"nickname\":\"FPX电子竞技俱乐部\",\"description\":\"甲方的需求：F P X冠军皮肤的起源\",\"likecount\":1200000,\"avatar\":\"http://jzvd.nathen.cn/snapshot/ef384b95897b470c80a4aca4dd1112a500005.jpg\"},{\"_id\":\"5e983423a21527020d426fbb\",\"feedurl\":\"http://jzvd.nathen.cn/video/2a101070-170bad88892-0007-1823-c86-de200.mp4\",\"nickname\":\"抖音官方广告报名！\",\"description\":\"买它！买它！买它！\",\"likecount\":480,\"avatar\":\"http://jzvd.nathen.cn/snapshot/86a055d08b514c9ca1e76e76862105ec00005.jpg\"}]";
        ArrayList<String> urls=new ArrayList<String>();
        try{
            JSONArray jsonArray=new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject != null) {
                    String url=jsonObject.optString("feedurl");
                    urls.add(url);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return urls;
    }
    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        private int[] imgs = {R.mipmap.img_video_1,R.mipmap.img_video_2};
        ArrayList<String> urls=GSONDEMO();
        public MyAdapter(){
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String url=urls.get(position%(urls.size()));
            Bitmap bitmap = getNetVideoBitmap(url);
            holder.img_thumb.setImageBitmap(bitmap);//对应的ImageView赋值图片
            holder.videoView.setVideoURI(Uri.parse(urls.get(position%(urls.size()))));
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView img_thumb;
            VideoView videoView;
            ImageView img_play;
            RelativeLayout rootView;
            public ViewHolder(View itemView) {
                super(itemView);
                img_thumb = itemView.findViewById(R.id.img_thumb);
                videoView = itemView.findViewById(R.id.video_view);
                img_play = itemView.findViewById(R.id.img_play);
                rootView = itemView.findViewById(R.id.root_view);
            }
        }
    }
}
