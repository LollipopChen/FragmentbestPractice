package com.example.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Bean.News;
import com.example.avtivity.NewsContentActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * Created by TS-YFZX-CQE on 2017/6/23.
 */

public class NewsTitleFragment extends Fragment {

    private View view;
    private RecyclerView mRecyclerView;
    private boolean isTwoPane;
    private RecyclerViewAdapter mNewsAdapater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       view = inflater.inflate(R.layout.news_title_frag,container,false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mNewsAdapater = new RecyclerViewAdapter(getNews());
        mRecyclerView.setAdapter(mNewsAdapater);
        return view;
    }

    private List<News> getNews() {
        List<News> mList = new ArrayList<>();
        for (int i = 1 ; i < 50 ; i++){
            News news = new News();
            news.setTitle("This is title"+ i);
            news.setContent(getRandomLengths("This is content"+i +"."));
            mList.add(news);
        }

        return mList;
    }

    private String getRandomLengths(String s) {
        Random random = new Random();
        int num = random.nextInt(50)+1;
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<num;i++){
            sb.append(s);
        }
        return sb.toString();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null){
            isTwoPane = true;//找到news_content_layout布局，为双页模式
        }else{
            isTwoPane = false;//找不到news_content_layout布局，为单页模式
        }
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
        private List<News> mNewsList ;
        public RecyclerViewAdapter(List<News> newsList) {
            mNewsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
            final ViewHolder holder =  new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPane){//双页
                        NewsContentFragment newsContent = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContent.refesh(news.getTitle(),news.getContent());
                    }else{//单页
                        NewsContentActivity.antionStart(getActivity(),news.getTitle(),news.getContent());
                    }
                }
            });

            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitle.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView newsTitle;
            public ViewHolder(View itemView) {
                super(itemView);
                newsTitle = (TextView)itemView.findViewById(R.id.news_title_left);
            }
        }
    }
}
