package com.example.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by TS-YFZX-CQE on 2017/6/23.
 */

public class NewsContentFragment extends Fragment {

    private View view;
    private TextView newsTitleText;
    private TextView newsContentText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.news_content_frag,container,false);
        newsTitleText = (TextView) view.findViewById(R.id.news_title);
        newsContentText = (TextView) view.findViewById(R.id.news_content);

        return view;
    }

    public void refesh(String titleText , String contentText){
        newsTitleText.setText(titleText);//刷新新闻标题
        newsContentText.setText(contentText);//刷新新闻内容
    }
}
