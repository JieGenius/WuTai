package com.example.wutai.wutaimoutain.navigation;

import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.Log;

import com.example.wutai.wutaimoutain.Utils.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwei on 2018/8/7.
 */

public class HotArea implements Serializable {

    private static final long serialVersionUID = -6440170308520798408L;

    private String code;

    private String title;

    private String desc;

    private String img;

    private String event;

    private int[] pts;

    private List<HotArea> areas = new ArrayList<HotArea>();

    public HotArea(){}

    public HotArea(String code, String title, String img, String desc, int[] pts) {
        this.code = code;
        this.title = title;
        this.desc = desc;
        this.img = img;
        this.pts = pts;
    }

    public HotArea(String code, String title, String img, String desc, String[] pts) {
        this.code = code;
        this.title = title;
        this.img = img;
        setStrArrayToIntArray(pts);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setPts(int[] pts) {
        this.pts = pts;
    }

    public void setPts(String[] pts) {
        setStrArrayToIntArray(pts);
    }

    public void setPts(String pts, String split) {
        if(!StringUtils.isNullOrEmpty(pts) && !StringUtils.isNullOrEmpty(split)) {
            String[] points = pts.split(split) ;
            setPts(points);
        }
    }

    private void setStrArrayToIntArray(String[] pts) {
        if(null != pts && 0 != pts.length) {
            int len = pts.length;
            this.pts = new int[len];
            for(int i = 0; i < len; ++i) {
                try{
                    this.pts[i] = Integer.parseInt(pts[i]);
                }catch(Exception e) {
                    this.pts[i] = 0;
                    Log.e("SmartPit", e.getMessage());
                }
            }
        }
    }

    public String getDesc() {
        String tempDesc = desc;
        if(!StringUtils.isNullOrEmpty(tempDesc) && tempDesc.startsWith("<![CDATA[") && tempDesc.endsWith("]]>")) {
            tempDesc = desc.substring("<![CDATA[".length(), desc.length() - "]]>".length());
        }
        return tempDesc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<HotArea> getAreas() {
        return areas;
    }

    public void setAreas(List<HotArea> areas) {
        this.areas = areas;
    }

    public CheckArea getCheckArea() {
        CheckArea checkArea = null;
        if(pts != null) {
            checkArea = new CheckArea(pts);
        }
        return checkArea;
    }

    /**
     * 写内部类的原因在于在Activity之间传递时，
     * 不能传未实现Serializable接口的类
     */
    public class CheckArea{
        private Path path;
        //当前处理是从点的个数来判断是矩形 还是多边形,这两种的方式对点的位置判断不太一样
        private boolean isRectF;
        private CheckArea(int[] pts) {
            this.path = new Path();
            int len = pts.length;
            isRectF = len == 4;
            for(int i = 0; i < len;) {
                if(i == 0) {
                    this.path.moveTo(pts[i++], pts[i++]);
                }else {
                    this.path.lineTo(pts[i++], pts[i++]);
                }
            }
            this.path.close();
        }

        public Path getPath() {
            return this.path;
        }

        /**
         * 检测是否在区域范围内
         * @param rectf 从外部传可以重用
         * @param x
         * @param y
         * @return
         */
        public boolean isInArea(RectF rectf, float x, float y) {
            boolean resStatus = false;
            if(this.path != null) {
                rectf.setEmpty();
                path.computeBounds(rectf, true);
                if(isRectF) {
                    //当是矩形时
                    resStatus = rectf.contains(x, y);
                }else {
                    //如果是多边形时
                    Region region = new Region();
                    region.setPath(path, region);
                    region.setPath(path, new Region((int)rectf.left, (int)rectf.top, (int)rectf.right, (int)rectf.bottom));
                    resStatus = region.contains((int)x, (int)y);
                }
            }
            return resStatus;
        }
    }
}

