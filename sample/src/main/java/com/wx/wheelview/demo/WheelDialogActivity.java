package com.wx.wheelview.demo;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WheelDialogActivity extends Activity {
    View view;
    Dialog dialog;
    WheelView wv_height;
    WheelView wv_height_2;
    WheelView wv_unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_dialog);
        view = getLayoutInflater().inflate(R.layout.wheel_view, null);
        dialog = new Dialog(this, R.style.Dialog);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        wv_height = (WheelView) view.findViewById(R.id.wv_height);
        wv_height.setWheelAdapter(new ArrayWheelAdapter(this));
        wv_height.setSkin(WheelView.Skin.Holo);
        wv_height.setWheelData(createMainDatas());
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 20;
        style.textSize = 16;
        wv_height.setStyle(style);

        wv_height_2 = (WheelView) view.findViewById(R.id.wv_height_2);
        wv_height_2.setWheelAdapter(new ArrayWheelAdapter(this));
        wv_height_2.setSkin(WheelView.Skin.Holo);
        wv_height_2.setWheelData(createSubDatas().get(createMainDatas().get(wv_height.getSelection())));
        wv_height_2.setStyle(style);
        wv_height.join(wv_height_2);
        wv_height.joinDatas(createSubDatas());

        wv_unit = (WheelView) view.findViewById(R.id.wv_unit);
        wv_unit.setWheelAdapter(new ArrayWheelAdapter(this));
        wv_unit.setSkin(WheelView.Skin.Holo);
        wv_unit.setWheelData(createChildDatas().get(createSubDatas().get(createMainDatas().get(wv_height_2
                .getSelection())).get(wv_height_2.getSelection())));
        wv_unit.setStyle(style);
        wv_height_2.join(wv_unit);
        wv_height_2.joinDatas(createChildDatas());

        findViewById(R.id.btn_wheel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        view.findViewById(R.id.btn_ensure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = wv_height.getSelectionItem().toString() + wv_height_2.getSelectionItem().toString() + wv_unit.getSelectionItem().toString();
                Toast.makeText(WheelDialogActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private List<String> createMainDatas() {
        String[] strings = {"黑龙江", "吉林", "辽宁"};
        return Arrays.asList(strings);
    }

    private HashMap<String, List<String>> createSubDatas() {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        String[] strings = {"黑龙江", "吉林", "辽宁"};
        String[] s1 = {"哈尔滨", "齐齐哈尔", "大庆"};
        String[] s2 = {"长春", "吉林"};
        String[] s3 = {"沈阳", "大连", "鞍山", "抚顺"};
        String[][] ss = {s1, s2, s3};
        for (int i = 0; i < strings.length; i++) {
            map.put(strings[i], Arrays.asList(ss[i]));
        }
        return map;
    }

    private HashMap<String, List<String>> createChildDatas() {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        String[] strings = {"哈尔滨", "齐齐哈尔", "大庆", "长春", "吉林", "沈阳", "大连", "鞍山", "抚顺"};
        String[] s1 = {"道里区", "道外区", "南岗区", "香坊区"};
        String[] s2 = {"龙沙区", "建华区", "铁锋区"};
        String[] s3 = {"红岗区", "大同区"};
        String[] s11 = {"南关区", "朝阳区"};
        String[] s12 = {"龙潭区"};
        String[] s21 = {"和平区", "皇姑区", "大东区", "铁西区"};
        String[] s22 = {"中山区", "金州区"};
        String[] s23 = {"铁东区", "铁西区"};
        String[] s24 = {"新抚区", "望花区", "顺城区"};
        String[][] ss = {s1, s2, s3, s11, s12, s21, s22, s23, s24};
        for (int i = 0; i < strings.length; i++) {
            map.put(strings[i], Arrays.asList(ss[i]));
        }
        return map;
    }
}
